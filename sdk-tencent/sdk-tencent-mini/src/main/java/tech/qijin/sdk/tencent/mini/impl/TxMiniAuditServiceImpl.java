package tech.qijin.sdk.tencent.mini.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tech.qijin.sdk.tencent.base.TxAuditLabel;
import tech.qijin.sdk.tencent.base.TxAuditScene;
import tech.qijin.sdk.tencent.base.TxMiniErrorCode;
import tech.qijin.sdk.tencent.mini.TxMiniAuditService;
import tech.qijin.sdk.tencent.mini.pojo.TxAuditResp;
import tech.qijin.util4j.aop.annotation.Retry;
import tech.qijin.util4j.utils.NumberUtil;

import java.util.Map;
import java.util.Set;

@Slf4j
@Service
public class TxMiniAuditServiceImpl extends TxMiniTokenServiceImpl implements TxMiniAuditService {
    @Autowired
    private RestTemplate txMiniClient;

    private Map<TxAuditScene, Set<TxAuditLabel>> labelsMap = new HashedMap() {{
        put(TxAuditScene.PROFILE, Sets.newHashSet(TxAuditLabel.POLITICS, TxAuditLabel.POLITICS, TxAuditLabel.CRIME, TxAuditLabel.PORN));
        put(TxAuditScene.COMMENT, Sets.newHashSet(TxAuditLabel.POLITICS, TxAuditLabel.POLITICS, TxAuditLabel.CRIME, TxAuditLabel.PORN));
        put(TxAuditScene.FEED, Sets.newHashSet(TxAuditLabel.POLITICS, TxAuditLabel.POLITICS, TxAuditLabel.CRIME, TxAuditLabel.PORN));
        put(TxAuditScene.LOG, Sets.newHashSet(TxAuditLabel.POLITICS, TxAuditLabel.POLITICS, TxAuditLabel.CRIME, TxAuditLabel.PORN));
    }};


    @Retry
    @Override
    public boolean checkMsg(String openid, String content, TxAuditScene scene) {
        String accessToken = getToken();
        String uri = String.format(MSG_CHECK_URL, accessToken);
        Map<String, Object> data = Maps.newHashMap();
        data.put("version", 2);
        data.put("openid", openid);
        data.put("scene", scene.getCode());
        data.put("content", content);
        ResponseEntity<String> entity = txMiniClient.postForEntity(requestUrl(uri), data, String.class);
        TxAuditResp resp = TxAuditResp.fromJson(entity.getBody());
        if (resp == null) {
            log.error("checkMsg error, openid={}, content={}, scene={}, resp={}", openid, content, scene, resp);
            return true;
        }
        if (resp.getErrcode() == TxMiniErrorCode.CODE_40001.getCode()) {
            delToken();
            throw new IllegalStateException();
        }
        for (TxAuditResp.TxAuditDetail detail : resp.getDetail()) {
            if (detail.getErrcode() != 0) continue;
            if (detail.getLabel() == null) continue;
            TxAuditLabel label = TxAuditLabel.valueOf(detail.getLabel());
            if (label == null) continue;
            if (!labelsMap.get(scene).contains(label)) continue;
            if (detail.getSuggest().equals("risky") && NumberUtil.gtZero(detail.getProb()) && detail.getProb() >= 80) {
                log.warn("checkMsg risk content={}, detail={}", content, detail);
                return false;
            }
        }
        return true;
    }
}

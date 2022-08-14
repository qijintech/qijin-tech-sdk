package tech.qijin.sdk.tencent.mini.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tech.qijin.sdk.tencent.base.TxMiniErrorCode;
import tech.qijin.sdk.tencent.mini.TxMiniMsgService;
import tech.qijin.sdk.tencent.mini.pojo.TxSubSendResp;
import tech.qijin.util4j.aop.annotation.Retry;

import java.util.Map;

@Slf4j
@Service
public class TxMiniMsgServiceImpl extends TxMiniTokenServiceImpl implements TxMiniMsgService {
    @Autowired
    private RestTemplate txMiniClient;

    @Retry
    @Override
    public boolean subscribeSend(String openid,
                                 String templateId,
                                 String page,
                                 Object data) {
        String accessToken = getToken();
        String uri = String.format(SUBSCRIBE_SEND_URI, accessToken);
        Map<String, Object> payload = Maps.newHashMap();
        payload.put("touser", openid);
        payload.put("template_id", templateId);
        payload.put("page", page);
        payload.put("data", data);
        log.info("payload={}", JSON.toJSONString(payload));
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=UTF-8");
        HttpEntity<String> entity = new HttpEntity<>(JSON.toJSONString(payload), headers);
        ResponseEntity<String> resEntity = txMiniClient.postForEntity(requestUrl(uri), entity, String.class);
        TxSubSendResp resp = TxSubSendResp.fromJson(resEntity.getBody());
        if (resp == null) {
            log.error("subscribeSend error, openid={}, templateId={}, data={}", openid, templateId, data);
            return true;
        }
        if (resp.getErrcode() == TxMiniErrorCode.CODE_40001.getCode()) {
            delToken();
            throw new IllegalStateException();
        }
        return false;
    }
}

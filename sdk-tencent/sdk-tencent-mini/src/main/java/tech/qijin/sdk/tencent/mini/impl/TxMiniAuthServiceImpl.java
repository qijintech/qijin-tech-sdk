package tech.qijin.sdk.tencent.mini.impl;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Joiner;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tech.qijin.sdk.tencent.base.Constants;
import tech.qijin.sdk.tencent.base.TxErrorException;
import tech.qijin.sdk.tencent.mini.TxMiniAuthService;
import tech.qijin.sdk.tencent.mini.pojo.TxJscode2SessionResp;
import tech.qijin.util4j.kms.KmsBean;
import tech.qijin.util4j.lang.constant.ResEnum;
import tech.qijin.util4j.utils.MAssert;

import java.util.Map;
import java.util.Optional;

/**
 * @author michealyang
 * @date 2020-02-25
 * @relax: 开始眼保健操 ←_← ↓_↓ →_→ ↑_↑
 */
@Slf4j
@Service
public class TxMiniAuthServiceImpl implements TxMiniAuthService {
    @Autowired
    private KmsBean kmsBean;
    @Autowired
    private RestTemplate txMiniClient;

    @Override
    public TxJscode2SessionResp jsCode2SessionInfo(String jsCode) throws TxErrorException {
        Optional<String> appIdOpt = kmsBean.getAppId(Constants.TX_KMS_PREFIX);
        Optional<String> secretOpt = kmsBean.getSecret(Constants.TX_KMS_PREFIX);
        MAssert.isTrue(appIdOpt.isPresent() && secretOpt.isPresent(), ResEnum.INVALID_CONFIG);
        Map<String, String> params = Maps.newHashMap();
        params.put("appid", appIdOpt.get());
        params.put("secret", secretOpt.get());
        params.put("js_code", jsCode);
        params.put("grant_type", "authorization_code");
        String queryString = Joiner.on("&").withKeyValueSeparator("=").join(params);
        String url = String.format("%s%s?%s", TxMiniAuthService.DEFAULT_HOST, TxMiniAuthService.JSCODE_TO_SESSION_URL, queryString);
        ResponseEntity<TxJscode2SessionResp> result = txMiniClient.getForEntity(url, TxJscode2SessionResp.class);
        return result.getBody();
    }
}

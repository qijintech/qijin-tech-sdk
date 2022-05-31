package tech.qijin.sdk.tencent.mini.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tech.qijin.sdk.tencent.mini.TxMiniMsgService;
import tech.qijin.sdk.tencent.mini.pojo.TxSubSendResp;

import java.util.Map;

@Slf4j
@Service
public class TxMiniMsgServiceImpl extends TxMiniTokenServiceImpl implements TxMiniMsgService {
    @Autowired
    private RestTemplate txMiniClient;

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
        ResponseEntity<String> entity = txMiniClient.postForEntity(requestUrl(uri), JSON.toJSONString(payload), String.class);
        TxSubSendResp resp = TxSubSendResp.fromJson(entity.getBody());
        if (resp == null) {
            log.error("subscribeSend error, openid={}, templateId={}, data={}", openid, templateId, data);
            return true;
        }
        return false;
    }
}

package tech.qijin.sdk.huanxin.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import tech.qijin.sdk.huanxin.pojo.HuanXinMsgImg;
import tech.qijin.sdk.huanxin.pojo.HuanXinMsgText;
import tech.qijin.sdk.huanxin.service.HuanXinMsgService;
import tech.qijin.sdk.huanxin.service.HuanXinRestClient;

import java.util.Map;

/**
 * @author michealyang
 * @date 2019-11-19
 * @relax: 开始眼保健操 ←_← ↓_↓ →_→ ↑_↑
 */
@Slf4j
@Service
@Validated
public class HuanXinMsgServiceImpl extends HuanXinAbstractMsgService implements HuanXinMsgService {
    private static final String URL_MESSAGE_SUFFIX = "messages";
    @Autowired
    private HuanXinRestClient huanXinRestClient;

    @Override
    public void sendText(HuanXinMsgText text) {
        Map<String, Object> req = buildReq(text);
        String data = huanXinRestClient.doPost(URL_MESSAGE_SUFFIX, req, String.class);
        log.info(data);
    }

    @Override
    public void sendImg(HuanXinMsgImg img) {
        Map<String, Object> req = buildReq(img);
        Map<String, Object> msg = (Map<String, Object>) req.get("msg");
        msg.put("url", "http://a1-vip6.easemob.com/gmu/jimu/chatfiles/" + img.getUuid());
        String data = huanXinRestClient.doPost(URL_MESSAGE_SUFFIX, req, String.class);
        log.info(data);
    }
}

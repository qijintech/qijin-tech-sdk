package tech.qijin.sdk.tencent.mini;

/**
 * 小程序 - 消息相关操作
 *
 * @author michealyang
 * @date 2020-02-25
 * @relax: 开始眼保健操 ←_← ↓_↓ →_→ ↑_↑
 */
public interface TxMiniMsgService extends TxMiniTokenService {
    String SUBSCRIBE_SEND_URI = "/cgi-bin/message/subscribe/send?access_token=%s";

    boolean subscribeSend(String openid,
                          String templateId,
                          String page,
                          Object data);

}

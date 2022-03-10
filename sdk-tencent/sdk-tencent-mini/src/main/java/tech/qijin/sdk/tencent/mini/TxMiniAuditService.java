package tech.qijin.sdk.tencent.mini;

import tech.qijin.sdk.tencent.base.TxAuditScene;

public interface TxMiniAuditService extends TxMiniTokenService {
    /**
     * 内容校验 api
     */
    String MSG_CHECK_URL = "/wxa/msg_sec_check?access_token=%s";


    boolean checkMsg(String openid, String content, TxAuditScene scene);
}

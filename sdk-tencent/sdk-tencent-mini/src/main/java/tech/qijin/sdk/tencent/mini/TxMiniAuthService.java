package tech.qijin.sdk.tencent.mini;

import tech.qijin.sdk.tencent.base.TxErrorException;
import tech.qijin.sdk.tencent.mini.pojo.TxJscode2SessionResp;
import tech.qijin.sdk.tencent.mini.pojo.UserPhoneInfo;

/**
 * 小程序 - 登录及权限相关功能
 *
 * @author michealyang
 * @date 2020-02-25
 * @relax: 开始眼保健操 ←_← ↓_↓ →_→ ↑_↑
 */
public interface TxMiniAuthService {
    String DEFAULT_HOST = "https://api.weixin.qq.com";
    /**
     * 获取access_token.
     */
    String GET_ACCESS_TOKEN_URL = "/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";

    String JSCODE_TO_SESSION_URL = "/sns/jscode2session";
    /**
     * getPaidUnionId
     */
    String GET_PAID_UNION_ID_URL = "/wxa/getpaidunionid";

    /**
     * 获取登录后的session信息.
     *
     * @param jsCode 登录时获取的 code
     */
    TxJscode2SessionResp jsCode2SessionInfo(String jsCode) throws TxErrorException;

    /**
     * 微信小程序用户数据解密，包含手机号
     *
     * @param sessionKey
     * @param encryptedData
     * @param iv
     */
    UserPhoneInfo decodeUserInfo(String sessionKey, String encryptedData, String iv);

}

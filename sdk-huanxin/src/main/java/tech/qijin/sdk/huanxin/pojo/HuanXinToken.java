package tech.qijin.sdk.huanxin.pojo;

import lombok.Data;

/**
 * @author michealyang
 * @date 2019-11-15
 * @relax: 开始眼保健操 ←_← ↓_↓ →_→ ↑_↑
 */
@Data
public class HuanXinToken {
    //有效的token字符串
    private String accessToken;
    //token 有效时间，以秒为单位，在有效期内不需要重复获取
    private String expiresIn;
    //当前 App 的 UUID 值
    private String application;
}

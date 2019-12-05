package tech.qijin.sdk.tencent.pojo;

import lombok.Data;

/**
 * @author michealyang
 * @date 2019-12-05
 * @relax: 开始眼保健操 ←_← ↓_↓ →_→ ↑_↑
 */
@Data
public class CredentialVo {
    private Credential credentials;
    private String expiration;
    private Long startTime;
    private Long expiredTime;
}

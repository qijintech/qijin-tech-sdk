package tech.qijin.sdk.tencent.cloud.pojo;

import lombok.Data;

/**
 * @author michealyang
 * @date 2019-12-05
 * @relax: 开始眼保健操 ←_← ↓_↓ →_→ ↑_↑
 */
@Data
public class TxCredential {
    private String tmpSecretId;
    private String tmpSecretKey;
    private String sessionToken;
}

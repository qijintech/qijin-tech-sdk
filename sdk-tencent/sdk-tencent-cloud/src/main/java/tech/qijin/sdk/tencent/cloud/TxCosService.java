package tech.qijin.sdk.tencent.cloud;


import tech.qijin.sdk.tencent.cloud.pojo.TxCosType;
import tech.qijin.sdk.tencent.cloud.pojo.TxCredentialVo;

/**
 * @author michealyang
 * @date 2019-12-05
 * @relax: 开始眼保健操 ←_← ↓_↓ →_→ ↑_↑
 * @see: https://cloud.tencent.com/product/cos
 */
public interface TxCosService {
    // 获取上传OSS的凭证，小程序使用
    TxCredentialVo getCredential(TxCosType txCosType);
}

package tech.qijin.sdk.tencent.cloud;


import tech.qijin.sdk.tencent.cloud.pojo.CosUploadVo;
import tech.qijin.sdk.tencent.cloud.pojo.TxCosType;
import tech.qijin.sdk.tencent.cloud.pojo.TxCredentialVo;

import java.io.InputStream;
import java.util.Optional;

/**
 * @author michealyang
 * @date 2019-12-05
 * @relax: 开始眼保健操 ←_← ↓_↓ →_→ ↑_↑
 * @see: https://cloud.tencent.com/product/cos
 */
public interface TxCosService {
    // 获取上传OSS的凭证，小程序使用
    TxCredentialVo getCredential(TxCosType txCosType);

    /**
     * 将输入流(input stream)通过腾讯API上传到COS，并得到url
     *
     * @param sceneOpt    自定义场景。不同场景对应不同的bucket，host等
     * @param fileName    上传资源成功后返回的文件名。为空时，会自动生成文件名
     * @param fileSize    资源文件大小
     * @param inputStream 待上传的资源流
     */
    CosUploadVo uploadFile(Optional<String> sceneOpt, Optional<String> fileName, Long fileSize, InputStream inputStream);
}

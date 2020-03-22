package tech.qijin.sdk.tencent.cloud.pojo;

import lombok.Builder;
import lombok.Data;

/**
 * @author michealyang
 * @date 2020-03-22
 * @relax: 开始眼保健操 ←_← ↓_↓ →_→ ↑_↑
 */
@Data
@Builder
public class CosUploadVo {
    private String eTag;
    private String url;
}

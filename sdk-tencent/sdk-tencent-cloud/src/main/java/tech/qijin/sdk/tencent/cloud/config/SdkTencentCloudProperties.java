package tech.qijin.sdk.tencent.cloud.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author michealyang
 * @date 2020-02-25
 * @relax: 开始眼保健操 ←_← ↓_↓ →_→ ↑_↑
 */
@Data
@ConfigurationProperties(prefix = "sdk.tencent.cloud")
public class SdkTencentCloudProperties {
    // COS 桶
    private String bucket;
    // 区域
    private String region;
    // 秘钥有效期，单位是秒
    private Integer duration;
}

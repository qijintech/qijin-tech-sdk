package tech.qijin.sdk.huanxin.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author michealyang
 * @date 2019-11-15
 * @relax: 开始眼保健操 ←_← ↓_↓ →_→ ↑_↑
 */
@Data
@ConfigurationProperties(prefix = "huanxin")
public class HuanXinProperties {
    private String host;
    private String orgName;
    private String appName;
    private String clientId;
    private String clientSecret;
}

package tech.qijin.sdk.tencent.cloud.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author michealyang
 * @date 2020-02-25
 * @relax: 开始眼保健操 ←_← ↓_↓ →_→ ↑_↑
 */
@Configuration
@ComponentScan(value = {"tech.qijin.sdk.tencent.cloud"})
@Import({SdkTencentCloudPropertiesConfiguration.class})
@EnableConfigurationProperties(SdkTencentCloudProperties.class)
public class SdkTencentCloudAutoConfiguration {
}

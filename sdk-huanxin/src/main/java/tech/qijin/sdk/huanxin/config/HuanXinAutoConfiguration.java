package tech.qijin.sdk.huanxin.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author michealyang
 * @date 2019-11-15
 * @relax: 开始眼保健操 ←_← ↓_↓ →_→ ↑_↑
 */
@Configuration
@ComponentScan("tech.qijin.sdk.huanxin")
@EnableConfigurationProperties(HuanXinProperties.class)
public class HuanXinAutoConfiguration {
}

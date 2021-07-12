package tech.qijin.sdk.tencent.mini.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import tech.qijin.sdk.tencent.base.WxMappingJackson2HttpMessageConverter;

@Configuration
@ComponentScan(basePackages = "tech.qijin.sdk.tencent.mini")
@EnableConfigurationProperties
public class SdkTxMiniAutoConfiguration {
    @Bean("txMiniClient")
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new WxMappingJackson2HttpMessageConverter());
        return restTemplate;
    }
}

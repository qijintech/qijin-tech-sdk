package tech.qijin.sdk.tencent.mini.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import tech.qijin.util4j.rpc.config.http.HttpClientBuilder;

@Configuration
@EnableConfigurationProperties
public class SdkTxMiniAutoConfiguration {

    @Bean("wxApiBuilder")
    @ConfigurationProperties(prefix = "spring.util4j.rpc.http.wxapi")
    public HttpClientBuilder wxApiBuilder() {
        return HttpClientBuilder.create();
    }

    @Bean("wxApi")
    public WebClient wxApi(@Qualifier("wxApiBuilder") HttpClientBuilder wxApiBuilder) {
        return wxApiBuilder.build("https://api.weixin.qq.com");
    }
}

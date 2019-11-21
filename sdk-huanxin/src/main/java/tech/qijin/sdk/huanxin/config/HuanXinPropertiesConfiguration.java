package tech.qijin.sdk.huanxin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

/**
 * @author michealyang
 * @date 2019/3/8
 * 开始做眼保健操：←_← ↑_↑ →_→ ↓_↓
 **/
@Configuration
public class HuanXinPropertiesConfiguration {
    @Profile("dev")
    @Configuration
    @PropertySource("classpath:sdk-huanxin-dev.properties")
    public static class DevConfiguration {
    }

    @Profile("test")
    @Configuration
    @PropertySource("classpath:sdk-huanxin-test.properties")
    public static class TestConfiguration {
    }

    @Profile("prod")
    @Configuration
    @PropertySource("classpath:sdk-huanxin-prod.properties")
    public static class ProdConfiguration {
    }
}

package tech.qijin.sdk.qiniu.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author UnitTest
 */

@SpringBootApplication(scanBasePackages = {"tech.qijin.sdk.qiniu"})
public class ServiceApplicationTest {

    public static void main(String[] args) {
        SpringApplication.run(ServiceApplicationTest.class, args);
    }
}

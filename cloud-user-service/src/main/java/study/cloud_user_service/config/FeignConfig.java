package study.cloud_user_service.config;

import feign.Logger;
import feign.Request;
import feign.RetryableException;
import feign.Retryer;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import study.cloud_user_service.config.errordecoder.FeignErrorDecoder;

import java.util.concurrent.TimeUnit;

@Configuration

public class FeignConfig {

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    // 타임아웃 설정
    @Bean
    public Request.Options requestOptions() {
        return new Request.Options(5 * 1000, 10 * 1000); // 연결 타임아웃, 읽기 타임아웃
    }

    // 재시도 설정
    @Bean
    public Retryer feignRetryer() {
        return new Retryer.Default(100L, TimeUnit.SECONDS.toMillis(3L), 5);
    }

}


package com.ben.config;

import com.ben.infrastructure.persistent.gateway.IQQInfoService;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Slf4j
@Configuration
@EnableConfigurationProperties(Retrofit2ConfigProperties.class)
public class Retrofit2Config {
    @Bean
    public Retrofit retrofit(Retrofit2ConfigProperties properties) {
        // 创建日志拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        // 自定义拦截器打印请求地址
//        Interceptor customInterceptor = chain -> {
//            Request request = chain.request();
//            log.info("请求地址：{}", request.url());
//            return chain.proceed(request);
//        };

        // 配置 OkHttpClient
        OkHttpClient client = new OkHttpClient.Builder()
//                .addInterceptor(customInterceptor)
                .addInterceptor(loggingInterceptor)
                .build();

        return new Retrofit.Builder()
                .baseUrl(properties.getHost()) // "https://api.qjqq.cn"
                .client(client)
                .addConverterFactory(JacksonConverterFactory.create()).build();
    }

    @Bean
    public IQQInfoService qqInfoService(Retrofit retrofit) {
        return retrofit.create(IQQInfoService.class);
    }

}

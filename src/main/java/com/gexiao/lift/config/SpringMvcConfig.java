package com.gexiao.lift.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Spring MVC 配置类（注入FastJson）
 *
 * @author : lzx
 * Created by lzx on 18/12/10.
 */
@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {

    /**
     * 添加跨域支持
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedHeaders("*")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowCredentials(true)
                .maxAge(3600L);
    }


    /**
     * 跨域配置
     * spring boot 配置跨域的坑 https://segmentfault.com/a/1190000018018849
     */
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig());
        return new CorsFilter(source);
    }

    private CorsConfiguration corsConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setMaxAge(3600L);
        return corsConfiguration;
    }

    /**
     * 注入restTemplate（移除jackson并设置fastJson作为解析）
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate() {{
            List<HttpMessageConverter<?>> messageConverters = getMessageConverters();
            messageConverters.removeIf(next -> next instanceof MappingJackson2HttpMessageConverter);
            messageConverters.add(fastJsonConverter());
        }};
    }

    /**
     * 添加拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor()).addPathPatterns("/**");
    }

    /**
     * 注入拦截器给spring管理，不然无法读取配置文件
     */
    @Bean
    public LoginInterceptor loginInterceptor() {
        return new LoginInterceptor();
    }


    /**
     * 设置编码，解决中文乱码
     */
    @Bean
    public StringHttpMessageConverter stringHttpMessageConverter() {
        return new StringHttpMessageConverter(StandardCharsets.UTF_8);
    }

    /**
     * 添加到内置转换器之后（移除jackson，使用fastJson）
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.removeIf(next -> next instanceof MappingJackson2HttpMessageConverter);
        converters.add(fastJsonConverter());
    }

    /**
     * 配置fastJson
     */
    private AbstractHttpMessageConverter fastJsonConverter() {
        final FastJsonHttpMessageConverter fastJsonConverter = new FastJsonHttpMessageConverter();
        final FastJsonConfig config = new FastJsonConfig();
        config.setSerializerFeatures(SerializerFeature.IgnoreErrorGetter);
        config.setCharset(StandardCharsets.UTF_8);
        config.setDateFormat("yyyy-MM-dd HH:mm:ss");
        fastJsonConverter.setFastJsonConfig(config);
        //spring要求Content-Type不能含有通配符，fastJson默认为MediaType.ALL不可使用，这应该是一种保护机制,并强制用户自己配置MediaType
        List<MediaType> supportedMediaTypes = new ArrayList<>();
        supportedMediaTypes.add(MediaType.APPLICATION_JSON);
        supportedMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        fastJsonConverter.setSupportedMediaTypes(supportedMediaTypes);

        return fastJsonConverter;
    }
}

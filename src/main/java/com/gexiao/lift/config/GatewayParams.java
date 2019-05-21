package com.gexiao.lift.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Auther: gexiao
 * @Date: 2019/4/10 16:47
 * @Description:
 */
@Component
@ConfigurationProperties(prefix = "gateway")
@Getter
@Setter
@ToString
public class GatewayParams {

   private List<String> unchecked;

}

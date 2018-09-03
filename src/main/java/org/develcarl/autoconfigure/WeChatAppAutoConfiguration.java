package org.develcarl.autoconfigure;

import org.develcarl.request.WeChatRequest;
import org.develcarl.request.body.RequestBodyHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yichen
 * @description
 * @date 2018/7/25
 **/
@Configuration
@EnableConfigurationProperties(value = {WeChatAppProperties.class})
@ConditionalOnClass(value = {WeChatRequest.class})
public class WeChatAppAutoConfiguration {

    @Autowired
    private WeChatAppProperties weChatAppProperties;

    @Bean
    @ConditionalOnMissingBean(value = {WeChatRequest.class})
    public WeChatRequest weChatRequest(){
        return new WeChatRequest(weChatAppProperties);
    }

    @Bean
    @ConditionalOnMissingBean(value = {RequestBodyHelper.class})
    public RequestBodyHelper requestBodyHelper(){
        return new RequestBodyHelper(weChatAppProperties);
    }


}

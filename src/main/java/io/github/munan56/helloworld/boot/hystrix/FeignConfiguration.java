package io.github.munan56.helloworld.boot.hystrix;

import feign.Feign;
import feign.Logger;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Configuration
@ConditionalOnClass({Feign.class})
public class FeignConfiguration {
    @Bean
    public WebMvcRegistrations feignWebRegistrations() {
        return new WebMvcRegistrations() {
            @Override
            public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
                return new FeignRequestMappingHandlerMapping();
            }
        };
    }


    @Bean
    public Logger.Level level(){
        return Logger.Level.BASIC;
    }

    /**
     *
     * @FeignClient(url = "${second.url:localhost:8081}", name = "secondApi", fallback = ClientFallBack.class)
     * @RequestMapping("/api/second")
     * public interface Client {
     *
     * }
     * 启动时候的 冲突问题
     *
     */
    private static class FeignRequestMappingHandlerMapping extends RequestMappingHandlerMapping {
        @Override
        protected boolean isHandler(Class<?> beanType) {
            return super.isHandler(beanType) &&
                    !AnnotatedElementUtils.hasAnnotation(beanType, FeignClient.class);
        }
    }



}

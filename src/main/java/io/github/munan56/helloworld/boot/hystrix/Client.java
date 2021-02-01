package io.github.munan56.helloworld.boot.hystrix;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(url = "${second.url:localhost:8081}", name = "secondApi", fallback = ClientFallBack.class)
@RequestMapping("/api/second")
public interface Client {

    @RequestMapping("/hello")
    String hello();


}

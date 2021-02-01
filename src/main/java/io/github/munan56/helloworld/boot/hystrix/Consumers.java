package io.github.munan56.helloworld.boot.hystrix;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/consumer/second")
public class Consumers {

    @Autowired
    Client client;


    @RequestMapping("/hello")
    public  String hello(){
        return client.hello();

    }
}

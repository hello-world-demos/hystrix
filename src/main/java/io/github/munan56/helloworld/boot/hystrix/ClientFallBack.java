package io.github.munan56.helloworld.boot.hystrix;

import org.springframework.stereotype.Service;

@Service
public class ClientFallBack implements Client {
    @Override
    public String hello() {
        return "ERROR";
    }
}

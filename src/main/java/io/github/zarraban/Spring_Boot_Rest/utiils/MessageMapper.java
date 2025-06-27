package io.github.zarraban.Spring_Boot_Rest.utiils;

import org.springframework.stereotype.Component;

import java.util.Map;
@Component
public class MessageMapper {

    public Map<String,String> messageToJson(String message){
        return Map.of("message", message);

    }
}

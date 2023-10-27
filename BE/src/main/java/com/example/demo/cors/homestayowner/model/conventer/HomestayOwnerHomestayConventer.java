package com.example.demo.cors.homestayowner.model.conventer;

import com.example.demo.cors.homestayowner.model.request.HomestayownerHomestayRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class HomestayOwnerHomestayConventer implements Converter<String, HomestayownerHomestayRequest> {
    @Override
    public HomestayownerHomestayRequest convert(String source) {
        ObjectMapper objectMapper=new ObjectMapper();
        try {
            return objectMapper.readValue(source,HomestayownerHomestayRequest.class);
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}

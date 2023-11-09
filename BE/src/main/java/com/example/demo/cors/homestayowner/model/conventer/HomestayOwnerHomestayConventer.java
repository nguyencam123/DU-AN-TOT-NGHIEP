package com.example.demo.cors.homestayowner.model.conventer;

import com.example.demo.cors.homestayowner.model.request.HomestayownerHomestayRequest;
import com.example.demo.infrastructure.exception.rest.RestApiException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class HomestayOwnerHomestayConventer implements Converter<String, HomestayownerHomestayRequest> {
    @Override
    public HomestayownerHomestayRequest convert(String source) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(source);
            if (!jsonNode.has("name") || jsonNode.get("name").textValue().isEmpty() ||jsonNode.get("name").isNull()) {
                throw new RestApiException("Trường 'name' bị trống");
            }
            if (!jsonNode.has("startDate") || !jsonNode.get("startDate").isLong() ||jsonNode.get("startDate").isNull()) {
                throw new RestApiException("Trường 'startDate' bị trống");
            }
            if (!jsonNode.has("endDate") || !jsonNode.get("endDate").isLong() ||jsonNode.get("startDate").isNull()) {
                throw new RestApiException("Trường 'endDate' bị trống");
            }
            if (!jsonNode.has("desc") || jsonNode.get("desc").textValue().isEmpty() || jsonNode.get("desc").isNull()) {
                throw new RestApiException("Trường 'desc' bị trống");
            }
            if (!jsonNode.has("address") || jsonNode.get("address").textValue().isEmpty()||jsonNode.get("address").isNull()) {
                throw new RestApiException("Trường 'address' bị trống");
            }
            if (!jsonNode.has("price") || !jsonNode.get("price").isInt() || jsonNode.get("price").isNull()) {
                throw new RestApiException("Trường 'price' bị trống");
            }
            if (!jsonNode.has("numberPerson") || !jsonNode.get("numberPerson").isInt() || jsonNode.get("numberPerson").isNull()) {
                throw new RestApiException("Trường 'numberPerson' bị trống");
            }
            if (!jsonNode.has("ownerHomestay") || jsonNode.get("ownerHomestay").textValue().isEmpty() || jsonNode.get("ownerHomestay").isNull()) {
                throw new RestApiException("Trường 'ownerHomestay' bị trống");
            }
            return objectMapper.readValue(source, HomestayownerHomestayRequest.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}





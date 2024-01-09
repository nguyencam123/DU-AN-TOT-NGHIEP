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
            validateField(jsonNode, "name", 200);
            validateField(jsonNode, "timeCheckIn", -1);
            validateField(jsonNode, "timeCheckOut", -1);
            validateField(jsonNode, "acreage", -1);
            validateField(jsonNode, "cancellationPolicy", -1);
            validateIntField(jsonNode, "roomNumber");
            validateLongField(jsonNode, "startDate");
            validateLongField(jsonNode, "endDate");
            validateField(jsonNode, "desc", 2000);
            validateField(jsonNode, "address", 300);
            validateIntField(jsonNode, "price");
            validateIntField(jsonNode, "numberPerson");
            validateField(jsonNode, "ownerHomestay", -1);

            return objectMapper.readValue(source, HomestayownerHomestayRequest.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void validateField(JsonNode jsonNode, String fieldName, int maxLength) {
        String fieldValue = jsonNode.get(fieldName).textValue();
        String trimmedValue = fieldValue.trim();
        if (fieldValue.isEmpty() || fieldValue.length() > maxLength || !fieldValue.equals(trimmedValue)) {
            throw new RestApiException("Trường '" + fieldName + "' bị trống hoặc nhiều hơn " + maxLength + " kí tự hoặc chứa dấu cách ở đầu hoặc cuối");
        }
    }

    private void validateIntField(JsonNode jsonNode, String fieldName) {
        if (!jsonNode.has(fieldName) || !jsonNode.get(fieldName).isInt() || jsonNode.get(fieldName).asInt() <= 0) {
            throw new RestApiException("Trường '" + fieldName + "' bị trống hoặc không phải số nguyên dương");
        }
    }

    private void validateLongField(JsonNode jsonNode, String fieldName) {
        if (!jsonNode.has(fieldName) || !jsonNode.get(fieldName).isLong()) {
            throw new RestApiException("Trường '" + fieldName + "' bị trống hoặc không phải kiểu số nguyên dài (long)");
        }
    }
}





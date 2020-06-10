package com.smartosc.team5.abstracts;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.json.JsonParseException;

import java.io.IOException;

/**
 * team5
 *
 * @author ThaoPhuong
 * @created_at 09/06/2020 - 05:25 PM
 * @created_by ThaoPhuong
 * @since 09/06/2020
 */
public class AbstractTest {
    /**
     * convert Java object to Json
     * @param obj
     * @return
     * @throws JsonProcessingException
     */
    public static String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
}

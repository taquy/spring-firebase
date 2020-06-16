package fpt.fbiz.fremote.entities;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class BaseEntity {
    public Map<String, Object> toMap() {
        return new ObjectMapper().convertValue(this, Map.class);
    }
}

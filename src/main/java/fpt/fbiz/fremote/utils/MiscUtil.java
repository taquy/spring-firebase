package fpt.fbiz.fremote.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class MiscUtil {

    public static <T> T convertMapToObject(Map map, Class<T> clz) {
        final ObjectMapper mapper = new ObjectMapper(); // jackson's objectmapper
        return (T) mapper.convertValue(map, clz);
    }

}

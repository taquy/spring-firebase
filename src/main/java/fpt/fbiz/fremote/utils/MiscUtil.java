package fpt.fbiz.fremote.utils;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;

public class MiscUtil {

    public static <T> T convertMapToObject(Map map, Class<T> clz) {
        final ObjectMapper mapper = new ObjectMapper().setVisibility(PropertyAccessor.FIELD,
                Visibility.ANY);
        mapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
        return (T) mapper.convertValue(map, clz);
    }

}

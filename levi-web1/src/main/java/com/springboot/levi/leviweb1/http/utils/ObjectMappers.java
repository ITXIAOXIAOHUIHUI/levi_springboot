package com.springboot.levi.leviweb1.http.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.levi.leviweb1.http.enums.ErrorCode;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Nullable;
import java.io.IOException;

/**
 * @author jianghaihui
 * @date 2021/7/4 10:47
 */
@Slf4j
public class ObjectMappers {

    private static final ObjectMapper DEFAULT_INSTANCE = new ObjectMapper();

    static {
        DEFAULT_INSTANCE.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        DEFAULT_INSTANCE.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        DEFAULT_INSTANCE.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);
    }


    public static ObjectMapper get() {
        return DEFAULT_INSTANCE;
    }

    /**
     * Deserialize <code>json</code> to an object of type <code>clazz</code>.
     *
     * @throws DataFormatException
     */
    public static <T> T mustReadValue(@Nullable String json, Class<T> clazz) {
        if (json == null) {
            return null;
        }
        try {
            return DEFAULT_INSTANCE.readValue(json, clazz);
        } catch (IOException e) {
            throw new DataFormatException(e, ErrorCode.Err_DataFormat);
        }
    }


    /**
     * Deserialize <code>json</code> to an object, using <code>typeRef</code>
     * to determine class of the object.
     *
     * @throws DataFormatException
     */
    public static <T> T mustReadValue(@Nullable String json, TypeReference<T> typeRef) {
        if (json == null) {
            return null;
        }
        try {
            return DEFAULT_INSTANCE.readValue(json, typeRef);
        } catch (IOException e) {
            throw new DataFormatException(e, ErrorCode.Err_DataFormat);
        }
    }

    /**
     * Serialize <code>o</code> to a string.
     *
     * @throws DataFormatException
     */
    public static String mustWriteValue(@Nullable Object o) {
        if (o == null) {
            return null;
        }
        try {
            return DEFAULT_INSTANCE.writeValueAsString(o);
        } catch (IOException e) {
            throw new DataFormatException(e, ErrorCode.Err_DataFormat);
        }
    }

    public static String mustWriteValuePretty(@Nullable Object o) {
        if (o == null) {
            return null;
        }
        try {
            return DEFAULT_INSTANCE.writerWithDefaultPrettyPrinter().writeValueAsString(o);
        } catch (IOException e) {
            throw new DataFormatException(e, ErrorCode.Err_DataFormat);
        }
    }

    public static JsonNode mustReadTree(@Nullable String json) {
        if (json == null) {
            return null;
        }
        try {
            return DEFAULT_INSTANCE.readTree(json);
        } catch (IOException e) {
            throw new DataFormatException(e, ErrorCode.Err_DataFormat);
        }
    }

    public static boolean getResultSuccess(String resultJson) {
        try {
            JsonNode jsonNode = mustReadTree(resultJson);
            JsonNode body = jsonNode.get("body");
            if (body == null) {
                log.error("body is null");
                return false;
            }
            JsonNode success = body.get("success");
            if (success == null) {
                log.error("success is null");
                return false;
            }
            return success.asBoolean();
        } catch (Exception e) {
            log.error("json parse error : {}", e.getMessage());
            return false;
        }
    }

    public static String getResultData(String resultJson) {
        try {
            JsonNode jsonNode = mustReadTree(resultJson);
            JsonNode body = jsonNode.get("body");
            if (body == null) {
                log.error("body is null");
                return resultJson;
            }
            JsonNode data = body.get("data");
            if (data == null) {
                log.error("data is null");
                return resultJson;
            }
            return data.textValue();
        } catch (Exception e) {
            log.error("json parse error : {}", e.getMessage());
            return resultJson;
        }
    }

    public static class DataFormatException extends RuntimeException {
        protected final ErrorCode error;

        public DataFormatException(String m, ErrorCode error) {
            super(m);
            this.error = error;
        }

        public DataFormatException(Throwable t, ErrorCode error) {
            super(t);
            this.error = error;
        }

        public DataFormatException(String m, Throwable t, ErrorCode error) {
            super(m, t);
            this.error = error;
        }
    }

    public static void main(String[] args) {

        String resultJson = "{\"header\":{},\"body\":{\"succss\":false,\"code\":\"OK\"}}";

        System.out.println(getResultSuccess(resultJson));

    }


}

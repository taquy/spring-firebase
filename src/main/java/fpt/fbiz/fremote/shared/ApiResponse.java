package fpt.fbiz.fremote.shared;

import fpt.fbiz.fremote.consts.ApiResponseCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class ApiResponse {
    private String code;
    private String message;
    private Object body;

    public static ApiResponse success(Object body) {
        var response = new ApiResponse();
        response.setCode(ApiResponseCode.SUCCESS.name());
        response.setMessage(ApiResponseCode.SUCCESS.label);
        response.setBody(body);
        return response;
    }

    public static ApiResponse success() {
        return ApiResponse.success(null);
    }

    public static ApiResponse error(Object body) {
        var response = new ApiResponse();
        response.setCode(ApiResponseCode.ERROR.name());
        response.setMessage(ApiResponseCode.ERROR.label);
        response.setBody(body);
        return response;
    }

    public static ApiResponse error() {
        return ApiResponse.error(null);
    }
}

package codes.meo.common.api.exception;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ApiErrorResponse {

    private List<ApiError> errors = new ArrayList<>();

    public List<ApiError> getErrors() {
        return errors;
    }

    public ApiErrorResponse errors(ApiError... errors) {
        this.errors.addAll(Arrays.asList(errors));
        return this;
    }
}
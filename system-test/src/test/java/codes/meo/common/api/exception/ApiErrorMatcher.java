package codes.meo.common.api.exception;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.util.Objects;

public class ApiErrorMatcher extends TypeSafeMatcher<ApiError> {

    private final ApiError expected;

    public ApiErrorMatcher(String code, String message) {
        this.expected = new ApiError().code(code).message(message);
    }

    public static ApiErrorMatcher apiError(String code, String message) {
        return new ApiErrorMatcher(code, message);
    }

    @Override
    protected boolean matchesSafely(ApiError actual) {
        return Objects.equals(expected.getCode(), actual.getCode())
                && Objects.equals(expected.getMessage(), actual.getMessage());
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("code ")
                .appendValue(expected.getCode())
                .appendText(" and message ")
                .appendValue(expected.getMessage());
    }

    @Override
    public void describeMismatchSafely(ApiError actual, Description description) {
        description.appendText("code ")
                .appendValue(actual.getCode())
                .appendText(" and message ")
                .appendValue(actual.getMessage());
    }
}
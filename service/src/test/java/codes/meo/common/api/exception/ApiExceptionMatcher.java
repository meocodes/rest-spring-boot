package codes.meo.common.api.exception;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.text.MessageFormat;
import java.util.Objects;

/**
 * Matcher for {@link ApiException}s.
 */
public class ApiExceptionMatcher extends TypeSafeMatcher<ApiException> {

    private final ApiExceptionType expectedType;
    private final String expectedMessage;

    public ApiExceptionMatcher(ApiExceptionType expectedType, Object... expectedMessageProperties) {
        this.expectedType = expectedType;
        this.expectedMessage = MessageFormat.format(expectedType.getMessage(), expectedMessageProperties);
    }

    public static ApiExceptionMatcher isApiException(ApiExceptionType type, Object... messageProperties) {
        return new ApiExceptionMatcher(type, messageProperties);
    }

    @Override
    protected boolean matchesSafely(ApiException actual) {
        return Objects.equals(expectedType.getCode(), actual.getCode())
                && Objects.equals(expectedType.getStatus(), actual.getStatus())
                && Objects.equals(expectedMessage, actual.getMessage());
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("code ")
                .appendValue(expectedType.getCode())
                .appendText(" and status ")
                .appendValue(expectedType.getStatus())
                .appendText(" and message ")
                .appendValue(expectedMessage);
    }

    @Override
    public void describeMismatchSafely(ApiException actual, Description description) {
        description.appendText("code ")
                .appendValue(actual.getCode())
                .appendText(" and status ")
                .appendValue(actual.getStatus())
                .appendText(" and message ")
                .appendValue(actual.getMessage());
    }
}
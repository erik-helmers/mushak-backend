package core.exceptions;

public enum  ErrorType {
    AUTHENTICATION_FAILED("The authentication failed.", 100),
    REGISTRATION_FAILED("The registration failed.", 300),
    LOGIN_FAILED("The login failed.", 200),
    INTERNAL_ERROR("An internal error occured", 500);

    public String description;
    public int code;

    ErrorType(String description, int code) {
        this.description = description;
        this.code = code;
    }
}

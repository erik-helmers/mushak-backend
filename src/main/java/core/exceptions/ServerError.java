package core.exceptions;

public enum ServerError {

    //region registration
    // =========================================== REGISTRATION ===========================================

    USERNAME_TOO_SHORT(ErrorType.REGISTRATION_FAILED, "username too short", 1),
    USERNAME_TOO_LONG(ErrorType.REGISTRATION_FAILED, "username too long", 2),
    USERNAME_ALREADY_TAKEN(ErrorType.REGISTRATION_FAILED, "username already taken", 3),

    PASSWORD_TOO_SHORT(ErrorType.REGISTRATION_FAILED, "password too short", 4),
    PASSWORD_TOO_LONG(ErrorType.REGISTRATION_FAILED, "password too long", 5),

    //endregion
    //region login
    // ============================================== LOGIN ===============================================

    USERNAME_NOT_FOUND(ErrorType.LOGIN_FAILED, "username not found", 1),
    INCORRECT_PASSWORD(ErrorType.LOGIN_FAILED, "incorrect password", 2),

    //endregion

    //region authentication
    // ========================================== AUTHENTICATION ==========================================

    BAD_TOKEN(ErrorType.AUTHENTICATION_FAILED, "bad token", 1),
    EXPIRED_TOKEN(ErrorType.AUTHENTICATION_FAILED, "expired token", 2),

    //endregion
    
    INTERNAL_ERROR(ErrorType.INTERNAL_ERROR, "internal error", 0);

    


    public final ErrorType type;
    public final String title;
    public final String message;
    public final int code;

    ServerError(ErrorType type, String title, int code) {
        this.type = type;
        this.title = title;
        this.message = "Le dev a clairement eu la flemme de donner un message a cette erreur. No rage.";
        this.code = getCode(code);
    }

    ServerError(ErrorType type, String title, String message, int code) {
        this.type = type;
        this.title = title;
        this.message = message;
        this.code = getCode(code);
    }

    public int getCode(int code){
        return this.type.code+code;
    }
}

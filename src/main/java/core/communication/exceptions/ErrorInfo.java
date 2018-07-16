package core.exceptions;

public enum ErrorInfo {

    //region registration
    // =========================================== REGISTRATION ===========================================

    USERNAME_TOO_SHORT(ErrorType.REGISTRATION_FAILED),
    USERNAME_TOO_LONG(ErrorType.REGISTRATION_FAILED),
    USERNAME_ALREADY_TAKEN(ErrorType.REGISTRATION_FAILED),

    PASSWORD_TOO_SHORT(ErrorType.REGISTRATION_FAILED),
    PASSWORD_TOO_LONG(ErrorType.REGISTRATION_FAILED),

    //endregion
    //region login
    // ============================================== LOGIN ===============================================

    USERNAME_NOT_FOUND(ErrorType.LOGIN_FAILED),
    INCORRECT_PASSWORD(ErrorType.LOGIN_FAILED),

    //endregion

    //region authentication
    // ========================================== AUTHENTICATION ==========================================

    BAD_TOKEN(ErrorType.AUTHENTICATION_FAILED),
    EXPIRED_TOKEN(ErrorType.AUTHENTICATION_FAILED),
    MISSING_TOKEN(ErrorType.AUTHENTICATION_FAILED),

    //endregion
    
    INTERNAL_ERROR(ErrorType.INTERNAL_ERROR, "internal error"),
    NOT_AN_ERROR(ErrorType.INTERNAL_ERROR);

    


    public final ErrorType type;
    public final String user_description;

    ErrorInfo(ErrorType type) {
        this.type = type;
        this.user_description = "Le dev a clairement eu la flemme de donner un message a cette erreur. No rage.";
    }

    ErrorInfo(ErrorType type, String user_description){
        this.type = type;
        this.user_description = user_description;
    }



}

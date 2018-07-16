package core.exceptions;

public class SubError {

    public ErrorInfo type;
    public String comment;
    public Throwable throwable;

    public SubError(ErrorInfo type, String comment){
        this.type = type;
        this.comment = comment;
    }
    public SubError(ErrorInfo type, Throwable throwable){
        this.type = type;
        this.throwable = throwable;
    }
    public SubError(ErrorInfo type){
        this.type = type;
    }



}

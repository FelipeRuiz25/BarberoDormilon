package simulator.models.exceptions;

public class CPUException extends Exception{

    private final ErrorCode code;

    public CPUException(ErrorCode code) {
        super(code.description);
        this.code = code;
    }

    public CPUException(String message, Throwable cause, ErrorCode code) {
        super(message, cause);
        this.code = code;
    }

    public ErrorCode getCode() {
        return code;
    }
}

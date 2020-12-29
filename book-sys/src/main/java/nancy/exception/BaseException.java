package nancy.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName BaseException
 * @Description TODO
 * @Author nancy
 * @Date 2020/12/27 16:00
 * @Version 1.0
 **/
@Getter
@Setter


public class BaseException extends RuntimeException{
    protected String code;
    public BaseException(String code, String message) {
        super(message);
        this.code = code;
    }
    public BaseException(String code, Throwable cause, String message) {
        super(message, cause);
        this.code = code;
    }
}

package nancy.exception;

/**
 * @ClassName SysException
 * @Description TODO
 * @Author nancy
 * @Date 2020/12/27 16:04
 * @Version 1.0
 **/
public class BusinessException extends BaseException{
    public BusinessException(String code, String message) {
        super("SYS" + code, "业务异常" + message);
    }

    public BusinessException(String code, Throwable cause, String message) {
        super("SYS" + code, cause,"业务异常" +  message);
    }
}

package nancy.exception;

/**
 * @ClassName SysException
 * @Description TODO
 * @Author nancy
 * @Date 2020/12/27 16:04
 * @Version 1.0
 **/
public class SysException extends BaseException{
    public SysException(String code, String message) {
        super("SYS" + code, "服务端异常" + message);
    }

    public SysException(String code, Throwable cause, String message) {
        super("SYS" + code, cause, "服务端异常" +  message);
    }
}

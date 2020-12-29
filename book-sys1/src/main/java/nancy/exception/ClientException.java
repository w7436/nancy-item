package nancy.exception;

/**
 * @ClassName ClientException
 * @Description TODO
 * @Author nancy
 * @Date 2020/12/27 16:05
 * @Version 1.0
 **/
public class ClientException extends BaseException{
    public ClientException(String code, String message) {
            super("CLI" + code, "客户端错误" + message);
    }

    public ClientException(String code, Throwable cause, String message) {
        super("CLI" + code, cause,  "客户端错误" + message);
    }
}

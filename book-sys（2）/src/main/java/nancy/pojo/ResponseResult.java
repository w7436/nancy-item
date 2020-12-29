package nancy.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @ClassName ResponseResult
 * @Description TODO
 * @Author nancy
 * @Date 2020/12/27 16:25
 * @Version 1.0
 **/
@Getter
@Setter

@ToString
public class ResponseResult {
    private boolean success;//响应状态码
    private String code;//自定义消息编码
    private String message;//自定义的消息内容
    private Object data;//分页需要的字段，总的字段
    private String stackTrace;//异常堆栈信息
    private Integer total;//业务数据
}

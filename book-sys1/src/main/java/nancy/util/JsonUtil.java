package nancy.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import nancy.exception.SysException;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;

/**
 * @ClassName JsonUtil
 * @Description TODO
 * @Author nancy
 * @Date 2020/12/27 16:15
 * @Version 1.0
 **/
public class JsonUtil {

    private static ObjectMapper MAPPER;
    static {
        MAPPER = new ObjectMapper();
        MAPPER.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }

    //读取输入流的json数据，反序列化为对象
    public static <T> T read(InputStream is, Class<T> clazz) {
        try {
            return MAPPER.readValue(is,clazz);
        } catch (IOException e) {
            throw new SysException("003",e,"http请求处理解析json数据出错");
        }
    }

    //序列化为json字符串
    public static String write(Object o) {
        try {
            return MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(o);
        } catch (JsonProcessingException e) {
            throw new SysException("004",e, "返回json序列化对象出错");
        }
    }

}

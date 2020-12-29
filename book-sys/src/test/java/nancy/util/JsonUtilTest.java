package nancy.util;

import nancy.pojo.ResponseResult;
import org.junit.Assert;
import org.junit.Test;

import java.io.InputStream;

/**
 * @ClassName JsonUtilTest
 * @Description TODO
 * @Author nancy
 * @Date 2020/12/27 16:35
 * @Version 1.0
 **/
public class JsonUtilTest {
//    @Test
//    public void test1() {
//        InputStream in = getClass().getClassLoader().getResourceAsStream("jsonutil.json");
//        ResponseResult read = JsonUtil.read(in, ResponseResult.class);
//        System.out.println(read);
//    }

    @Test
    public void test() {
        ResponseResult res = new ResponseResult();
        res.setCode("001");
        res.setMessage("你好啊");
        res.setSuccess(true);
        res.setTotal(8);
        System.out.println(res);
        String write = JsonUtil.write(res);
        System.out.println(write);
        Assert.assertNotNull(write);

    }
}

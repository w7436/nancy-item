package nancy.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * @ClassName DButilTest
 * @Description TODO
 * @Author nancy
 * @Date 2020/12/27 16:31
 * @Version 1.0
 **/
public class DButilTest {

    @Test
    public void test() {
        Assert.assertNotNull(DButil.getConnetion());
    }
}

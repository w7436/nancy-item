package nancy.mapper;

import nancy.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @ClassName test
 * @Description TODO
 * @Author nancy
 * @Date 2020/11/8 20:43
 * @Version 1.0
 **/


@Mapper
public interface Test {
    User query(Integer i);
}

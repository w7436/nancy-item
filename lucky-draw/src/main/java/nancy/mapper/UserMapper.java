package nancy.mapper;

import nancy.base.BaseMapper;
import nancy.model.User;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserMapper extends BaseMapper<User> {
    User login(User user);
}
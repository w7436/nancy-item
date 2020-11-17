package nancy.mapper;

import nancy.base.BaseMapper;
import nancy.model.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper extends BaseMapper<Member> {
    void deleteByUserId(Integer id);
}
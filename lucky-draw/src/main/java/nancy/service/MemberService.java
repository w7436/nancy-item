package nancy.service;

import nancy.mapper.MemberMapper;
import nancy.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName MemberService
 * @Description TODO
 * @Author nancy
 * @Date 2020/11/10 17:24
 * @Version 1.0
 **/

@Service
public class MemberService {
    @Autowired
    MemberMapper memberMapper;

    @Transactional
    public List<Member> query(Member member) {

        return memberMapper.selectByCondition(member);
    }

    @Transactional
    public void add(Member member) {
        memberMapper.insert(member);
    }

    @Transactional
    public void update(Member member) {
        memberMapper.updateByPrimaryKeySelective(member);
    }

    @Transactional
    public void delete(Integer id) {
        memberMapper.deleteByPrimaryKey(id);
    }


    public void deleteByUserId(Integer id) {
        memberMapper.deleteByUserId(id);
    }
}

package nancy.service;

import nancy.exception.BusinessException;
import nancy.mapper.SettingMapper;
import nancy.model.Award;
import nancy.model.Member;
import nancy.model.Setting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName SettingService
 * @Description TODO
 * @Author nancy
 * @Date 2020/11/10 17:26
 * @Version 1.0
 **/

@Service
public class SettingService {
    @Autowired
    private SettingMapper settingMapper;


    @Autowired
    private MemberService memberService;
    @Autowired
    private AwardService awardService;

    public Setting query(Integer id) {
        Setting query = new Setting();
        query.setUserId(id);
        //注册用户需要生成setting信息，是一对一，如果没有生成，则业务有问题
        Setting setting = settingMapper.selectOne(query);
        if (setting == null) {
            throw new BusinessException("SET001","用户设置信息出错");
        }
        //查询将品列表，人员列表，设置到属性中
        else {
            Award award = new Award();
            award.setSettingId(setting.getId());
            List<Award> awards = awardService.query(award);
            setting.setAwards(awards);

            Member member = new Member();
            member.setUserId(id);
            List<Member> members = memberService.query(member);
            setting.setMembers(members);



            return setting;
        }
    }

    @Transactional
    public void add(Setting setting) {
        settingMapper.insertSelective(setting);
    }

    //Spring事务设置：默认的传播方式为Required,当前没有事务，如果没有就创建，有就加入
    @Transactional
    public void update(Integer id, Integer batchNumber) {
        //1、可以使用mapper已经提供的单表操作方法，使用userid查询setting信息，在进行修改
        //2、自己写sql，进行实现
        int num = settingMapper.updateByUserId(id, batchNumber);
    }


    @Transactional
    public void deleteByUserId(Integer id) {
        settingMapper.deleteByUserId(id);
    }
}

package nancy.service;

import nancy.mapper.AwardMapper;
import nancy.model.Award;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName AwardService
 * @Description TODO
 * @Author nancy
 * @Date 2020/11/10 17:19
 * @Version 1.0
 **/

@Service
public class AwardService {
    @Autowired
    AwardMapper awardMapper;

    public List<Award> query(Award award) {
        List<Award> awards = awardMapper.query(award);
        return awards;

    }

    @Transactional
    public void add(Award award) {
        awardMapper.insertSelective(award);
    }

    @Transactional
    public void update(Award award) {
        awardMapper.updateByPrimaryKeySelective(award);
    }

    public void delete(Integer id) {
        awardMapper.deleteByPrimaryKey(id);
    }
}

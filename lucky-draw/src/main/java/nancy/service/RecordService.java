package nancy.service;

import nancy.mapper.RecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import nancy.model.Record;
import java.util.List;

/**
 * @ClassName RecordService
 * @Description TODO
 * @Author nancy
 * @Date 2020/11/10 17:25
 * @Version 1.0
 **/

@Service
public class RecordService {
    @Autowired
    RecordMapper recordMapper;

    @Transactional
    public void add(Integer awardId, List<Integer> memberIds) {
        /**
         *批量更新：
         *1、循环遍历for
         *2、mybatis批量操作
         * 提供mapper自定义的方法：两个参数注意使用@param，List遍历参考deleteByIds生成的方法
         * xml中insert ... select ...
         */


        for (Integer memberId : memberIds) {
            Record record = new Record();
            record.setMemberId(memberId);
            record.setAwardId(awardId);
            recordMapper.insertSelective(record);
        }

    }

    @Transactional
    public void deleteByMemberId(Integer id) {
        Record r = new Record();
        r.setMemberId(id);
        recordMapper.deleteByCondition(r);
    }

    @Transactional
    public void deleteByAwardId(Integer id) {
        Record r = new Record();
        r.setAwardId(id);
        recordMapper.deleteByCondition(r);
    }

    @Transactional
    public void deleteBySettingId(Integer id) {
        recordMapper.deleteByPrimaryKey(id);
    }
}

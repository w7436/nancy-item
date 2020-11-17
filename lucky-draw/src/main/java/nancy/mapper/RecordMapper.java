package nancy.mapper;

import nancy.base.BaseMapper;
import nancy.model.Record;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RecordMapper extends BaseMapper<Record> {
    void deleteByCondition(Record r);
}
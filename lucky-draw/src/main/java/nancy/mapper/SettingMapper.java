package nancy.mapper;

import nancy.base.BaseMapper;
import nancy.model.Setting;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SettingMapper extends BaseMapper<Setting> {

    //mybatis传入参数存在多个的时候，需要使用@param并指定名称，xml中引用@param中的值为变量值
    int updateByUserId(@Param("userId") Integer id, @Param("batchNumber") Integer batchNumber);

    void deleteByUserId(Integer id);
}
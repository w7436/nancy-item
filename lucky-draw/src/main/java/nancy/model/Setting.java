package nancy.model;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import nancy.base.BaseEntity;

/**
 * 抽奖设置
 */
@Getter
@Setter
@ToString
public class Setting extends BaseEntity {
    
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 每次抽奖人数
     */
    private Integer batchNumber;

    /**
     * 创建时间
     */
    private Date createTime;

    //查询设置接口需要的字段
    private User user;
    private List<Award> awards;//还需哟啊添加
    private List<Member> members;
}
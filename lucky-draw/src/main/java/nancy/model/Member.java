package nancy.model;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import nancy.base.BaseEntity;

/**
 * 抽奖人员
 */
@Getter
@Setter
@ToString
public class Member extends BaseEntity {
    
    private Integer id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 工号
     */
    private String no;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 创建时间
     */
    private Date createTime;
}
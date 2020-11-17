package nancy.controller;

import nancy.model.Setting;
import nancy.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @ClassName RecordController
 * @Description TODO
 * @Author nancy
 * @Date 2020/11/10 17:18
 * @Version 1.0
 **/

@RestController
@RequestMapping("/record")
public class RecordController {
    @Autowired
    private RecordService recordService;

    @PostMapping("/add/{awardId}")
    public Object add(@PathVariable("awardId") Integer awardId, @RequestBody List<Integer> memberIds) {
        recordService.add(awardId,memberIds);
        return null;
    }

    @GetMapping("/delete/member")
    public Object deleteByMemberId(Integer id) {//一个人只能抽一个奖，倘若可以抽多个奖，通过memberid+awardid进行删除
        recordService.deleteByMemberId(id);
        return null;
    }

    @GetMapping("/delete/award")
    public Object deleteByAwardId(Integer id) {
        recordService.deleteByAwardId(id);
        return null;
    }

    //需要实现：根据setting_id删除中奖纪录
    @GetMapping("/delete/setting")
    public Object deleteBySettingId(HttpSession httpSession) {
        Setting setting = (Setting) httpSession.getAttribute("setting");
        recordService.deleteBySettingId(setting.getId());
        return null;
    }


}

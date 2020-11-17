package nancy.controller;

import nancy.model.Award;
import nancy.model.User;
import nancy.service.AwardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @ClassName AwardController
 * @Description TODO
 * @Author nancy
 * @Date 2020/11/10 17:17
 * @Version 1.0
 **/
@RestController
@RequestMapping("/award")
public class AwardController {
    @Autowired
    private AwardService awardService;

    @PostMapping("/add")
    public Object add(@RequestBody Award award,HttpSession session) { //插入语句中不带id
        User user = (User) session.getAttribute("user");
        award.setSettingId(user.getSettingId());
        awardService.add(award);
        return award.getId();//数据库插入时，自动设置id为自增，返回前端，否则有bug
    }

    @PostMapping("/update")
    public Object update(@RequestBody Award award) {//根据id进行修改
        awardService.update(award);
        return null;
    }

    //PathVariable中的值绑定路径的变量，如果@PathVariable没有值，绑定为变量名
    @GetMapping("/delete/{id}")
    public Object delete(@PathVariable("id") Integer id) {
        awardService.delete(id);
        return null;
    }



}

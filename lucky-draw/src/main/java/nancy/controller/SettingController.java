package nancy.controller;

import nancy.model.Setting;
import nancy.model.User;
import nancy.service.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @ClassName SettingCOntroller
 * @Description TODO
 * @Author nancy
 * @Date 2020/11/10 17:18
 * @Version 1.0
 **/

@RestController
@RequestMapping("/setting")
public class SettingController {
    @Autowired
    private SettingService settingService;

    @GetMapping("/query")
    //Cookie中携带tomcat的session实现，也就是请求头中包含Cookie：JSESSION=xxx
    //比如说服务器重启，或没有登录的时候但是携带的JSESSION信息，获取的HttpSession不为空
    //此时要进行判断设置key，value


    //如果不实现拦截器的话，需要保证上面的逻辑，如果实现拦截器，一定是登录之后才可以进行访问
    public Object query(HttpSession httpSession) {
        //登录以后才可以访问的接口，从session会话中拿取
        User user = (User) httpSession.getAttribute("user");

        Setting setting = settingService.query(user.getId());
        setting.setUser(user);
        return setting;
    }

    @GetMapping("/update")
    public Object update(Integer batchNumber, HttpSession session) {
        User user = (User) session.getAttribute("user");
        settingService.update(user.getId(),batchNumber);
        return null;
    }
}

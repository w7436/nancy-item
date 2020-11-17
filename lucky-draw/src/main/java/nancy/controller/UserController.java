package nancy.controller;

import nancy.model.Setting;
import nancy.model.User;
import nancy.service.MemberService;
import nancy.service.SettingService;
import nancy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @ClassName UserController
 * @Description TODO
 * @Author nancy
 * @Date 2020/11/9 21:04
 * @Version 1.0
 **/

/**
 * @RestController是Controller和ResponseBody的结合
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Value("${user.head.local-path")
    private String localPath;

    @Value("${user.head.remote-path")
    private String remotePath;
    @Value("${user.head.filename")
    private String fileName;

    @Autowired
    private UserService userService;
    @Autowired
    private SettingService settingService;
    @Autowired
    private MemberService memberService;

    @PostMapping("/login")
    // @RequestBody主要用来接收前端传递给后端的json字符串中的数据的(请求体中的数据的)
    public Object login(@RequestBody User user, HttpServletRequest req) {
        //exist为数据库查询的用户，如果查询不到就null
        User exist = userService.login(user);
        //提供一个方法，根据用户id获取settingid
        Setting setting = settingService.query(exist.getId());
        exist.setSettingId(setting.getId());
        HttpSession session = req.getSession();
        session.setAttribute("user",exist);
        session.setAttribute("setting",setting);
        return null;
    }

    @PostMapping("/register")
    /**
     * 文件上传
     * RequestPart：这个注解用在multipart/form-data表单提交的请求方法上，支持请求的方式是MultipartFile,这个请求通过http传输
     * RequestParam：也支持multipart/form-data请求
     * 不同点:当请求的参数类型不再是String的时候，后者适用于name-value String类型的请求域，前者适用于复杂的请求域（json,xml)
     * 前者会生成临时文件夹，而后者不会因而后者效率更高
     */
    public Object register (User user,
                            //上传文件：1、保存在本地文件夹（web服务器需要加载到），
                            @RequestPart(value = "headFile", required = false)MultipartFile file) {
        userService.register(user, file);
        return null;

    }

//    //注销功能：get api/user/logout
//    @GetMapping("/logout")
//    public Object logout(HttpSession session) {
//        System.out.println("删除");
//        User user = (User) session.getAttribute("user");
//
//        memberService.deleteByUserId(user.getId());//删除member中的user_id
//        settingService.deleteByUserId(user.getId());//删除setting中user_id
//        userService.delete(user);
//
//
//        return null;
//    }

}

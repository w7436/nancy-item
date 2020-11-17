package nancy.controller;

import nancy.model.Member;
import nancy.model.User;
import nancy.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @ClassName MemberController
 * @Description TODO
 * @Author nancy
 * @Date 2020/11/10 17:17
 * @Version 1.0
 **/

@RestController
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @PostMapping("/add")
    public Object add(@RequestBody Member member, HttpSession session) {
        User user = (User) session.getAttribute("user");
        member.setUserId(user.getId());
        memberService.add(member);
        return member.getId();
    }

    @PostMapping("/update")
    public Object update(@RequestBody Member member) {
        memberService.update(member);
        return null;
    }

    @GetMapping("/delete/{id}")
    public Object delete(@PathVariable("id") Integer id) {
        memberService.delete(id);
        return null;
    }

}

/**
 * @ClassName testController
 * @Description TODO
 * @Author nancy
 * @Date 2020/11/5 20:57
 * @Version 1.0
 **/
package nancy.controller;

import nancy.mapper.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class testController{

    @Autowired
    private Test t1;

    @GetMapping("/query/{id}")

    public Object query(@PathVariable("id") Integer id) {
        return t1.query(id);
    }
}
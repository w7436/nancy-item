package nancy.service;

import nancy.exception.ClientException;
import nancy.exception.SystemException;
import nancy.mapper.UserMapper;
import nancy.model.Setting;
import nancy.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @ClassName UserService
 * @Description TODO
 * @Author nancy
 * @Date 2020/11/9 21:08
 * @Version 1.0
 **/
@Service
public class UserService {

    @Value("${user.head.local-path}")
    private String localPath;

    @Value("${user.head.remote-path}")
    private String remotePath;
    @Value("${user.head.filename}")
    private String fileName;

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper mapper;

    @Autowired
    private SettingService settingService;

    public  User query(Integer id) {
        User user = new User();
        user.setId(id);
        return mapper.selectOne(user);
    }


    public User login(User user) {
//        User exsit = mapper.login(user);
//        if (exsit == null) {
//            throw new ClientException("USER001","用户名密码校验失败");
//        }

        User query = new User();
        query.setUsername(user.getUsername());
        User exsit = mapper.selectOne(query);//仅仅根据用户名进行查询
        if (exsit == null) {
            throw new ClientException("USER001","用户名不存在");
        }
        if (!exsit.getPassword().equals(user.getPassword())) {
            throw new ClientException("USER002","用户名密码校验失败");
        }
        return exsit;



    }



    //默认事务的配置为Spring事务的传播特性=required，事务的隔离级别为数据库的默认事务隔离级别
    @Transactional
    public void register(User user, MultipartFile file){

        User query = new User();
        query.setUsername(user.getUsername());
        User exist = mapper.selectOne(query);
        if (exist != null) {
            throw new ClientException("USER003","用户已存在");
        }

        //保存注册用户信息
        String path = "/"+user.getUsername()+"/" + fileName;
        user.setHead(remotePath+path);
        mapper.insertSelective(user);//插入成功之后，自增主键通过mybatis的<selectKey>返回对象

        //注册用户时候，完成初始化操作，创建setting数据
        Setting setting = new Setting();
        setting.setUserId(user.getId());
        setting.setBatchNumber(8);
        settingService.add(setting);

        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        try {
            try {
                File dir = new File(localPath+"/"+user.getUsername());
                dir.mkdir();
                fos = new FileOutputStream(localPath+path);
                bos = new BufferedOutputStream(fos);
                bos.write(file.getBytes());
                bos.flush();
            } finally {
                if (bos != null) bos.close();
                if (fos != null) fos.close();
            }
        } catch (IOException e) {
            //打印捕获的异常，抛出自定义异常，统一异常拦截器进行自定义异常打印
//            e.printStackTrace();
//            throw new SystemException("USER004","用户注册失败，用户头像上传错误");

            throw new SystemException("USER004","用户注册失败，用户头像上传错误",e);
        }

    }

    public void delete(User user) {
        mapper.deleteByPrimaryKey(user.getId());
    }
}

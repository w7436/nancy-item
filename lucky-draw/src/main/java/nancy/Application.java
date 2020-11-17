package nancy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("nancy.mapper") //指定要变成实现类的接口所在的包，然后包下面的所有接口在编译之后都会生成相应的实现类
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

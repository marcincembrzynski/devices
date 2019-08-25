package cembrzynski;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("cembrzynski")
@SpringBootApplication
public class DevicesApp {
    public static void main(String[] args) {
        SpringApplication.run(DevicesApp.class, args);
    }
}

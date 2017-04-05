package by.jackson.letshavealunch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SuppressWarnings("CheckStyle")
@SpringBootApplication
@EnableTransactionManagement
public class LetsHaveALunch {

    public static void main(String[] args) {
        SpringApplication.run(LetsHaveALunch.class, args);
    }
}

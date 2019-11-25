package cn.lhz.sso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication(scanBasePackages = "cn.lhz")
public class EcsSsoApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcsSsoApplication.class, args);
	}

}

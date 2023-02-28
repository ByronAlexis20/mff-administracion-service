package mff.administracion;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestTemplate;

@EnableAsync
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class MffAdministracionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MffAdministracionServiceApplication.class, args);
	}
	
	@Bean
	public RestTemplate registrarRestTemplate() {
		return new RestTemplate();
	}
	
	@PostConstruct
	void started() {
		TimeZone.setDefault(TimeZone.getTimeZone("GMT-5"));
	}

}

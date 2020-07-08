package jp.co.sample.emp_management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication
public class ExEmpManageAnswerApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(ExEmpManageAnswerApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(ExEmpManageAnswerApplication.class);
	}
}

package sample.multimodule.application2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages = "sample.*", exclude = {
		org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration.class,
		ManagementWebSecurityAutoConfiguration.class })
public class SampleApplication2 extends SpringBootServletInitializer {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SampleApplication2.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(applicationClass);
	}

	private static Class<SampleApplication2> applicationClass = SampleApplication2.class;
	
	
}

package hr.in2.postenipoduzetnikevents;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@SpringBootApplication
public class In2PosteniPoduzetnikEventsApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(In2PosteniPoduzetnikEventsApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(applicationClass);
	}

	private static Class<In2PosteniPoduzetnikEventsApplication> applicationClass = In2PosteniPoduzetnikEventsApplication.class;

}

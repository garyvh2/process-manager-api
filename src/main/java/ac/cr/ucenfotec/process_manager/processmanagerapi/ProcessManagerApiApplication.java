package ac.cr.ucenfotec.process_manager.processmanagerapi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.rest.RepositoryRestMvcAutoConfiguration;



@SpringBootApplication
@EnableAutoConfiguration(exclude = RepositoryRestMvcAutoConfiguration.class)
public class ProcessManagerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProcessManagerApiApplication.class, args);
	}
}

package ac.cr.ucenfotec.process_manager.processmanagerapi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;



@SpringBootApplication
@ComponentScan("ac.cr.ucenfotec.process_manager.repositories")
public class ProcessManagerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProcessManagerApiApplication.class, args);
	}
}

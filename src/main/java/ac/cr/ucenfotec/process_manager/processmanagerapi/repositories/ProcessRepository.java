package ac.cr.ucenfotec.process_manager.processmanagerapi.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import ac.cr.ucenfotec.process_manager.entities.Process;
import ac.cr.ucenfotec.process_manager.entities.UserType;;

public interface ProcessRepository extends MongoRepository<Process, String>{
	
	Optional<Process> findOneByNumeroTramite(int NumeroTramite);
	
	Process deleteByNumeroTramite (int NumeroTramite);
	
	//@Query(value = "{'tasks.0.userGroup' : ?0 }")
	@Query(value = "{'tasks': { '$elemMatch': { 'userGroup' : ?0 }}}")
	public List<Process> findByFirstTask(UserType userType);
	
}

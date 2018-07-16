package ac.cr.ucenfotec.process_manager.processmanagerapi.repositories;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import ac.cr.ucenfotec.process_manager.entities.ProcessTemplate;
import ac.cr.ucenfotec.process_manager.entities.UserType;;

public interface ProcessRepository extends MongoRepository<ProcessTemplate, String>{
	
	Optional<ProcessTemplate> findOneByNumeroTramite(int NumeroTramite);
	
	ProcessTemplate deleteByNumeroTramite (int NumeroTramite);
	
	@Query(value = "{'tasks.0.userGroup._id' : ?0 }")
	public List<ProcessTemplate> findByFirstTask(ObjectId userType);
	
}

package ac.cr.ucenfotec.process_manager.processmanagerapi.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import ac.cr.ucenfotec.process_manager.entities.UserType;
import ac.cr.ucenfotec.process_manager.entities.ProcessInstance;
import ac.cr.ucenfotec.process_manager.enums.Status;

public interface ProcessInstanceRepository extends MongoRepository<ProcessInstance, String>{
	
	@Query(value = "{'tasks': { '$elemMatch': { 'userGroup' : ?0 , 'taskStatus': ?1 }}}")
	public List<ProcessInstance> findInstanceTask(UserType userGroup, Status status);

}

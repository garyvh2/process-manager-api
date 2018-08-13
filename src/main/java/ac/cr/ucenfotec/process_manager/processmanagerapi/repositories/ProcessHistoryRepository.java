package ac.cr.ucenfotec.process_manager.processmanagerapi.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import ac.cr.ucenfotec.process_manager.entities.ProcessHistory;
;

public interface ProcessHistoryRepository extends MongoRepository<ProcessHistory, String>{
	
}

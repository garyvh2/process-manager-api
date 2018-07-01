package ac.cr.ucenfotec.process_manager.processmanagerapi.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import ac.cr.ucenfotec.process_manager.entities.Process;;

public interface ProcessRepository extends MongoRepository<Process, String>{
	
	Optional<Process> findOneByNumeroTramite(int NumeroTramite);
	
	Process deleteByNumeroTramite (int NumeroTramite);
	
}

package ac.cr.ucenfotec.process_manager.processmanagerapi.repositories;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ac.cr.ucenfotec.process_manager.entities.UserType;

@Repository
public interface UserTypeRepository extends MongoRepository<UserType, String> {
	
	List<UserType> findByUserTypeName(@Param("userTypeName") String userTypeName);
	
}

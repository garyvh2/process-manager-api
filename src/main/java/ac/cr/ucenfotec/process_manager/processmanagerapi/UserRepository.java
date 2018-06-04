package ac.cr.ucenfotec.process_manager.processmanagerapi;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List; 
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ac.cr.ucenfotec.process_manager.entities.User;

@RepositoryRestResource(collectionResourceRel = "user", path = "user")
public interface UserRepository extends MongoRepository<User, String> {
	List<User> findByUserGroupUserTypeName(String userTypeName);
}

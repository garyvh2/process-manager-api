package ac.cr.ucenfotec.process_manager.processmanagerapi.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List; 

import org.springframework.stereotype.Repository;

import ac.cr.ucenfotec.process_manager.entities.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
	List<User> findByUserGroupUserTypeName(String userTypeName);

}

package ac.cr.ucenfotec.process_manager.processmanagerapi;
import java.util.List;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ac.cr.ucenfotec.process_manager.entities.UserType;

@RepositoryRestResource(collectionResourceRel = "usertype", path = "usertype")
public interface UserTypeRepository extends MongoRepository<UserType, String>{
	List<UserType> findByUserTypeName(@Param("userTypeName") String userTypeName);
}

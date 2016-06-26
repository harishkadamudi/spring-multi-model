package sample.multimodule.repository;

import org.springframework.data.repository.CrudRepository;
import sample.multimodule.domain.entity.UserDetails;

public interface UserRepository extends CrudRepository<UserDetails, Long> {

}

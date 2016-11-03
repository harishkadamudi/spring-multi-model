package sample.multimodule.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import sample.multimodule.domain.entity.Status;

@Repository
public interface StatusRepository extends CrudRepository<Status, Long> {

	Status findByuserId(long userId);
/*
	@Modifying
	@Transactional
	@Query("update Status s set s.status = ?1 where s.userId = ?2")
	int setStatusFor(String status, long userId);*/
}

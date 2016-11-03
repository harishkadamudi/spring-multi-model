package sample.multimodule.repository;

import org.springframework.data.repository.CrudRepository;

import sample.multimodule.domain.entity.MessageIn;

public interface MessageInRepository extends CrudRepository<MessageIn, Long> {

}

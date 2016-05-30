package sample.multimodule.repository;

import org.springframework.data.repository.CrudRepository;

import sample.multimodule.domain.entity.Message;

public interface MessageRepository extends CrudRepository<Message, Long> {

}

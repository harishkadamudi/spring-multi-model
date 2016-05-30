package sample.multimodule.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import sample.multimodule.domain.entity.Account;

@Repository
public interface AccountRepositoryTest extends CrudRepository<Account, Long> {

  Account findByNumber(String number);

}

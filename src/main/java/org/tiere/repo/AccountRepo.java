package org.tiere.repo;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.tiere.entity.AccountEntity;

@ApplicationScoped
public class AccountRepo implements PanacheRepository<AccountEntity> {

    public AccountEntity findByEmail(String email) {
        return find("email", email).firstResult();
    }

}

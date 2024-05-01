package org.tiere.repo;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.tiere.entity.AccountEntity;

@ApplicationScoped
public class AccountRepo implements PanacheRepositoryBase<AccountEntity, Integer> {

    public AccountEntity findByEmail(String email) {
        return find("email", email).firstResult();
    }

}

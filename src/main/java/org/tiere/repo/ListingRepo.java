package org.tiere.repo;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.springframework.data.jpa.repository.JpaRepository;
import org.tiere.entity.AccountEntity;
import org.tiere.entity.ListingEntity;

import java.util.List;

@ApplicationScoped
public class ListingRepo implements PanacheRepositoryBase<ListingEntity, Integer> {

    public List<ListingEntity> findByAccount(AccountEntity account) {
        return find("account", account).list();
    }

}

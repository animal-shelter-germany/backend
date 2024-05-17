package org.tiere.repo;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import org.tiere.entity.AccountEntity;
import org.tiere.entity.MessageEntity;

import java.util.List;

@ApplicationScoped
public class MessageRepo implements PanacheRepositoryBase<MessageEntity, Integer> {

    public List<MessageEntity> findAllByUser(AccountEntity account) {
        return find("sender = :account OR receiver = :account", Parameters.with("account", account)).list();
    }

    public List<MessageEntity> findAllByUsers(AccountEntity account, AccountEntity opponent) {
        return find("(sender = :account AND receiver = :opponent) OR (sender = :opponent AND receiver = :account)", Parameters.with("account", account).and("opponent", opponent)).list();
    }
}

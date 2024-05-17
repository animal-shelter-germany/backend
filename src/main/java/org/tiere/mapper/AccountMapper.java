package org.tiere.mapper;

import org.tiere.dto.Account;
import org.tiere.dto.Animal;
import org.tiere.entity.AccountEntity;
import org.tiere.entity.AnimalEntity;

import java.util.List;

public class AccountMapper {

    public static List<Account> map(List<AccountEntity> accounts) {
        return accounts.stream().map(AccountMapper::map).toList();
    }

    public static Account map(AccountEntity account) {
        return new Account(account.getId(), account.getEmail(), account.getFirstName(), account.getLastName());
    }

}

package org.tiere.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.tiere.dto.Account;
import org.tiere.mapper.AccountMapper;
import org.tiere.repo.AccountRepo;

@ApplicationScoped
public class AccountService {

    private final JsonWebToken jsonWebToken;
    private final AccountRepo accountRepo;

    @Inject
    public AccountService(JsonWebToken jsonWebToken, AccountRepo accountRepo) {
        this.jsonWebToken = jsonWebToken;
        this.accountRepo = accountRepo;
    }

    public Account find() {
        return AccountMapper.map(accountRepo.findById(Long.parseLong(jsonWebToken.getName())));
    }

}

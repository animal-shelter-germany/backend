package org.tiere.service;

import io.quarkus.elytron.security.common.BcryptUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.tiere.dto.Account;
import org.tiere.dto.AccountUpdate;
import org.tiere.entity.AccountEntity;
import org.tiere.mapper.AccountMapper;
import org.tiere.repo.AccountRepo;

@ApplicationScoped
public class AccountService {

    private final JsonWebToken jsonWebToken;
    private final AccountRepo accountRepo;
    private final AuthenticationService authenticationService;

    @Inject
    public AccountService(JsonWebToken jsonWebToken, AccountRepo accountRepo, AuthenticationService authenticationService) {
        this.jsonWebToken = jsonWebToken;
        this.accountRepo = accountRepo;
        this.authenticationService = authenticationService;
    }

    public Account find() {
        return AccountMapper.map(accountRepo.findById(Integer.parseInt(jsonWebToken.getName())));
    }

    @Transactional
    public void setPassword(String password) {
        AccountEntity account = authenticationService.requireAccount();
        account.setPassword(BcryptUtil.bcryptHash(password));
        accountRepo.persist(account);
    }

    @Transactional
    public void update(AccountUpdate account) {
        AccountEntity accountEntity = authenticationService.requireAccount();
        accountEntity.setEmail(account.email());
        accountEntity.setFirstName(account.firstName());
        accountEntity.setLastName(account.lastName());
        accountEntity.persist();
    }
}

package org.tiere.service;

import io.quarkus.security.UnauthorizedException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.tiere.entity.AccountEntity;
import org.tiere.repo.AccountRepo;

@ApplicationScoped
public class AuthenticationService {

    private final JsonWebToken jsonWebToken;
    private final AccountRepo accountRepo;

    @Inject
    public AuthenticationService(JsonWebToken jsonWebToken, AccountRepo accountRepo) {
        this.jsonWebToken = jsonWebToken;
        this.accountRepo = accountRepo;
    }

    public AccountEntity requireAccount() {
        return accountRepo.findByIdOptional(Long.parseLong(jsonWebToken.getName())).orElseThrow(() -> new UnauthorizedException());
    }

}

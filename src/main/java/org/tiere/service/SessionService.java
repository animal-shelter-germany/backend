package org.tiere.service;

import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.security.UnauthorizedException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.tiere.dto.Session;
import org.tiere.entity.AccountEntity;
import org.tiere.repo.AccountRepo;
import org.tiere.util.JwtUtils;

@ApplicationScoped
public class SessionService {

    private final JwtUtils jwtUtils;
    private final AccountRepo accountRepo;

    @Inject
    public SessionService(JwtUtils jwtUtils, AccountRepo accountRepo) {
        this.jwtUtils = jwtUtils;
        this.accountRepo = accountRepo;
    }

    public String get(Session session) {
        AccountEntity accountEntity = accountRepo.findByEmail(session.email());
        if(BcryptUtil.matches(session.password(), accountEntity.getPassword())) {
            return jwtUtils.generate(accountEntity);
        }
        throw new UnauthorizedException();
    }

}

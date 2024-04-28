package org.tiere.util;

import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;
import org.tiere.entity.AccountEntity;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@ApplicationScoped
public class JwtUtils {

    public String generate(AccountEntity user) {
        LocalDateTime expiresAt = LocalDateTime.now().plusMonths(1);
        return Jwt.upn(String.valueOf(user.id)).expiresAt(expiresAt.toInstant(ZoneOffset.UTC)).sign();
    }

}

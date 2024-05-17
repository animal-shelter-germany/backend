package org.tiere.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.tiere.dto.Message;
import org.tiere.entity.AccountEntity;
import org.tiere.mapper.MessageMapper;
import org.tiere.repo.AccountRepo;
import org.tiere.repo.MessageRepo;

import java.util.List;

@ApplicationScoped
public class MessageService {

    private final AuthenticationService authenticationService;
    private final MessageRepo messageRepo;
    private final AccountRepo accountRepo;

    @Inject
    public MessageService(AuthenticationService authenticationService, MessageRepo messageRepo, AccountRepo accountRepo) {
        this.authenticationService = authenticationService;
        this.messageRepo = messageRepo;
        this.accountRepo = accountRepo;
    }

    public List<Message> findAllByUser(int userId) {
        AccountEntity opponent = accountRepo.findById(userId);
        return MessageMapper.map(messageRepo.findAllByUsers(authenticationService.requireAccount(), opponent));
    }

}

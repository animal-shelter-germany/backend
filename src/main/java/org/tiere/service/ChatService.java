package org.tiere.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.tiere.dto.Chat;
import org.tiere.entity.AccountEntity;
import org.tiere.mapper.AccountMapper;
import org.tiere.repo.MessageRepo;

import java.util.LinkedList;
import java.util.List;

@ApplicationScoped
public class ChatService {

    private final AuthenticationService authenticationService;
    private final MessageRepo messageRepo;

    @Inject
    public ChatService(AuthenticationService authenticationService, MessageRepo messageRepo) {
        this.authenticationService = authenticationService;
        this.messageRepo = messageRepo;
    }

    public List<Chat> findAllByUser() {
        AccountEntity user = authenticationService.requireAccount();
        List<Chat> result = new LinkedList<>();
        messageRepo.findAllByUser(user).forEach(message -> {
            AccountEntity sender = message.getSender();
            AccountEntity receiver = message.getReceiver();
            addChat(result, user, sender);
            addChat(result, user, receiver);
        });
        return result;
    }

    private void addChat(List<Chat> chats, AccountEntity user, AccountEntity opponent) {
        if(!opponent.equals(user) && notContainsChat(chats, opponent)) {
            chats.add(new Chat(AccountMapper.map(opponent), "Testmessage"));
        }
    }

    private boolean notContainsChat(List<Chat> chats, AccountEntity account) {
        for(Chat chat : chats) {
            if(chat.user() != null && account != null && chat.user().id().equals(account.getId())) {
                return false;
            }
        }
        return true;
    }

}

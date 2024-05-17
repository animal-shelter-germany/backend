package org.tiere.mapper;

import org.tiere.dto.Account;
import org.tiere.dto.Message;
import org.tiere.entity.MessageEntity;

import java.util.List;

public class MessageMapper {

    public static List<Message> map(List<MessageEntity> messages) {
        return messages.stream().map(MessageMapper::map).toList();
    }

    public static Message map(MessageEntity message) {
        return new Message(AccountMapper.map(message.getSender()), message.getMessage(), message.getSentAt());
    }

}

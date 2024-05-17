package org.tiere.dto;

import java.time.LocalDateTime;

public record Message(Account sender, String text, LocalDateTime sentAt) {

}

package com.furnifinders.backend.Helper;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

import lombok.Getter;
import lombok.Setter;

@ApplicationScope
@Getter
@Setter
@Component
public class MessageHelper {
    private String message;

    public MessageHelper(String message) {
        this.message = message;
    }
}

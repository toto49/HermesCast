package com.hermescast.core.controller;

import com.hermescast.core.model.TwitchMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ActionController {

    @MessageMapping("/select-question")
    @SendTo("/topic/caster-screen")
    public TwitchMessage broadcastQuestionToCaster(TwitchMessage selectedQuestion) {
        System.out.println("[Régie] Question envoyée au Caster : " + selectedQuestion.getMessage());
        return selectedQuestion;
    }
}
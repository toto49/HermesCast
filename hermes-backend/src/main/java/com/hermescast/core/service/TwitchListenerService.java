package com.hermescast.core.service;

import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;
import com.github.twitch4j.common.enums.CommandPermission;
import com.hermescast.core.model.TwitchMessage;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;

import java.util.UUID;

@Service
public class TwitchListenerService {

    private final TwitchClient twitchClient;
    private final SimpMessagingTemplate messagingTemplate;

    public TwitchListenerService(TwitchClient twitchClient, SimpMessagingTemplate messagingTemplate) {
        this.twitchClient = twitchClient;
        this.messagingTemplate = messagingTemplate;
    }

    @PostConstruct
    public void registerTwitchEvents() {
        twitchClient.getEventManager().onEvent(ChannelMessageEvent.class, this::onChannelMessage);
    }

    private void onChannelMessage(ChannelMessageEvent event) {
        TwitchMessage messageClean = TwitchMessage.builder()
                .id(event.getMessageEvent().getTagValue("id").orElse(UUID.randomUUID().toString()))
                .username(event.getUser().getName())
                .message(event.getMessage())
                .color(event.getMessageEvent().getTagValue("color").orElse("#FFFFFF"))
                .isMod(event.getPermissions().contains(CommandPermission.MODERATOR))
                .isSub(event.getPermissions().contains(CommandPermission.SUBSCRIBER))
                .isVip(event.getPermissions().contains(CommandPermission.VIP))
                .isBroadcaster(event.getPermissions().contains(CommandPermission.BROADCASTER))
                .build();
        messagingTemplate.convertAndSend("/topic/chat", messageClean);

        System.out.println("[Twitch] " + messageClean.getUsername() + ": " + messageClean.getMessage());
    }
}
package com.hermescast.core.config;

import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.TwitchClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TwitchConfig {
    @Value("${twitch.channel}")
    private String TwitchChannel;
    @Bean
    public TwitchClient twitchClient() {
        TwitchClient client = TwitchClientBuilder.builder()
                .withEnableChat(true)
                .build();
        client.getChat().joinChannel(TwitchChannel);
        return client;
    }
}
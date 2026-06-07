package com.hermescast.core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TwitchMessage {
    private String id;
    private String username;
    private String message;
    private String color;
    private boolean isMod;
    private boolean isSub;
    private boolean isBroadcaster;
    private boolean isVip;
}

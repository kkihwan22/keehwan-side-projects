package com.keehwan.domain.announcement.domain;

import java.time.LocalDateTime;

public class CommonAnnouncementContent {
    private String title;
    private String content;

    private boolean fixed;
    private boolean display;
    private boolean reserved;
    private LocalDateTime displayStartDateTime;
    private LocalDateTime displayEndDateTime;
}

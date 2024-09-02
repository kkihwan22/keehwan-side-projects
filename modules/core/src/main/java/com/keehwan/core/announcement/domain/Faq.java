package com.keehwan.core.announcement.domain;

import java.util.List;

public class Faq {
    private Long id;
    private String title;
    private String content;
    private boolean display;
    private List<FaqTranslation> translations;
}

package com.keehwan.domain.announcement.domain;

import com.keehwan.share.domain.BaseCreatedAndUpdatedDateTimeWithAudit;

import java.util.List;

public class Banner extends BaseCreatedAndUpdatedDateTimeWithAudit {
    private Long id;
    private CommonAnnouncementContent content;
    private List<BannerTranslation> translations;
}

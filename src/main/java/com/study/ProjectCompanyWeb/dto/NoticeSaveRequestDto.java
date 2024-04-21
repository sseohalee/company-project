package com.study.ProjectCompanyWeb.dto;

import com.study.ProjectCompanyWeb.domain.community.Notice;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NoticeSaveRequestDto {
    private String noticeTitle;
    private String noticeContent;
    private String noticeMemberId;

    public Notice toEntity(){
        return Notice.builder()
                .noticeTitle(this.noticeTitle)
                .noticeContent(this.noticeContent)
                .noticeMemberId(this.noticeMemberId)
                .build();
    }
}

package com.study.ProjectCompanyWeb.domain.community;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name="company_notice")
@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_idx", nullable = false)
    private Long noticeIdx;
    @Column(name = "notice_title", nullable = false)
    private String noticeTitle;
    @Column(name = "notice_content", nullable = false)
    private String noticeContent;
    @Column(name = "notice_member_id", nullable = false)
    private String noticeMemberId;
    @Column(name = "notice_date", nullable = false)
    private LocalDate noticeDate = LocalDate.now();

    @Builder
    public Notice(String noticeTitle, String noticeContent, String noticeMemberId) {
        this.noticeTitle = noticeTitle;
        this.noticeContent = noticeContent;
        this.noticeMemberId = noticeMemberId;
    }

    public void update(String noticeTitle, String noticeContent, String noticeMemberId){
        this.noticeTitle = noticeTitle;
        this.noticeContent = noticeContent;
        this.noticeMemberId = noticeMemberId;
        this.noticeDate = LocalDate.now();
    }
}

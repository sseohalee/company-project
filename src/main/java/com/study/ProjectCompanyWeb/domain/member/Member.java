package com.study.ProjectCompanyWeb.domain.member;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="company_member")
@Getter
@NoArgsConstructor(access=AccessLevel.PROTECTED)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_idx", nullable = false)
    private Long memberIdx;
    @Column(name = "member_id", nullable = false)
    private String memberId;
    @Column(name = "member_pw", nullable = false)
    private String memberPw;
    @Column(name = "member_name", nullable = false)
    private String memberName;
    @Column(name = "member_email", nullable = false)
    private String memberEmail;
    @Column(name = "member_email_receive", nullable = false)
    private Long memberEmailReceive;
    @Column(name = "member_pw_question", nullable = false)
    private Long memberPwQuestion;
    @Column(name = "member_pw_answer", nullable = false)
    private String memberPwAnswer;
    @Column(name = "member_gender", nullable = false)
    private String memberGender;
    @Column(name = "member_birth_date", nullable = false)
    private LocalDate memberBirthDate;
    @Column(name = "member_join_date", nullable = false)
    private LocalDateTime memberJoinDate = LocalDateTime.now();

    @Builder
    public Member(String memberId, String memberPw, String memberName, String memberEmail, Long memberEmailReceive, Long memberPwQuestion, String memberPwAnswer, String memberGender, LocalDate memberBirthDate) {
        this.memberId = memberId;
        this.memberPw = memberPw;
        this.memberName = memberName;
        this.memberEmail = memberEmail;
        this.memberEmailReceive = memberEmailReceive;
        this.memberPwQuestion = memberPwQuestion;
        this.memberPwAnswer = memberPwAnswer;
        this.memberGender = memberGender;
        this.memberBirthDate = memberBirthDate;
    }

    public void update(String memberId, String memberPw, String memberName, String memberEmail, Long memberEmailReceive, Long memberPwQuestion, String memberPwAnswer, String memberGender, LocalDate memberBirthDate) {
        this.memberId = memberId;
        this.memberPw = memberPw;
        this.memberName = memberName;
        this.memberEmail = memberEmail;
        this.memberEmailReceive = memberEmailReceive;
        this.memberPwQuestion = memberPwQuestion;
        this.memberPwAnswer = memberPwAnswer;
        this.memberGender = memberGender;
        this.memberBirthDate = memberBirthDate;
    }
}

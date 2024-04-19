package com.study.ProjectCompanyWeb.dto;

import com.study.ProjectCompanyWeb.domain.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class MemberSaveRequestDto {
    private String memberId;
    private String memberPw;
    private String memberName;
    private String memberEmail;
    private Long memberEmailReceive;
    private Long memberPwQuestion;
    private String memberPwAnswer;
    private String memberGender;
    private LocalDate memberBirthDate;

    @Builder
    public MemberSaveRequestDto(String memberId, String memberPw, String memberName, String memberEmail, Long memberEmailReceive, Long memberPwQuestion, String memberPwAnswer, String memberGender, LocalDate memberBirthDate) {
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

    public Member toEntity(){
        return Member.builder()
                .memberId(this.memberId)
                .memberPw(this.memberPw)
                .memberName(this.memberName)
                .memberEmail(this.memberEmail)
                .memberEmailReceive(this.memberEmailReceive)
                .memberPwQuestion(this.memberPwQuestion)
                .memberPwAnswer(this.memberPwAnswer)
                .memberGender(this.memberGender)
                .memberBirthDate(this.memberBirthDate)
                .build();
    }

    @Override
    public String toString() {
        return "MemberSaveRequestDto{" +
                "memberId='" + memberId + '\'' +
                ", memberPw='" + memberPw + '\'' +
                ", memberName='" + memberName + '\'' +
                ", memberEmail='" + memberEmail + '\'' +
                ", memberEmailReceive=" + memberEmailReceive +
                ", memberPwQuestion=" + memberPwQuestion +
                ", memberPwAnswer='" + memberPwAnswer + '\'' +
                ", memberGender='" + memberGender + '\'' +
                ", memberBirthDate=" + memberBirthDate +
                '}';
    }
}

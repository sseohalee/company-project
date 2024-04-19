package com.study.ProjectCompanyWeb.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberFindPwRequestDto {
    private String memberName;
    private String memberId;
    private String memberEmail;

    @Override
    public String toString() {
        return "MemberFindPwRequestDto{" +
                "memberName='" + memberName + '\'' +
                ", memberId='" + memberId + '\'' +
                ", memberEmail='" + memberEmail + '\'' +
                '}';
    }
}

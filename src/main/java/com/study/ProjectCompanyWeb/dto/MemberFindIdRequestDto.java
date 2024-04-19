package com.study.ProjectCompanyWeb.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberFindIdRequestDto {
    private String memberName;
    private String memberEmail;

    @Override
    public String toString() {
        return "MemberFindIdRequestDto{" +
                "memberName='" + memberName + '\'' +
                ", memberEmail='" + memberEmail + '\'' +
                '}';
    }
}

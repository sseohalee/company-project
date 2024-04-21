package com.study.ProjectCompanyWeb.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class MemberLoginRequestDto {
    private String memberId;
    private String memberPw;
}

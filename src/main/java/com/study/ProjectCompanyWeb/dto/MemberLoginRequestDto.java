package com.study.ProjectCompanyWeb.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class MemberLoginRequestDto {
    private String memberId;
    @Size(min=4,max=20)
    private String memberPw;
}

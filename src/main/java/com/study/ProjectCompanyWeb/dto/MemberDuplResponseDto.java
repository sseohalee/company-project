package com.study.ProjectCompanyWeb.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberDuplResponseDto {
    private String status;
    private String message;
}

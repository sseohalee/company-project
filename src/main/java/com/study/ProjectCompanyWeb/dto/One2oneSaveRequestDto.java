package com.study.ProjectCompanyWeb.dto;

import com.study.ProjectCompanyWeb.domain.customer.One2one;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class One2oneSaveRequestDto {
    private String one2oneName;
    private String one2onePhone;
    private String one2oneEmail;
    private String one2oneAddress;
    private String one2oneDetailAddress;
    private String one2oneTitle;
    private String one2oneContent;

    public One2one toEntity(){
        return One2one.builder()
                .one2oneName(this.one2oneName)
                .one2onePhone(this.one2onePhone)
                .one2oneEmail(this.one2oneEmail)
                .one2oneAddress(this.one2oneAddress + " " +this.one2oneDetailAddress)
                .one2oneTitle(this.one2oneTitle)
                .one2oneContent(this.one2oneContent)
                .build();
    }
}

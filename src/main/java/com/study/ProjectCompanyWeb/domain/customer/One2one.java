package com.study.ProjectCompanyWeb.domain.customer;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name="company_one2one")
@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class One2one {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "one2one_idx", nullable = false)
    private Long one2oneIdx;
    @Column(name = "one2one_name", nullable = false)
    private String one2oneName;
    @Column(name = "one2one_phone", nullable = false)
    private String one2onePhone;
    @Column(name = "one2one_email", nullable = false)
    private String one2oneEmail;
    @Column(name = "one2one_address", nullable = false)
    private String one2oneAddress;
    @Column(name = "one2one_title", nullable = false)
    private String one2oneTitle;
    @Column(name = "one2one_content", nullable = false)
    private String one2oneContent;
    @Column(name = "one2one_date", nullable = false)
    private LocalDate one2oneDate=LocalDate.now();

    @Builder
    public One2one(String one2oneName, String one2onePhone, String one2oneEmail, String one2oneAddress, String one2oneTitle, String one2oneContent) {
        this.one2oneName = one2oneName;
        this.one2onePhone = one2onePhone;
        this.one2oneEmail = one2oneEmail;
        this.one2oneAddress = one2oneAddress;
        this.one2oneTitle = one2oneTitle;
        this.one2oneContent = one2oneContent;
    }
}

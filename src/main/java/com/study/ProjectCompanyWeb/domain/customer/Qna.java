package com.study.ProjectCompanyWeb.domain.customer;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name="company_qna")
@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class Qna {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qna_idx", nullable = false)
    private Long qnaIdx;
    @Column(name = "qna_name", nullable = false)
    private String qnaName;
    @Column(name = "qna_pw", nullable = false)
    private String qnaPw;
    @Column(name = "qna_title", nullable = false)
    private String qnaTitle;
    @Column(name = "qna_content", nullable = false)
    private String qnaContent;
    @Column(name = "qna_date", nullable = false)
    private LocalDate qnaDate = LocalDate.now();

    @Builder
    public Qna(String qnaName, String qnaPw, String qnaTitle, String qnaContent) {
        this.qnaName = qnaName;
        this.qnaPw = qnaPw;
        this.qnaTitle = qnaTitle;
        this.qnaContent = qnaContent;
    }
}

package com.study.ProjectCompanyWeb.dto;

import com.study.ProjectCompanyWeb.domain.customer.Qna;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class QnaSaveRequestDto {
    private String qnaTitle;
    private String qnaName;
    private String qnaPw;
    private String qnaContent;

    public Qna toEntity(){
        return Qna.builder()
                .qnaTitle(this.qnaTitle)
                .qnaName(this.qnaName)
                .qnaPw(this.qnaPw)
                .qnaContent(this.qnaContent)
                .build();
    }

    @Override
    public String toString() {
        return "QnaSaveRequestDto{" +
                "qnaTitle='" + qnaTitle + '\'' +
                ", qnaName='" + qnaName + '\'' +
                ", qnaPw='" + qnaPw + '\'' +
                ", qnaContent='" + qnaContent + '\'' +
                '}';
    }
}

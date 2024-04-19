package com.study.ProjectCompanyWeb.service;

import com.study.ProjectCompanyWeb.domain.community.Notice;
import com.study.ProjectCompanyWeb.domain.customer.One2oneRepository;
import com.study.ProjectCompanyWeb.domain.customer.Qna;
import com.study.ProjectCompanyWeb.domain.customer.QnaRepository;
import com.study.ProjectCompanyWeb.dto.One2oneSaveRequestDto;
import com.study.ProjectCompanyWeb.dto.QnaSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final One2oneRepository one2oneRepository;
    private final QnaRepository qnaRepository;

    public Long one2oneSave(One2oneSaveRequestDto reqDto){
        return one2oneRepository.save(reqDto.toEntity()).getOne2oneIdx();
    }

    public Long qnaSave(QnaSaveRequestDto reqDto){
        reqDto.toString();
        return qnaRepository.save(reqDto.toEntity()).getQnaIdx();
    }

    public List<Qna> qnaFindAll(){
        Sort sort = Sort.by(Sort.Direction.DESC, "qnaDate", "qnaIdx");
        return qnaRepository.findAll(sort);
    }

    public List<Qna> findByQnaTitle(String searchText){
        searchText='%'+searchText+'%';
        List<Qna> list = qnaRepository.findByQnaTitle_nativeQuery(searchText);
        return list;
    }
    public List<Qna> findByQnaContent(String searchText){
        searchText='%'+searchText+'%';
        List<Qna> list = qnaRepository.findByQnaContent_nativeQuery(searchText);
        return list;
    }
    public List<Qna> findByQnaName(String searchText){
        searchText='%'+searchText+'%';
        List<Qna> list = qnaRepository.findByQnaName_nativeQuery(searchText);
        return list;
    }
    public Qna qnaFindById(Long qnaIdx){
        Qna qna = qnaRepository.findById(qnaIdx)
                .orElseThrow(()-> new IllegalArgumentException("없는 글 인덱스입니다."));
        return qna;
    }
}

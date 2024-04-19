package com.study.ProjectCompanyWeb.service;

import com.study.ProjectCompanyWeb.domain.community.Notice;
import com.study.ProjectCompanyWeb.domain.community.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeService {
    private final NoticeRepository noticeRepository;

    public List<Notice> findAll(){
        Sort sort = Sort.by(Sort.Direction.DESC, "noticeDate", "noticeIdx");
        return noticeRepository.findAll(sort);
    }

    public Notice findById(Long noticeIdx){
        Notice notice = noticeRepository.findById(noticeIdx)
                .orElseThrow(()-> new IllegalArgumentException(
                        "없는 글 인덱스입니다."
                ));
        return notice;
    }

    public List<Notice> findByNoticeTitle(String searchText){
        searchText='%'+searchText+'%';
        List<Notice> list = noticeRepository.findByNoticeTitle_nativeQuery(searchText);
        return list;
    }

    public List<Notice> findByNoticeContent(String searchText){
        searchText='%'+searchText+'%';
        List<Notice> list = noticeRepository.findByNoticeContent_nativeQuery(searchText);
        return list;
    }
}

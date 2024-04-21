package com.study.ProjectCompanyWeb.service;

import com.study.ProjectCompanyWeb.domain.community.Notice;
import com.study.ProjectCompanyWeb.domain.community.NoticeRepository;
import com.study.ProjectCompanyWeb.dto.NoticeSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public List<Notice> findAllOrder(String searchType, String searchText, PageRequest pageRequest){
        String search='%'+searchText.toLowerCase()+'%';
        if(searchType.equals("null")) return noticeRepository.findByNoticeAll(pageRequest);
        else if(searchType.equals("all"))return noticeRepository.findByNoticeSearchAll(search, pageRequest);
        else if(searchType.equals("title")) return noticeRepository.findByNoticeSearchTitle(search, pageRequest);
        else if(searchType.equals("content")) return noticeRepository.findByNoticeSearchContent(search, pageRequest);
        else if(searchType.equals("id")) return noticeRepository.findByNoticeSearchMemberId(search, pageRequest);

        return null;
    }

    public Long noticeSave(NoticeSaveRequestDto reqDto){
        return noticeRepository.save(reqDto.toEntity()).getNoticeIdx();
    }

    @Transactional
    public Notice update(Long noticeIdx, NoticeSaveRequestDto reqDto){
        Notice notice = noticeRepository.findById(noticeIdx)
                .orElseThrow( () -> new IllegalArgumentException(
                        "없는 글 인덱스입니다."));
        notice.update(reqDto.getNoticeContent());

        return notice;
    }

    @Transactional
    public void delete(Long noticeIdx){
        noticeRepository.delete(
                noticeRepository.findById(noticeIdx).orElseThrow(()-> new IllegalArgumentException("없는 글 인덱스입니다."))
        );
    }
}

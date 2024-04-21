package com.study.ProjectCompanyWeb.domain.community;

import com.study.ProjectCompanyWeb.domain.member.Member;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
    @Query(value="SELECT * FROM company_notice WHERE notice_title LIKE :searchText",
            nativeQuery = true)
    List<Notice> findByNoticeTitle_nativeQuery(String searchText);

    @Query(value="SELECT * FROM company_notice WHERE notice_content LIKE :searchText",
            nativeQuery = true)
    List<Notice> findByNoticeContent_nativeQuery(String searchText);

    //-----------------------------------

    @Query(value="SELECT * FROM company_notice",
            nativeQuery = true)
    List<Notice> findByNoticeAll(Pageable pageable);

    @Query(value="SELECT * FROM company_notice WHERE LOWER(notice_title) LIKE :searchText || LOWER(notice_content) LIKE :searchText || LOWER(notice_member_id) LIKE :searchText",
            nativeQuery = true)
    List<Notice> findByNoticeSearchAll(String searchText, Pageable pageable);

    @Query(value="SELECT * FROM company_notice WHERE LOWER(notice_title) LIKE :searchText",
            nativeQuery = true)
    List<Notice> findByNoticeSearchTitle(String searchText, Pageable pageable);

    @Query(value="SELECT * FROM company_notice WHERE LOWER(notice_content) LIKE :searchText",
            nativeQuery = true)
    List<Notice> findByNoticeSearchContent(String searchText, Pageable pageable);

    @Query(value="SELECT * FROM company_notice WHERE LOWER(notice_member_id) LIKE :searchText",
            nativeQuery = true)
    List<Notice> findByNoticeSearchMemberId(String searchText, Pageable pageable);

    //------------------------------------
}

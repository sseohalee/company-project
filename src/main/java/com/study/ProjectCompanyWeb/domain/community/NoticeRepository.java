package com.study.ProjectCompanyWeb.domain.community;

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
}

package com.study.ProjectCompanyWeb.domain.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QnaRepository extends JpaRepository<Qna, Long> {

    @Query(value="SELECT * FROM company_qna WHERE qna_title LIKE :searchText",
            nativeQuery = true)
    List<Qna> findByQnaTitle_nativeQuery(String searchText);

    @Query(value="SELECT * FROM company_qna WHERE qna_content LIKE :searchText",
            nativeQuery = true)
    List<Qna> findByQnaContent_nativeQuery(String searchText);

    @Query(value="SELECT * FROM company_qna WHERE qna_name LIKE :searchText",
            nativeQuery = true)
    List<Qna> findByQnaName_nativeQuery(String searchText);
}

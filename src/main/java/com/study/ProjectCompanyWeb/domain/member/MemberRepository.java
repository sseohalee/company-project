package com.study.ProjectCompanyWeb.domain.member;

import com.study.ProjectCompanyWeb.domain.community.Notice;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Boolean existsByMemberId(String memberId);
    Member findByMemberIdAndMemberPw(String memberId, String memberPw);

    //------------------------------------
    @Query(value="SELECT * FROM company_member",
            nativeQuery = true)
    List<Member> findByMemberAll(Pageable pageable);

    List<Member> findByMemberIdContaining(String searchText, Pageable pageable);
    List<Member> findByMemberNameContaining(String searchText, Pageable pageable);
    List<Member> findByMemberEmailContaining(String searchText, Pageable pageable);

    @Query(value="SELECT * FROM company_member WHERE member_id LIKE :searchText || member_name LIKE :searchText || member_email LIKE :searchText",
            nativeQuery = true)
    List<Member> searchByMemberAll_nativeQuery(String searchText, Pageable pageable);

    //------------------------------------


    // findAll 정렬
    List<Member> findAllByOrderByMemberIdAsc();
    List<Member> findAllByOrderByMemberIdDesc();
    List<Member> findAllByOrderByMemberJoinDateAsc();
    List<Member> findAllByOrderByMemberJoinDateDesc();
    // 검색
    // searchType : all
    @Query(value="SELECT * FROM company_member WHERE member_id LIKE :searchText || member_name LIKE :searchText || member_email LIKE :searchText ORDER BY member_id",
            nativeQuery = true)
    List<Member> searchByMemberAllId_nativeQuery(String searchText);

    @Query(value="SELECT * FROM company_member WHERE member_id LIKE :searchText || member_name LIKE :searchText || member_email LIKE :searchText ORDER BY member_id DESC",
            nativeQuery = true)
    List<Member> searchByMemberAllIdDesc_nativeQuery(String searchText);

    @Query(value="SELECT * FROM company_member WHERE member_id LIKE :searchText || member_name LIKE :searchText || member_email LIKE :searchText ORDER BY member_join_date",
            nativeQuery = true)
    List<Member> searchByMemberAllJoinAsc_nativeQuery(String searchText);

    @Query(value="SELECT * FROM company_member WHERE member_id LIKE :searchText || member_name LIKE :searchText || member_email LIKE :searchText ORDER BY member_join_date DESC",
            nativeQuery = true)
    List<Member> searchByMemberAllJoinDesc_nativeQuery(String searchText);

    // searchType : id, name, email
    List<Member> findByMemberIdContaining(String searchText);
    List<Member> findByMemberNameContaining(String searchText);
    List<Member> findByMemberEmailContaining(String searchText);

    List<Member> findByMemberIdContaining(String searchText, Sort sort);
    List<Member> findByMemberNameContaining(String searchText, Sort sort);
    List<Member> findByMemberEmailContaining(String searchText, Sort sort);

}

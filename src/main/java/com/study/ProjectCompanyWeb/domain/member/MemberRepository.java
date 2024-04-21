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

    //-----------------------------------

    @Query(value="SELECT * FROM company_member",
            nativeQuery = true)
    List<Member> findByMemberAll(Pageable pageable);

    @Query(value="SELECT * FROM company_member WHERE member_id LIKE :searchText || member_name LIKE :searchText || member_email LIKE :searchText",
            nativeQuery = true)
    List<Member> findByMemberSearchAll(String searchText, Pageable pageable);

    @Query(value="SELECT * FROM company_member WHERE member_id LIKE :searchText",
            nativeQuery = true)
    List<Member> findByMemberSearchId(String searchText, Pageable pageable);

    @Query(value="SELECT * FROM company_member WHERE member_name LIKE :searchText",
            nativeQuery = true)
    List<Member> findByMemberSearchName(String searchText, Pageable pageable);

    @Query(value="SELECT * FROM company_member WHERE member_email LIKE :searchText",
            nativeQuery = true)
    List<Member> findByMemberSearchEmail(String searchText, Pageable pageable);

    //------------------------------------


}

package com.study.ProjectCompanyWeb.service;

import com.study.ProjectCompanyWeb.domain.member.Member;
import com.study.ProjectCompanyWeb.domain.member.MemberRepository;
import com.study.ProjectCompanyWeb.dto.MemberLoginRequestDto;
import com.study.ProjectCompanyWeb.dto.MemberSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Long save(MemberSaveRequestDto reqDto){
        return memberRepository.save(reqDto.toEntity()).getMemberIdx();
    }

    public boolean existsById(Long memberIdx){
        return memberRepository.existsById(memberIdx);
    }

    public boolean existsByMemberId(String memberId){
        return memberRepository.existsByMemberId(memberId);
    }

    public Optional<Member> findByMemberId(String memberId){
        return memberRepository.findAll().stream().filter(m -> m.getMemberId().equals(memberId)).findAny();
    }

    public Optional<Member> findByMemberName(String memberName){
        return memberRepository.findAll().stream().filter(m -> m.getMemberName().equals(memberName)).findAny();
    }

    public List<Member> findAll(){
        return memberRepository.findAll();
    }

    // ------------------------------------------------
    @Transactional
    public List<Member> findAllOrder(String order, Long max){
        // 검색 x, 모든 데이터에 대하여 정렬, 페이징 조건만
        List<Sort.Order> sorts = new ArrayList<>();
        Sort sort = null;
        int page = 0;

        if(order.equals("idAsc")){
            sorts.add(Sort.Order.asc("member_id"));
            sort = Sort.by(Sort.Direction.ASC, "memberId");
        } else if(order.equals("idDesc")){
            sorts.add(Sort.Order.desc("member_id"));
            sort = Sort.by(Sort.Direction.DESC, "memberId");
        } else if(order.equals("joinAsc")){
            sorts.add(Sort.Order.asc("member_join_date"));
            sort = Sort.by(Sort.Direction.ASC, "memberJoinDate");
        } else if(order.equals("joinDesc")){
            sorts.add(Sort.Order.desc("member_join_date"));
            sort = Sort.by(Sort.Direction.DESC, "memberJoinDate");
        }

        // 전체보기 일 때
        if(max==0){
            return memberRepository.findAll(sort);
        }

        Pageable pageable = PageRequest.of(0, Math.toIntExact(max), Sort.by(sorts));

        return memberRepository.findByMemberAll(pageable);
    }

    @Transactional
    public List<Member> searchByMember(String searchType, String searchText, String order, Long max){
        List<Sort.Order> sorts = new ArrayList<>();
        int page = 0;

        Sort sort = null;

        // 정렬조건, 페이징
        if(order.equals("idAsc")){
            sorts.add(Sort.Order.asc("member_id"));
        } else if(order.equals("idDesc")){
            sorts.add(Sort.Order.desc("member_id"));
        } else if(order.equals("joinAsc")){
            sorts.add(Sort.Order.asc("member_join_date"));
        } else if(order.equals("joinDesc")){
            sorts.add(Sort.Order.desc("member_join_date"));
        }

        // 전체보기 부분 구현하기!!!!!!!!!!!!!!!!!!!!!!
        if(max==0){

        }

        Pageable pageable = PageRequest.of(0, Math.toIntExact(max), Sort.by(sorts));

        // 검색
        if(searchType.equals("all")){
            searchText='%'+searchText+'%';
            return memberRepository.searchByMemberAll_nativeQuery(searchText, pageable);
        } else if(searchType.equals("id")){
            return memberRepository.findByMemberIdContaining(searchText, pageable);
        } else if(searchType.equals("name")){
            return memberRepository.findByMemberNameContaining(searchText, pageable);
        } else if(searchType.equals("email")){
            return memberRepository.findByMemberEmailContaining(searchText, pageable);
        }

        return null;
    }

    // ------------------------------------------------

    // search 관련
//    public List<Member> searchByMember(String searchType, String searchText, String order){
//        Sort sort = null;
//        if(order.equals("idAsc")){
//            sort = Sort.by(Sort.Direction.ASC, "memberId", "memberIdx");
//        } else if(order.equals("idDesc")){
//            sort = Sort.by(Sort.Direction.DESC, "memberId", "memberIdx");
//        } else if(order.equals("joinAsc")){
//            sort = Sort.by(Sort.Direction.ASC, "memberJoinDate", "memberIdx");
//        } else if(order.equals("joinDesc")){
//            sort = Sort.by(Sort.Direction.DESC, "memberJoinDate", "memberIdx");
//        }
//
//        if(searchType.equals("id")){
//            return memberRepository.findByMemberIdContaining(searchText.toLowerCase(), sort);
//        } else if(searchType.equals("name")){
//            return memberRepository.findByMemberNameContaining(searchText.toLowerCase(), sort);
//        } else if(searchType.equals("email")){
//            return memberRepository.findByMemberEmailContaining(searchText.toLowerCase(), sort);
//        }
//
//        return null;
//    }

    // ----------------------
    public List<Member> searchByMember2(String searchType, String searchText, String order, Pageable pageable){
        if(searchType.equals("id")){
            return memberRepository.findByMemberIdContaining(searchText.toLowerCase(), pageable);
        } else if(searchType.equals("name")){
            return memberRepository.findByMemberNameContaining(searchText.toLowerCase(), pageable);
        } else if(searchType.equals("email")){
            return memberRepository.findByMemberEmailContaining(searchText.toLowerCase(), pageable);
        }

        return null;
    }
    // ----------------------


    public List<Member> searchByMemberAll(String searchText, String order){
        searchText='%'+searchText.trim().toLowerCase()+'%';
        if(order.equals("idAsc")){
            return memberRepository.searchByMemberAllId_nativeQuery(searchText);
        } else if(order.equals("idDesc")){
            return memberRepository.searchByMemberAllIdDesc_nativeQuery(searchText);
        } else if(order.equals("joinAsc")){
            return memberRepository.searchByMemberAllJoinAsc_nativeQuery(searchText);
        } else if(order.equals("joinDesc")){
            return memberRepository.searchByMemberAllJoinDesc_nativeQuery(searchText);
        }

        // 기본
        return memberRepository.searchByMemberAllId_nativeQuery(searchText);
    }

}

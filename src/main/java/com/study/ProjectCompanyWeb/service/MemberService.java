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

    public List<Member> findAllOrder(String order){
        if(order.equals("idAsc")){
            return memberRepository.findAllByOrderByMemberIdAsc();
        } else if(order.equals("idDesc")){
            return memberRepository.findAllByOrderByMemberIdDesc();
        } else if(order.equals("joinAsc")){
            return memberRepository.findAllByOrderByMemberJoinDateAsc();
        } else if(order.equals("joinDesc")){
            return memberRepository.findAllByOrderByMemberJoinDateDesc();
        }
        return null;
    }

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

    @Transactional(readOnly = true)
    public Page<Member> getList(int page, String order, int max){
        List<Sort.Order> sorts = new ArrayList<>();
        if (order.equals("idAsc")){
            sorts.add(Sort.Order.asc("membeId"));
        } else if(order.equals("idDesc")){
            sorts.add(Sort.Order.desc("memberId"));
        } else if(order.equals("joinAsc")){
            sorts.add(Sort.Order.asc("memberJoinDate"));
        } else if(order.equals("joinDesc")){
            sorts.add(Sort.Order.desc("memberJoinDate"));
        }

        Pageable pageable = PageRequest.of(0, max, Sort.by(sorts));
        return memberRepository.findAll(pageable);
    }
}

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

    public Optional<Member> findByMemberNameAndMemberEmail(String memberName, String memberEmail){
        return memberRepository.findByMemberNameAndMemberEmail(memberName, memberEmail);
    }

    public List<Member> findAll(){
        return memberRepository.findAll();
    }

    // ------------------------------------------------

    @Transactional
    public List<Member> findAllOrder(String searchType, String searchText, PageRequest pageRequest){

        String search='%'+searchText.toLowerCase()+'%';
        if(searchType.equals("null")) return memberRepository.findByMemberAll(pageRequest);
        else if(searchType.equals("all"))return memberRepository.findByMemberSearchAll(search, pageRequest);
        else if(searchType.equals("id")) return memberRepository.findByMemberSearchId(search, pageRequest);
        else if(searchType.equals("name")) return memberRepository.findByMemberSearchName(search, pageRequest);
        else if(searchType.equals("email")) return memberRepository.findByMemberSearchEmail(search, pageRequest);

        return null;
    }

}

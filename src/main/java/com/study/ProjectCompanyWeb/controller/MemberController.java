package com.study.ProjectCompanyWeb.controller;

import com.study.ProjectCompanyWeb.domain.member.Member;
import com.study.ProjectCompanyWeb.dto.*;
import com.study.ProjectCompanyWeb.service.MemberService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    // 회원가입
    @PostMapping("/join")
    @ResponseBody
    public String join(@ModelAttribute MemberSaveRequestDto dto){
        Long newMemberIdx = memberService.save(dto);

        boolean isFound = memberService.existsById(newMemberIdx);
        if(isFound == true){
            return "<script>alert('회원가입 완료'); location.href='/member/login';</script>";
        }else{
            return "<script>alert('회원가입 실패. 다시 시도해주세요.'); history.back();</script>";
        }
    }

    // 회원가입 - 아이디 중복 확인
    @PostMapping("/dupl")
    public MemberDuplResponseDto dupl(@RequestBody MemberDuplRequestDto dto){
        boolean isFound = memberService.existsByMemberId(dto.getMemberId());
        if(isFound == true){
            return MemberDuplResponseDto.builder()
                    .status("ok")
                    .message("중복된 아이디가 있습니다.")
                    .build();
        }

        return MemberDuplResponseDto.builder()
                .status("fail")
                .message("중복된 아이디가 없습니다.")
                .build();
    }

    // 로그인
    @PostMapping("/login")
    @ResponseBody
    public String login(@Valid MemberLoginRequestDto dto,
                        BindingResult bindingResult,
                        HttpSession session){
        if(bindingResult.hasErrors()){
            String detail = bindingResult.getFieldError().getDefaultMessage();
            String bindResultCode = bindingResult.getFieldError().getCode();
            System.out.println(detail + ":"+ bindResultCode);
            return "<script>alert('" + detail +"'); history.back();</script>";
        }

        Optional<Member> optional = memberService.findByMemberId(dto.getMemberId());
        if(!optional.isPresent()){
            return "<script>alert('아이디가 존재하지 않습니다.'); location.href='/member/login';</script>";
        }

        if(!optional.get().getMemberPw().equals(dto.getMemberPw())){
            return "<script>alert('비밀번호가 다릅니다.'); location.href='/member/login';</script>";
        }

        session.setAttribute("isLogin", true);
        session.setAttribute("memberId", optional.get().getMemberId());
        session.setAttribute("memberPw", optional.get().getMemberPw());

        return "<script>alert('로그인 되었습니다.'); location.href='/';</script>";
    }

    @PostMapping("/idFind")
    @ResponseBody
    public String idFind(@ModelAttribute MemberFindIdRequestDto dto){
        Optional<Member> optional = memberService.findByMemberNameAndMemberEmail(dto.getMemberName(), dto.getMemberEmail());
        String btn = "<br><div><button onclick='window.close()'>닫기</button></div>";

        if(!optional.isPresent()){
            return "일치하는 아이디를 찾을 수 없습니다." + btn;
        }

        return "회원님의 아이디는 " + optional.get().getMemberId() +" 입니다."+btn;
    }

    @PostMapping("/pwFind")
    @ResponseBody
    public String pwFind(@ModelAttribute MemberFindPwRequestDto dto){
        System.out.println(dto.toString());
        Optional<Member> optional = memberService.findByMemberId(dto.getMemberId());
        String btn = "<br><div><button onclick='window.close()'>닫기</button></div>";
        if(!optional.isPresent()
                || !optional.get().getMemberName().equals(dto.getMemberName())
                || !optional.get().getMemberEmail().equals(dto.getMemberEmail())){
            return "일치하는 회원 정보를 찾을 수 없습니다." + btn;
        }

        return "회원님의 비밀번호는 " + optional.get().getMemberPw() +" 입니다."+btn;
    }
}

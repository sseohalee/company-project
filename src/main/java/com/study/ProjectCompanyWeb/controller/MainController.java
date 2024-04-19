package com.study.ProjectCompanyWeb.controller;

import com.study.ProjectCompanyWeb.domain.community.Notice;
import com.study.ProjectCompanyWeb.domain.customer.Qna;
import com.study.ProjectCompanyWeb.service.CustomerService;
import com.study.ProjectCompanyWeb.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final NoticeService noticeService;
    private final CustomerService customerService;

    @GetMapping("/")
    public String main(){
        return "index"; //index.html로 응답
    }

    @GetMapping("/member/login")
    public String login(){
        return "/member/login";
    }
    @GetMapping("/member/join")
    public String join(){
        return "/member/join2"; //join2.html 응답
    }
    @GetMapping("/idFind")
    public String idFind(){
        return "/member/idFind"; //idFind.html 응답
    }
    @GetMapping("/passwordFind")
    public String passwordFind(){
        return "/member/passwordFind"; //idFind.html 응답
    }

    // 공지사항
    @GetMapping("/community/notice")
    public String community01(Model model){

        List<Notice> list = noticeService.findAll();
        model.addAttribute("list", list);

        return "/community/community01";
    }
    @GetMapping("/community/notice/detail/{noticeIdx}")
    public String community01_1(@PathVariable Long noticeIdx, Model model){
        Notice notice = noticeService.findById(noticeIdx);
        model.addAttribute("notice",notice);
        return "/community/community01_1";
    }

    // 1:1 문의, 묻고 답하기, FAQ
    @GetMapping("/customer/customer01")
    public String customer01(@SessionAttribute(name = "memberId", required = false) String memberId){
        // 1:1 문의
        if(memberId==null){
            return "redirect:/alert/login";
        }
        return "/customer/customer01";
    }
    @GetMapping("/customer/customer02")
    public String customer02(@SessionAttribute(name = "memberId", required = false) String memberId,
                             Model model){
        // 묻고답하기
        if(memberId==null){
            return "redirect:/alert/login";
        }

        List<Qna> list = customerService.qnaFindAll();
        model.addAttribute("list",list);

        return "/customer/customer02";
    }
    @GetMapping("/customer/customer02_2")
    public String customer02_2(@SessionAttribute(name = "memberId", required = false) String memberId){
        // 묻고답하기 글쓰기
        if(memberId==null){
            return "redirect:/alert/login";
        }

        return "/customer/customer02_2";
    }
    @GetMapping("/customer/customer02/search")
    public String qnaSearch(@RequestParam String searchType, @RequestParam String searchText,
                            Model model){
        if(searchType.equals("title")) {
            List<Qna> list = customerService.findByQnaTitle(searchText);
            model.addAttribute("list", list);
        } else if(searchType.equals("content")){
            List<Qna> list = customerService.findByQnaContent(searchText);
            model.addAttribute("list", list);
        } else if(searchType.equals("name")){
            List<Qna> list = customerService.findByQnaName(searchText);
            model.addAttribute("list", list);
        }
        return "/customer/customer02";
    }
    @GetMapping("/customer/customer02_3")
    public String customer02_3(@RequestParam Long qnaIdx, Model model){
        model.addAttribute("qnaIdx",qnaIdx);
        return "/customer/customer02_3";
    }
    @GetMapping("/customer/customer02_4")
    public String customer02_4(@RequestParam Long qnaIdx, Model model){
        Qna qna = customerService.qnaFindById(qnaIdx);
        model.addAttribute("qna",qna);
        return "/customer/customer02_4";
    }


    @GetMapping("/customer/customer03")
    public String customer03(@SessionAttribute(name = "memberId", required = false) String memberId){
        // FAQ
        if(memberId==null){
            return "redirect:/alert/login";
        }
        return "/customer/customer03";
    }
    @GetMapping("/alert/{status}")
    @ResponseBody
    public String alert(@PathVariable String status){
        System.out.println(status);
        if(status.equals("login")){
            return "<script>alert('로그인 해주세요'); location.href='/member/login';</script>";
        }
        return "<script>location.href='/';</script>";
    }
}

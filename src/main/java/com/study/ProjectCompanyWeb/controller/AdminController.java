package com.study.ProjectCompanyWeb.controller;

import com.study.ProjectCompanyWeb.domain.member.Member;
import com.study.ProjectCompanyWeb.domain.member.MemberRepository;
import com.study.ProjectCompanyWeb.service.MemberService;
import io.micrometer.common.lang.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminController {
    private final MemberService memberService;

    @GetMapping("/admin")
    public String admin(){
        return "/admin/admin_login";
    }

    @GetMapping("/admin/login")
    public String login(@RequestParam String adminId,
                        @RequestParam String adminPw){
        if(adminId.equals("admin") && adminPw.equals("1234")){
            return "redirect:/admin_member";
        }
        return "redirect:/";
    }

//    @GetMapping("/admin_member")
//    public String admin_member(Model model){
//        List<Member> list = memberService.findAll();
//        model.addAttribute("list",list);
//        model.addAttribute("listSize", list.size());
//        return "/admin/admin_member";
//    }
//
    @RequestMapping("/admin_member")
    public String admin_member(@RequestParam(value="searchType", required = false, defaultValue = "null") @Nullable String searchType,
                               @RequestParam(value="searchText", required = false, defaultValue = "null") @Nullable String searchText,
                               @RequestParam(value="order", required = false, defaultValue = "idAsc") String order,
                               @RequestParam(value="max", required = false, defaultValue = "0") int max,
                               Model model){
        List<Member> list = null;
        int page = 0;

        // 전체 멤버 길이 구하기
        if(max==0) {
            List<Member> all = memberService.findAll();
            max=all.size();
        }

        // 정렬 조건
        PageRequest pageRequest = PageRequest.of(page, 5);
        if(order.equals("idAsc")) pageRequest = PageRequest.of(page, max, Sort.by("member_id").ascending());
        else if(order.equals("idDesc")) pageRequest = PageRequest.of(page, max, Sort.by("member_id").descending());
        else if(order.equals("joinAsc")) pageRequest = PageRequest.of(page, max, Sort.by("member_join_date").ascending());
        else if(order.equals("joinDesc")) pageRequest = PageRequest.of(page, max, Sort.by("member_join_date").descending());

        list = memberService.findAllOrder(searchType, searchText, pageRequest);


        model.addAttribute("list",list);
        model.addAttribute("listSize", list.size());
        model.addAttribute("searchType", searchType);
        model.addAttribute("searchText", searchText);
        model.addAttribute("order", order);
        model.addAttribute("max", max);


         return "/admin/admin_member";
    }
}

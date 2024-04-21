package com.study.ProjectCompanyWeb.controller;

import com.study.ProjectCompanyWeb.domain.member.Member;
import com.study.ProjectCompanyWeb.domain.member.MemberRepository;
import com.study.ProjectCompanyWeb.service.MemberService;
import io.micrometer.common.lang.Nullable;
import lombok.RequiredArgsConstructor;
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
                               @RequestParam(value="max", required = false, defaultValue = "5") Long max,
                               Model model){
        List<Member> list = null;

        // 검색 x, 모든 데이터에 대하여 정렬, 페이징 조건만
        if(searchType.equals("null")){
            list=memberService.findAllOrder(order, max);
        } else {
            list=memberService.searchByMember(searchType, searchText, order, max);
        }

        model.addAttribute("list",list);
        model.addAttribute("listSize", list.size());
        model.addAttribute("searchType", searchType);
        model.addAttribute("searchText", searchText);
        model.addAttribute("order", order);
        model.addAttribute("max", max);


         return "/admin/admin_member";
    }
}

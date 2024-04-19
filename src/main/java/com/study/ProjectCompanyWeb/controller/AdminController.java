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
                               @PageableDefault(size=5, sort="memberId", direction= Sort.Direction.ASC)Pageable pageable,
                               Model model){
        List<Member> list = null;

        if(searchType.equals("all")){
          list = memberService.searchByMemberAll(searchText, order);
        } else if(!searchType.equals("null")){
//            list = memberService.searchByMember(searchType, searchText, order);
            list = memberService.searchByMember2(searchType, searchText, order, pageable);
        } else{
            list = memberService.findAllOrder(order);
        }

        if(searchType!="null"){
            System.out.println(searchType);
            model.addAttribute("searchType", searchType);
            model.addAttribute("searchText", searchText);
        }
        model.addAttribute("list",list);
        model.addAttribute("listSize", list.size());

         return "/admin/admin_member";
    }
}

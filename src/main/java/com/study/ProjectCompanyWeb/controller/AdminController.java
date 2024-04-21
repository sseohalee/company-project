package com.study.ProjectCompanyWeb.controller;

import com.study.ProjectCompanyWeb.domain.community.Notice;
import com.study.ProjectCompanyWeb.domain.member.Member;
import com.study.ProjectCompanyWeb.domain.member.MemberRepository;
import com.study.ProjectCompanyWeb.dto.NoticeSaveRequestDto;
import com.study.ProjectCompanyWeb.service.MemberService;
import com.study.ProjectCompanyWeb.service.NoticeService;
import io.micrometer.common.lang.Nullable;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminController {
    private final MemberService memberService;
    private final NoticeService noticeService;

    @GetMapping("/admin")
    public String admin(){
        return "/admin/admin_login";
    }

    @GetMapping("/admin/login")
    public String login(@RequestParam String adminId,
                        @RequestParam String adminPw,
                        HttpSession session){
        if(adminId.equals("admin") && adminPw.equals("1234")){
            session.setAttribute("isLogin", true);
            session.setAttribute("memberId", "admin");
            session.setAttribute("memberPw", "1234");

            return "redirect:/admin_member";
        }
        return "redirect:/";
    }

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
        PageRequest pageRequest = PageRequest.of(page, max);
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

    @RequestMapping("/admin_notice")
    public String admin_notice(@RequestParam(value="searchType", required = false, defaultValue = "null") @Nullable String searchType,
                               @RequestParam(value="searchText", required = false, defaultValue = "null") @Nullable String searchText,
                               @RequestParam(value="order", required = false, defaultValue = "idAsc") String order,
                               @RequestParam(value="max", required = false, defaultValue = "0") int max,
                               Model model){

        List<Notice> list = null;
        int page = 0;

        // 전체 공지사항 개수 구하기
        if(max==0) {
            List<Notice> all = noticeService.findAll();
            max=all.size();
        }

        // 정렬 조건 적용
        PageRequest pageRequest = PageRequest.of(page, max);
        if(order.equals("idAsc")) pageRequest = PageRequest.of(page, max, Sort.by("notice_member_id").ascending());
        else if(order.equals("idDesc")) pageRequest = PageRequest.of(page, max, Sort.by("notice_member_id").descending());
        else if(order.equals("dateAsc")) pageRequest = PageRequest.of(page, max, Sort.by("notice_date").ascending());
        else if(order.equals("dateDesc")) pageRequest = PageRequest.of(page, max, Sort.by("notice_date").descending());

        list = noticeService.findAllOrder(searchType, searchText, pageRequest);


        model.addAttribute("list",list);
        model.addAttribute("listSize", list.size());
        model.addAttribute("searchType", searchType);
        model.addAttribute("searchText", searchText);
        model.addAttribute("order", order);
        model.addAttribute("max", max);

        return "/admin/admin_notice";
    }

    // 글 쓰기 버튼 클릭 -> 새 글 등록
    @RequestMapping("/admin_notice_write")
    public String admin_notice_write(@SessionAttribute(name = "memberId", required = false) String memberId,
                                     Model model){
        if(memberId==null){
            return "redirect:/admin";
        }
        model.addAttribute("memberId",memberId);

        return "/admin/admin_notice_write";
    }

    @PostMapping("/admin_notice_write_action")
    @ResponseBody
    public String admin_notice_write_action(@ModelAttribute NoticeSaveRequestDto dto){
        try{
            noticeService.noticeSave(dto);
        }catch (Exception e){
            return "<script>alert('공지사항 등록에 실패하였습니다.'); location.href='/admin_notice';</script>";
        }

        return "<script>alert('공지사항 등록에 성공하였습니다.'); location.href='/admin_notice';</script>";
    }

    // 글 클릭 -> 수정 및 삭제
    @RequestMapping("/admin_notice_view")
    public String admin_notice_view(@RequestParam(value="noticeIdx", required = false, defaultValue = "0") Long noticeIdx,
                                    Model model){
        Notice notice = noticeService.findById(noticeIdx);
        model.addAttribute("notice", notice);
        return "/admin/admin_notice_view";
    }

    @PostMapping("/admin_notice_modify")
    @ResponseBody
    public String admin_notice_modify(@RequestParam("noticeIdx") Long noticeIdx,
                                      @ModelAttribute NoticeSaveRequestDto dto,
                                      Model model){
        Notice notice = noticeService.update(noticeIdx, dto);
        // 글 수정 action
        if( notice.getNoticeIdx() == noticeIdx ) {
            return "<script>alert('공지사항 수정 성공'); location.href='/admin_notice';</script>";
        }else{
            return "<script>alert('공지사항 수정 실패'); history.back();</script>";
        }
    }

    @RequestMapping("/admin_notice_delete/{noticeIdx}")
    @ResponseBody
    public String admin_notice_delete(@PathVariable Long noticeIdx){
        try{
            noticeService.delete(noticeIdx);
        }catch (Exception e){
            e.printStackTrace();
            return "<script>alert('공지사항 삭제 실패'); history.back();</script>";
        }
        return "<script>alert('공지사항 삭제 성공'); location.href='/admin_notice';</script>";
    }

}

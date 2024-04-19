package com.study.ProjectCompanyWeb.controller;

import com.study.ProjectCompanyWeb.domain.community.Notice;
import com.study.ProjectCompanyWeb.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequestMapping("/api/v1/community")
@RequiredArgsConstructor
public class CommunityController {
    private final NoticeService noticeService;

    @GetMapping("/search")
    public String search(@RequestParam String searchType, @RequestParam String searchText,
                         Model model){
        if(searchType.equals("title")) {
            List<Notice> list = noticeService.findByNoticeTitle(searchText);
            model.addAttribute("list", list);
        } else if(searchType.equals("content")){
            List<Notice> list = noticeService.findByNoticeContent(searchText);
            model.addAttribute("list", list);
        }
        return "/community/community01";
    }

}

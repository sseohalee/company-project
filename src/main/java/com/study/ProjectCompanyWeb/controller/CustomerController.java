package com.study.ProjectCompanyWeb.controller;

import com.study.ProjectCompanyWeb.domain.community.Notice;
import com.study.ProjectCompanyWeb.domain.customer.Qna;
import com.study.ProjectCompanyWeb.dto.One2oneSaveRequestDto;
import com.study.ProjectCompanyWeb.dto.QnaSaveRequestDto;
import com.study.ProjectCompanyWeb.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/one2one/save")
    @ResponseBody
    public String one2oneSave(@ModelAttribute One2oneSaveRequestDto dto){
        try{
            customerService.one2oneSave(dto);
        }catch (Exception e){
            e.printStackTrace();
            return "<script>alert('문의 글 등록에 실패하였습니다.'); location.href='/';</script>";
        }
        return "<script>alert('문의 글이 등록되었습니다.'); location.href='/';</script>";
    }

    @PostMapping("/qna/save")
    @ResponseBody
    public String qnaSave(@ModelAttribute QnaSaveRequestDto reqDto){
        try{
            customerService.qnaSave(reqDto);
        }catch (Exception e){
            e.printStackTrace();
            return "<script>alert('묻고답하기 글 등록에 실패하였습니다.'); location.href='/customer/customer02';</script>";
        }
        return "<script>alert('묻고답하기 글이 등록되었습니다.'); location.href='/customer/customer02';</script>";
    }

    @GetMapping("/qna/pwCheck")
    @ResponseBody
    public String qnaPwCheck(@RequestParam Long qnaIdx, @RequestParam String qnaPw){
        try{
            Qna qna = customerService.qnaFindById(qnaIdx);
            if(!qna.getQnaPw().equals(qnaPw)){
                return "<script>alert('비밀번호가 일치하지 않습니다.');window.close();</script>";
            }
            return "<script>window.opener.location.href='/customer/customer02_4?qnaIdx="+qnaIdx+"'; window.close();</script>";
        }catch (Exception e){
            e.printStackTrace();
            return "<script>alert('존재하지 않는 글입니다.');window.close();</script>";
        }


    }

}

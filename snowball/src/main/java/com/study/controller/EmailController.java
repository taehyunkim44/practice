package com.study.controller;

import java.lang.ProcessBuilder.Redirect;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.study.dto.MailDTO;
import com.study.service.MailService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller // 컨트롤러 어노테이션 선언
@RequestMapping("/email/*") // 공통된 매핑 주소
public class EmailController {
   
   @Inject
   MailService mailService; // 서비스를 호출하기 위한 의존성 주입
   
   @RequestMapping("/write.do") // 이메일 쓰기를 누르면 여기 메소드에서 매핑 됨
   public String write() {
      return "/email/write"; // 다시 write jsp 페이지로 이동하고 jsp페이지에서 내용을 다 채운 뒤에 확인 버튼을 누르면 send.do로 넘어감
            
   }

   
   
   @PostMapping("/send.do") 
   public String send(@ModelAttribute MailDTO mailDto, Model model) { // 발송 버튼을 누르면 매핑 되는 메소드
      
      log.info("email"+mailDto);
      
      MailDTO mailDtoDB = new MailDTO();
      
      try {
         String[] receiver_list = {}; 
         receiver_list = mailDto.getReceiver_id().split(",|, | , ");
         for (String receiver_addr:receiver_list) {
            MailDTO mailDto_temp = mailDto;
            mailDto_temp.setReceiver_id(receiver_addr);
            
            mailDtoDB = mailService.EmailService(mailDto_temp);
         }
         mailService.emailToDB(mailDtoDB);
         // 얘가 반복
         //mailService.EmailService(mailDto); // dto 메일 관련 정보를 EmailService에 저장
         
         // model.addAttribute("message", "이메일이 전송 되었습니다"); // 이메일 발송 성공 시 
      } catch (Exception e) {
         e.printStackTrace();
         model.addAttribute("message", "이메일 전송 실패"); // 이메일 발송 실패 시 전송될 메세지
      }
      return "redirect:/email/notice_list";
   }

   @GetMapping("/notice_list")
   public void list(MailDTO mailDto,Model model) {
      
      log.info("공지 메일 리스트 요청");
      
      List<MailDTO> list = mailService.select(mailDto);
      log.info("공지 메일 리스트를 받아서 리스트안에 담기");
      
      model.addAttribute("notice_list", list);
      log.info("공지 메일 리스트를 담은 리스트를 --> notice_list로");
   }
   
   @GetMapping("/notice_read")
   public void read(String mail_id, Model model) {
      log.info("id로 공지 폼 읽기");
      
      MailDTO noticeRead = mailService.read(mail_id);
      
      model.addAttribute("noticeRead", noticeRead);
      
   }
}
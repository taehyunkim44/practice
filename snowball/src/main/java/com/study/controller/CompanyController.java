package com.study.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.study.dto.CompanyDTO;
import com.study.service.CompanyService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/company/*")
public class CompanyController {

   @Autowired
   private CompanyService companyService;
   
   @GetMapping("/company_list")
   public void list(Model model) {
      log.info("원청 전체 리스트 요청");
      
      List<CompanyDTO> list = companyService.select();
      log.info("원청 전체 리스트 받아와서 담기");
      
      model.addAttribute("company_list", list);
      log.info("원청 전체 리스트 모델에 담기");
   }
   
   @PostMapping("/company_list")
   public String listPost(@RequestParam("company_id") String company_id,Model model, RedirectAttributes rttr) {
      log.info("원청 전체 리스트 요청 POST");
      
      rttr.addFlashAttribute("company_id", company_id);
      model.addAttribute("company_id", company_id);
      return "redirect:/company/company_read";
   }
   
   @GetMapping({"/company_read","/company_modify"})
   public void read(String company_id, Model model) {
      log.info("company_id로 읽어오기"+company_id);
      
      CompanyDTO compDto = companyService.read(company_id);
      log.info("compDto start"+compDto.getCompany_partner_start());
      log.info("compDto end"+compDto.getCompany_partner_end());
      model.addAttribute("compDto", compDto);
      log.info("company_dto 만들기"+compDto);
   }
   
   @GetMapping("/company_register")
   public void register() {
      log.info("등록 폼 요청");
   }
   
   @PostMapping("/company_register")
   public String register(CompanyDTO registerDto) {
      log.info("원청 등록 폼 요청"+registerDto);
      
      companyService.register(registerDto);
      
      
      // register 등록 성공 시 list로
      return "redirect:/company/company_list";
      
   }
   
   //Model model 가져온 값을 model에 저장해서 jsp안에서 사용할 수 있게 도와줌
//   @GetMapping("/company_delete")
//   public void delete(@RequestParam("company_id") String company_id,Model model) {
//      
//      model.addAttribute("company_id", company_id);
//      log.info("원청 삭제 폼 요청");
//   }
//   
   @PostMapping("/company_delete")
   public String delete(@RequestParam("company_id") String company_id, RedirectAttributes rttr,Model model) {
      log.info("원청 삭제 요청");
      
      model.addAttribute("company_id",company_id);
      rttr.addAttribute("company_id", company_id);
      
      companyService.delete(company_id);
      
      // delete 성공 시, list로
      return "redirect:/company/company_list";
   }
   
   /*
    * @GetMapping("/company_modify") public void
    * modifyGet(@RequestParam("company_id") String company_id, Model model) {
    * log.info("modify get 성공"); CompanyDTO modifyDto =
    * companyService.getRow(company_id); log.info("modify getRow 해서 Dto 담아줌");
    * model.addAttribute("modifyDto", modifyDto); log.info("모델에 넣기");
    * 
    * }
    */
   
   @PostMapping("/company_update")
   public String modifyPost(CompanyDTO compDto,RedirectAttributes rttr) {
      log.info("company_update post"+compDto);
      companyService.modify(compDto);
      log.info("수정 modify"+compDto);
      rttr.addAttribute("company_id", compDto.getCompany_id());
      log.info("redirect compid rttr");
      return "redirect:/company/company_read";
   }
}

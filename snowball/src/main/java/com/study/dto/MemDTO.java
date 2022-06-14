package com.study.dto;


import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;


@Data
public class MemDTO {
	
	private String grade_id;
	private String company_id;
	private String dept_id;
	private String mem_id;
	private String mem_name;
	private String mem_phone;
	private String mem_mail;
	private String mem_gender;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date mem_birth;
	
	private String mem_addr;
	private int mem_sal;
	private String mem_pwd;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date mem_con_start;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date mem_con_end;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date mem_dcon_start;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date mem_dcon_end;

}

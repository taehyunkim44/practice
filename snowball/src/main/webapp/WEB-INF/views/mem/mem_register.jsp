<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="../includes/header.jsp" %>
<div id="layoutSidenav_content">
<main>
<div class="container-fluid px-4">
	<h1>사용자 등록</h1>
          
            <div class="row">
                <div class="col-lg-12">
                	<div class="panel panel-default">
                         <div class="panel-heading">
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                			<form action="/mem/mem_register" method="post" role="form">
                				<div class="form-group">
                					<label>사원번호</label>
                					<input class="form-control" name="mem_id" >                				
                				</div> 
                				<div class="form-group">
                					<label>직책번호</label>
                					<input class="form-control" name="grade_id" >                				
                				</div>  
                				<div class="form-group">
                					<label>원청번호</label>
                					<input class="form-control" name="company_id" >             				
                				</div> 
                				<div class="form-group">
                					<label>부서번호</label>
                					<input class="form-control" name="dept_id" >                				
                				</div>
                					<div class="form-group">
                					<label>이름</label>
                					<input class="form-control" name="mem_name" >                				
                				</div>  
                				<div class="form-group">
                					<label>전화번호</label>
                					<input class="form-control" name="mem_phone" >                				
                				</div>   
                				<div class="form-group">
                					<label>메일</label>
                					<input class="form-control" name="mem_mail" >                				
                				</div>
                				<div class="form-group">
                					<label>성별</label>
                					<input class="form-control" name="mem_gender" >                				
                				</div> 
                				<div class="form-group">
                					<label>생년월일</label>
                					<input class="form-control" name="mem_birth" >                				
                				</div> 
                				<div class="form-group">
                					<label>주소</label>
                					<input class="form-control" name="mem_addr" >                				
                				</div> 
                				<div class="form-group">
                					<label>연봉</label>
                					<input class="form-control" name="mem_sal" >                				
                				</div> 
                				<div class="form-group">
                					<label>비밀번호</label>
                					<input class="form-control" name="mem_pwd" >                				
                				</div>
                				<div class="form-group">
                					<label>계약일</label>
                					<input class="form-control" name="mem_con_start" >                				
                				</div> 
                				<div class="form-group">
                					<label>계약만료일</label>
                					<input class="form-control" name="mem_con_end" >                				
                				</div> 
                				<div class="form-group">
                					<label>파트너사계약일</label>
                					<input class="form-control" name="mem_dcon_start" >                				
                				</div> 
                				<div class="form-group">
                					<label>파트너사계약만료일</label>
                					<input class="form-control" name="mem_dcon_end" >                				
                				</div> 
                		
                				<input type="hidden" value="1" name="pageNum"/>
								<input type="hidden" value="10" name="amount"/>
								<input type="hidden" value="${cri.type}" name="type"/>
								<input type="hidden" value="${cri.keyword}" name="keyword"/>
							
                				<button type="submit" class="btn btn-default">Submit</button>              			
                				<button type="reset" class="btn btn-default">reset</button>                 				            			
                			</form>
                		</div>
                	</div>
                </div>
            </div> 
   	

       
<script src="/resources/js/mem_register.js"></script>        

<%@include file="../includes/footer.jsp" %>
















     
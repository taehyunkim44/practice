<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="../includes/header.jsp" %>

<div id="layoutSidenav_content">
<main>
<div class="container-fluid px-4">
   <h1>사용자 정보 리스트</h1>

<!-- /.row -->
<div class="row">
  <div class="col-lg-12">
    <div class="panel panel-default">
      <div class="panel-heading">
        <button id="regBtn" type="button" class="btn-pr-4 mb-2">
          사용자 등록
        </button>
      </div>
      <!-- /.panel-heading -->
      <div class="panel-body">
      	<div>
        <table class="table table-striped table-bordered table-hover">
          <thead>
            <tr>
              <th>사원번호</th>
              <th>직책번호</th>
              <th>원청번호</th>
              <th>부서번호</th>
              <th>이름</th>
              <th>전화번호</th>
              <th>메일</th>
              <th>성별</th>
              <th>생년월일</th>
              <th>주소</th>
              <th>연봉</th>
              <th>비밀번호</th>
              <th>계약일</th>
              <th>계약만료일</th>
              <th>파트너사계약일</th>
              <th>파트너사계약만료일</th>           
            </tr>
          </thead>
          <tbody>
          	<!-- 게시판 리스트 반복문 -->
            <c:forEach var="dto" items="${list}">
     
	          	<tr>
	          		<td>${dto.mem_id}</td>
	          		<td>${dto.grade_id}</td>
	          		<td>${dto.company_id}</td>
	          		<td>${dto.dept_id}</td>
	          		<td>${dto.mem_name}</td>
	          		<td>${dto.mem_phone}</td>
	          		<td>${dto.mem_mail}</td>
	          		<td>${dto.mem_gender}</td>
	    			<td><fmt:formatDate pattern="yyyy-MM-dd" value="${dto.mem_birth}"/></td>
	    			<td>${dto.mem_addr}</td>
	    			<td>${dto.mem_sal}</td>
	    			<td>${dto.mem_pwd}</td>
	    			<td><fmt:formatDate pattern="yyyy-MM-dd" value="${dto.mem_con_start}"/></td>
	    			<td><fmt:formatDate pattern="yyyy-MM-dd" value="${dto.mem_con_end}"/></td>
	    			<td><fmt:formatDate pattern="yyyy-MM-dd" value="${dto.mem_dcon_start}"/></td>
	    			<td><fmt:formatDate pattern="yyyy-MM-dd" value="${dto.mem_dcon_end}"/></td>
	          		<td><input type="button" value="수정" onClick="location.href='/mem/mem_read?mem_id=${dto.mem_id}'">
	          			<a id="update"></a>
	          		</td>
	          		<td><input type="button" name="remove"value="삭제"></td>
	          	</tr>

          	</c:forEach>
          </tbody>
        </table>
        </div>
        
        <div class="row">
          <!-- start search -->
          <div class="col-md-12">
            <div class="col-md-8">
            	<!--search Form-->
            	<form action="" method="get" id="searchForm">
            		<input type="hidden" name="pageNum" value="${cri.pageNum}" />
					<input type="hidden" name="amount" value="${cri.amount}" />            	
            		<select name="type" id="">
            			<option value=""  <c:out value="${cri.type == '' ? 'selected':'' }"/>>---------</option>
            			<option value="G" <c:out value="${cri.type == 'G' ? 'selected':'' }"/>>직책</option>
            			<option value="C" <c:out value="${cri.type == 'C' ? 'selected':'' }"/>>원청</option>
            			<option value="D" <c:out value="${cri.type == 'D' ? 'selected':'' }"/>>부서</option>
            			<option value="N" <c:out value="${cri.type == 'N' ? 'selected':'' }"/>>이름</option>              		
            		</select>            
            		<input type="text" name="keyword" id=""  value="${cri.keyword}"/>
            		<button class="btn btn-primary" type="submit">Search</button>	
            	</form>                
            </div>
          </div>
        </div>
        <!-- end search -->
        <!-- start Pagination -->
        <div>
	        <nav >
			  <ul class="pagination justify-content-center">
			  	<c:if test="${pageDto.prev}">
			    	<li class="page-item">
			      		<a class="paginate_button previous" href="${pageDto.startPage-1}">Previous</a>
			    	</li>
			    </c:if>
			    <c:forEach var="idx" begin="${pageDto.startPage}" end="${pageDto.endPage}">
			    	<li class="paginate_button ${pageDto.cri.pageNum==idx?'active':''}"><a class="page-link" href="${idx}">${idx}</a></li>
			    </c:forEach>
			    <c:if test="${pageDto.next}">
				    <li class="page-item">
				      <a class="paginate_button next" href="${pageDto.endPage+1}">Next</a>
				    </li>
				</c:if>
			  </ul>
			</nav>
		</div>
        <!-- end Pagination -->
      </div>
      <!-- end panel-body -->
    </div>
    <!-- end panel -->
  </div>
</div>


<!-- /.row <--></-->
<%-- 페이지 링크를 처리할 폼 --%>
<div>
<form action="" id="actionForm">
	<!-- pageNum, amount, type, keyword 값을 부를 때 
	     ① pageDto(pageDto.cri.pageNum) 
     	 ② cri(criteria.pageNum(ModelAttribute 가 사용 안된 경우), cri.pageNum)     	 
     -->
	
	<input type="hidden" name="mem_id" value="${dto.mem_id}" />
	<input type="hidden" name="pageNum" value="${cri.pageNum}" />
	<input type="hidden" name="amount" value="${cri.amount}" />
	<input type="hidden" name="type" value="${cri.type}" />
	<input type="hidden" name="keyword" value="${cri.keyword}" />
</form>

<form action="" id="removeForm">
	
	<input type="hidden" name="mem_id" value="${dto.mem_id}" />
	<input type="hidden" name="pageNum" value="${cri.pageNum}" />
	<input type="hidden" name="amount" value="${cri.amount}" />
	<input type="hidden" name="type" value="${cri.type}" />
	<input type="hidden" name="keyword" value="${cri.keyword}" />
</form>
</div>

<!-- 스크립트 -->
<script>
	//게시글 등록 성공 후 result 확인
	let result = '${result}';
	
</script>
<script src="/resources/js/mem_list.js"></script>
</div>
</main>


<%@include file="../includes/footer.jsp" %>

</div>















<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>웹 공부</title>
<style type="text/css">
    body{
        line-height:2em;        
        font-family:"맑은 고딕";
}
    ul, li{ 
        list-style:none;
        text-align:center;
        padding:0;
        margin:0;
}

    #mainWrapper{
        width: 800px;
        margin: 0 auto; /*가운데 정렬*/
    }

    #mainWrapper > ul > li:first-child {
        text-align: center;
        font-size:14pt;
        height:40px;
        vertical-align:middle;
        line-height:30px;
}

    #ulTable {margin-top:10px;}
    

    #ulTable > li:first-child > ul > li {
        background-color:#c9c9c9;
        font-weight:bold;
        text-align:center;
}

    #ulTable > li > ul {
        clear:both;
        padding:0px auto;
        position:relative;
        min-width:40px;
}
    #ulTable > li > ul > li { 
        float:left;
        font-size:10pt;
        border-bottom:1px solid silver;
        vertical-align:baseline;
}    

    #ulTable > li > ul > li:first-child               {width:10%;} /*No 열 크기*/
    #ulTable > li > ul > li:first-child +li           {width:45%;} /*제목 열 크기*/
    #ulTable > li > ul > li:first-child +li+li        {width:20%;} /*작성일 열 크기*/
    #ulTable > li > ul > li:first-child +li+li+li     {width:15%;} /*작성자 열 크기*/
    #ulTable > li > ul > li:first-child +li+li+li+li{width:10%;} /*조회수 열 크기*/

    #divPaging {
          clear:both; 
        margin:0 auto; 
        width:220px; 
        height:50px;
}

    #divPaging > div {
        float:left;
        width: 30px;
        margin:0 auto;
        text-align:center;
}

    #liSearchOption {clear:both;}
    #liSearchOption > div {
        margin:0 auto; 
        margin-top: 30px; 
        width:auto; 
        height:100px;

}

    .left {
        text-align : left;
}


</style>

</head>
<body>
    <div id="mainWrapper">

        <ul>
            <!-- 게시판 제목 -->
            <li>게시판 Title </li>

            <!-- 게시판 목록  -->
            <li>
                Table
                <ul id ="ulTable">
                    <li>
                        <ul>
                            <li>번호</li>
                            <li>제목</li>
                            <li>작성자</li>
                            <li>작성일</li>
                            <li>조회수</li>
                        </ul>
                    </li>
                    <!-- 게시물이 출력될 영역 -->
                    <c:forEach var="n" items="${list }">
                    <li>
                   		<ul>
                   			<li>${n.id }</li>
                   			<li class="center"><a href="detail?id=${n.id}">${n.title }</a></li>
                   			<li>${n.writerId }</li>
                   			<li>${n.regdate }</li>
                   			<li>${n.hit }</li>
                   		</ul>
                   	</li>
                    </c:forEach>                         
                </ul>
            </li>

            <!-- 게시판 페이징 영역 -->
            <c:set var="page" value="${(empty param.p)?1:param.p }"/>
            <c:set var="startNum" value="${page-(page-1)%5}"/>
            <c:set var="lastNum" value="20"/>
            
            <li>
                <div id="divPaging">
                    <div>
                    	<c:if test="${page-5>0 }">
                    		<button type="button" onclick="location.href='?p=${page-5 }'">이전</button>
                    	</c:if>
                    	<c:if test="${page-5<=0 }">
                    		<span onclick="alert('이전 페이지가 업습니다.')">
                    		<button type="button">이전</button></span>
                    	</c:if>
                    	
                    </div>
                       <c:forEach var="i" begin="0" end="4">
                       <c:if test="${(start+Num+i) <= lastNum}">
                       		<div><a href="?p=${i+startNum }&f=${param.f}&q=${param.q}">${i+startNum }</a></div>
                       </c:if>
                       </c:forEach>
                    <div>
                    	<c:if test="${startNum+4<lastNum }">
                    		<button type="button" onclick="location.href='?p=${startNum+5 }'">다음</button>
                    	</c:if>
                    	<c:if test="${startNum+4>=lastNum }">
                    		<span onclick="alert('다음 페이지가 업습니다.')">
                    		<button type="button">다음</button></span>
                    	</c:if>
                    </div>
                </div>
            </li>

            <!-- 검색 폼 영역 -->
            <li id='liSearchOption'>
                <div>
                    <select id='selSearchOption' >
                        <option value='A'>제목+내용</option>
                        <option value='T'>제목</option>
                        <option value='C'>내용</option>
                    </select>
                    <input id='txtKeyWord' />
                    <input type='button' value='검색'/>
                </div>
                </li>

        </ul>
    </div>
</body>
</html>
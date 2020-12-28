<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="../common/taglibs.jsp" %>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>预购系统</title>
    <link rel="stylesheet" href="../css/swiper.min.css">
    <link rel="stylesheet" href="../css/style.css">
    <link rel="stylesheet" href="../css/xadmin.css">
</head>
<body>
    <%--顶部--%>
    <%@ include file="../common/top.jsp" %>

    <!-- 主内容区 -->
    <div class="content">
        <div class="list mt10">
            <ul>
                <c:forEach var="item" items="${list}" varStatus="num">
                    <li>
                        <a class="to-detail" href="javascript:">
                            <img class="logo" src="${up}/image/bp.jpg" alt="">
                            <div class="info">
                                <h3 class="name">${item.pname}/(${item.quality})</h3>
                                <span class="time">单价：&yen;${item.price}</span>
                            </div>
                            <h4 class="name"><span style="color:red">总价：&yen;${item.ptotal}</span></h4>
                        </a>
                        <div class="down-btns btns2">
                            <p class="desc">数量：
                                <input id="${item.tpid}" type="text" value="${item.tpcount}" style="width:60px;"
                                       onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
                                       onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}">
                            </p>
                            <a class="btn btn-down" href="javascript:" onclick="submit(${item.tpid})">提交</a>
                        </div>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>

    <%--分页--%>
    <div class="page">
        <div>
            <c:choose>
                <c:when test="${totalPage == 0}">
                    <span class="current">暂无数据</span>
                </c:when>
                <c:when test="${totalPage == 1}">
                    <span class="current">${totalPage}</span>
                </c:when>
                <c:when test="${totalPage == currPageNumber}">
                    <a class="prev" href="${cyw }/backpack/pbProductList?currentPageNumber=${currPageNumber-1}">上一页</a>
                </c:when>
                <c:when test="${currPageNumber<totalPage && currPageNumber>1}">
                    <a class="prev" href="${cyw }/backpack/pbProductList?currentPageNumber=1">首页</a>
                    <a class="prev" href="${cyw }/backpack/pbProductList?currentPageNumber=${currPageNumber-1}">上一页</a>
                    <a class="prev" href="${cyw }/backpack/pbProductList?currentPageNumber=${currPageNumber+1}">下一页</a>
                    <a class="prev" href="${cyw }/backpack/pbProductList?currentPageNumber=${totalPage}">尾页</a>
                </c:when>
                <c:otherwise>
                    <a class="prev" href="${cyw }/backpack/pbProductList?currentPageNumber=${currPageNumber+1}">下一页</a>
                </c:otherwise>
            </c:choose>
        </div>
    </div>

    <script src="../js/jquery-3.4.1.min.js"></script>
    <script src="../js/swiper.min.js"></script>
    <script src="../js/script.js"></script>

    <%--提交--%>
    <script>
        function submit(id){
            // 获取商品id和相应商品的输入数量
            var pid = id;
            var number = document.getElementById(pid).value;
            //alert(pid+"======="+number);
            $.ajax({
                url: "${up}/tup/submit",
                type: "post",
                data: {"tpid":id, "tpcount":number},
                success: function (data) {
                    //console.log(JSON.stringify(data));
                    if (data.code == 0 && data.data == 1) {
                        alert("提交成功");
                        window.location.reload();//刷新页面
                    }
                }
            });
        }
    </script>

</body>
</html>
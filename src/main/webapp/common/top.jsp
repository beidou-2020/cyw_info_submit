<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%@ include file="taglibs.jsp" %>
<html>

<!-- Head -->
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>顶部</title>
    <link rel="stylesheet" href="../css/head.css">
</head>

<!-- Body -->
<body>
<!-- 顶部导航 -->
<div class="hd-main clearfix" id="header">
    <div class="navs">
        <%--<span class="separate"></span>
        <span class="separate"></span>--%>
        <a class="def-nav" href="${up}/product/pageData">首页</a>
    </div>
    <div class="navs">
        <%--<span class="separate"></span>
        <span class="separate"></span>--%>
        <a class="def-nav" href="${up}/backpack/pbProductList">我的背包</a>
    </div>
    <div class="navs">
        <%--<span class="separate"></span>
        <span class="separate"></span>--%>
        <a class="def-nav" href="${cyw}/tup/orderList">我的订单</a>
    </div>
    <div class="info">
        <ul>
            <li class="info-i user-name has-pulldown">
                <c:if test="${empty sessionScope.currUserInfo}">
                    <span class="name top-username"><a href="${cyw}/">登录</a></span>
                </c:if>
                <c:if test="${!empty sessionScope.currUserInfo}">
                    <span class="name top-username">${sessionScope.currUserInfo.name}</span>
                </c:if>

                <%--<div class="pulldown user-info">
                    <em class="arrow"></em>
                    <div class="content">
                        <span class="li"><a href="">个人资料</a></span>
                        <span class="li"><a href="">修改密码</a></span>
                        <span class="separate-li no-height"></span>
                        <span class="li"><a href="javascript:;" id="signout">退出</a></span>
                    </div>
                </div>--%>
            </li>
            <li class="info-i no-separate default-text has-pulldown">
                <em class="f-icon pull-arrow"></em>
                <span class="more" hideFocus="hideFocus"><a href="javascript:;"
                                                            onclick="exit(${sessionScope.currUserInfo.name})">退出</a></span>
                <%--<div class="pulldown more-info">
                    <em class="arrow"></em>
                    <div class="content">
                        <span class="separate-li no-height"></span>
                        <span class="li"><a href="">版本更新</a></span>
                        <span class="li"><a href="">帮助中心</a></span>
                        <span class="li"><a href="">问题反馈</a></span>
                        <span class="li"><a href="">权利声明</a></span>
                    </div>
                </div>--%>
            </li>
        </ul>
    </div>
</div>
</body>
<!-- //Body -->

<script type="text/javascript">
    function exit(useranme) {
        $.ajax({
            type: "post",
            url: "${cyw}/exit",
            success: function (data) {
                if (data.code == 0) {
                    alert("当前登录用户" + useranme + "退出成功");
                    window.location.reload();//刷新页面
                }
            }
        });
    }

    /*$(document).ready(function(){
        $(".def-nav,.info-i").hover(function(){
            $(this).find(".pulldown-nav").addClass("hover");
            $(this).find(".pulldown").show();
        },function(){
            $(this).find(".pulldown").hide();
            $(this).find(".pulldown-nav").removeClass("hover");
        });
    });*/
</script>

</html>
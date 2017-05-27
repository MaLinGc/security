<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <security:csrfMetaTags/>
    <title>Material Admin</title>

    <!-- Vendor CSS -->
    <link href="${staticPath}vendors/bower_components/animate.css/animate.min.css" rel="stylesheet">
    <link href="${staticPath}vendors/bower_components/material-design-iconic-font/dist/css/material-design-iconic-font.min.css"
          rel="stylesheet">

    <!-- CSS -->
    <link href="${staticPath}css/app_1.min.css" rel="stylesheet">
    <link href="${staticPath}css/app_2.min.css" rel="stylesheet">
    <link href="${staticPath}vendors/bower_components/datatables.net-dt/css/jquery.dataTables.min.css" rel="stylesheet">

    <script src="${staticPath}vendors/bower_components/jquery/dist/jquery.min.js"></script>
    <script src="${staticPath}vendors/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
</head>
<body>
<div class="login-content">
    <div class="lc-block toggled">
        <form action="${path }/login" method="post" class="lcb-form" id="form">
            <security:csrfInput/>
            <c:if test="${SPRING_SECURITY_LAST_EXCEPTION != null}">
                <div>
                    <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message }"/>
                </div>
            </c:if>
            <div class="input-group m-b-20">
                <span class="input-group-addon"><i class="zmdi zmdi-account"></i></span>
                <div class="fg-line">
                    <input name="username" type="text" class="form-control" placeholder="Username">
                </div>
            </div>
            <div class="input-group m-b-20">
                <span class="input-group-addon"><i class="zmdi zmdi-male"></i></span>
                <div class="fg-line">
                    <input name="password" type="password" class="form-control" placeholder="Password">
                </div>
            </div>

            <div class="checkbox">
                <label>
                    <input name="remember-me" type="checkbox" value="true">
                    <i class="input-helper"></i>
                    Keep me signed in
                </label>
            </div>
            <button type="submit" class="btn btn-login btn-success btn-float"><i class="zmdi zmdi-arrow-forward"></i></button>
        </form>

        <div class="lcb-navigation">
            <a onclick="submitForm()" href="javascript:void(0)" data-ma-block="#l-register"><i class="zmdi zmdi-plus"></i> <span>Register</span></a>
            <a data-ma-block="#l-forget-password"><i>?</i><span>Forgot Password</span></a>
        </div>
    </div>
</div>

<script src="${staticPath}vendors/bower_components/jquery/dist/jquery.min.js"></script>
<script src="${staticPath}vendors/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<script src="${staticPath}vendors/bower_components/Waves/dist/waves.min.js"></script>
<script src="${staticPath}js/app.min.js"></script>

<script>
    $(document).ajaxSend(function (e, xhr) {
        var csrfHeader = $("meta[name='_csrf_header']").attr("content");
        var csrfToken = $("meta[name='_csrf']").attr("content");
        xhr.setRequestHeader(csrfHeader,csrfToken)
    });
    function submitForm() {
        $.ajax({
            url: '${path }/login',
            data: $("#form").serialize(),
            type: 'post',
            success: function (result) {
                console.info(result)
            }
        })
    }
</script>
</body>
</html>

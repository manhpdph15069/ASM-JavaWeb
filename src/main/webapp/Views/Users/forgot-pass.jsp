<%--
  Created by IntelliJ IDEA.
  User: phamd
  Date: 12/21/2021
  Time: 1:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Forgot Password</title>
    <%@include file="/common/head.jsp" %>
    <!--
        
    TemplateMo 556 Catalog-Z
    
    https://templatemo.com/tm-556-catalog-z
    
    -->
</head>
<body>


<%@include file="/common/header.jsp" %>

<div class="container-fluid tm-mt-60">
    <div class="row tm-mb-50">
        <div class="col-lg-12 col-12 mb-5">
            <h2 style="text-align: center" class="tm-text-primary mb-5">Forgot Password</h2>
<%--            <form id="login-form" action="login" method="POST" class="tm-contact-form mx-auto">--%>
                <div class="form-group">
                    <input type="email" id="email" name="email" class="form-control rounded-0" placeholder="Email" required />
                </div>
                <div class="form-group tm-text-right">
                    <button id="sendBtn" type="submit" class="btn btn-primary">Send</button>
                </div>
                <h5 style="color: red" id="message"></h5>
<%--            </form>--%>
        </div>
    </div>
</div> <!-- container-fluid, tm-container-content -->
<%@include file="/common/footer.jsp" %>

</body>
<script>
    $('#sendBtn').click(function (){
        $('#message').text('');
        var email = $('#email').val();
        var formData = {'email':email};
        $.ajax({
            url: 'forgotPass',
            type: 'POST',
            data: formData
        }).then(function (data) {
            $('#message').text('New Password send to email');
            setTimeout(function () {
                window.location.href = 'http://localhost:8080/AAAA_war_exploded/index';
            },5*1000);
        }).fail(function (error) {
            $('#message').text('Tài khoản không tồn tại');
        });
    });
</script>
</html>

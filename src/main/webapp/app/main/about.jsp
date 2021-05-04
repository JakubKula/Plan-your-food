<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>About</title>
</head>
<body>
<%@include file="/header.jsp"%>
<section class="padding-medium story bg-light" id="about">
    <div class="container d-flex justify-content-center align-items-center">
        <div class="row">
            <div class="col-4 mr-4">
                <div class="div-img">
                </div>
            </div>

            <div class="col-7 ml-4">
                <h1 class="pb-1">${topic}</h1>
                <p>${description}
                </p>
            </div>
        </div>
    </div>
</section>
<%@include file="/footer.jsp"%>
</body>
</html>
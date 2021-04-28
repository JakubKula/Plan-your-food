<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<%@include file="/header.jsp"%>
<section class="dashboard-section">
    <div class="container pt-4 pb-4">
        <div class="border-dashed view-height">
            <div class="container w-25">
                <form class="padding-small text-center" action="/logout" method="post">
                    <h1 class="text-color-darker">Zalogowany jako ${name}</h1>
                    <p>Czy chcesz sie wylogować?</p>
                    <button class="btn btn-color rounded-0" type="submit">Wyloguj</button>
                </form>
            </div>
        </div>
    </div>
</section>
<%@include file="/footer.jsp"%>
</body>
</html>
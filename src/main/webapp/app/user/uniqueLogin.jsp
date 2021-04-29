<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login_not_unique</title>
</head>
<body>
<%@include file="/header.jsp"%>
<section class="dashboard-section">
    <div class="container pt-4 pb-4">
        <div class="border-dashed view-height">
            <div class="container w-25">
                <!-- add name attribute for all inputs -->

                    <p>Wybrany login jest w użyciu, proszę wybrać inny.</p>
                    <button class="btn btn-color rounded-0" type="button">
                        <a href="<c:url value="/register"></c:url>">OK</a></button>

            </div>
        </div>
    </div>
</section>
<%@include file="/footer.jsp"%>
</body>
</html>

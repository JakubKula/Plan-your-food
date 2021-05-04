<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Contact</title>
</head>
<body>
<%@include file="/header.jsp"%>
<section class="last-info-section padding-small" id="contact">
    <div class="container">
        <div class="row">
            <div class="col">
                <h3 class="mb-4">${topic1}</h3>
                <p>${description1}</p>
            </div>
            <div class="col pl-4 ml-4">
                <h3 class="mb-4">${topic2}</h3>
                <ul class="container">
                    <c:forEach var="list" items="${list}" >
                            <li>${list}</li>
                    </c:forEach>
                </ul>
            </div>
            <div class="col">
                <h3 class="mb-4">${topic3}</h3>
                <div class="input-group mb-3">
                    <input type="text" class="form-control border-0 rounded-0" placeholder=""
                           aria-label="Recipient's username" aria-describedby="basic-addon2">
                    <div class="input-group-append">
                        <button class="input-group-text btn-color border-0 rounded-0" type="submit" id="basic-addon2"><a
                                href="index.html">Lorem</a></button>
                    </div>
                </div>
                <div class="container d-flex-row">
                    <a href="#">
                        <i class="fab fa-facebook-square mr-4 icon-social"></i>
                    </a>
                    <a href="#">
                        <i class="fab fa-twitter-square mr-4 icon-social"></i>

                    </a>
                    <a href="#">
                        <i class="fab fa-instagram icon-social"></i>
                    </a>
                </div>
            </div>
        </div>
    </div>
</section>
<%@include file="/footer.jsp"%>
</body>
</html>
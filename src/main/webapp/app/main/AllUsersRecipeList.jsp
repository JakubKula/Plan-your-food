<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" pageEncoding="UTF-8"%>

<%@include file="../../header.jsp"%>

<script src="js/app.js" type="text/javascript"></script>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<link rel="stylesheet" type="text/css" href="dt/datatables.css">
<script type="text/javascript" charset="utf8" src="dt/datatables.js"></script>

<section>
    <div class="row padding-small">
        <i class="fas fa-users icon-users"></i>
        <h1>Przepisy naszych użytkowników:</h1>
        <hr>
        <div class="orange-line w-100"></div>
    </div>
</section>

<%--    <div class="m-4 p-3 width-medium">--%>
<section class="mr-4 ml-4">
            <table class="display" style="width:100%" id="table" >
                <thead >
                <tr class="text-color-darker" style="display: flex;">
                    <th class="col-1">ID</th>
                    <th class="col-5">NAZWA</th>
                    <th class="col-5">OPIS</th>
                    <th class="col-1">AKCJE</th>
                </tr>
                </thead>
                <tbody class="text-color-lighter">
                <c:forEach var="list" items="${list}" >
                    <tr class="d-flex">
                        <td class="col-1">${list.id}</td>
<%--                        <c:set var="name" value="${list.id}" scope="session" />--%>
                        <td class="col-5" data-tag="${list.name}">
                                ${list.name}
                        </td>
                        <td class="col-5" data-tag="${list.description}">
                                ${list.description}
                        </td>
                        <td class="col-1">
                            <a href="/app/main/details?id=${list.id}" class="btn btn-info rounded-0 text-light">Szczegóły</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
</section>

<footer class="footer-section pt-3 pb-3">
    <div class="container text-center">
        <h5 class="text-light">Copyright <span class="footer-text-color">ZaplanujJedzonko.pl</span></h5>
    </div>
</footer>
<script src="js/app2.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
        crossorigin="anonymous"></script>
</body>
</html>
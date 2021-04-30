<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" pageEncoding="UTF-8"%>

<%@include file="../../header.jsp"%>

<script src="js/app.js" type="text/javascript"></script>

<section>
    <div class="row padding-small">
        <i class="fas fa-users icon-users"></i>
        <h1>Przepisy naszych użytkowników:</h1>
        <hr>
        <div class="orange-line w-100"></div>
    </div>
</section>

<section class="mr-4 ml-4">
    <div class="m-4 p-3 width-medium">
        <div class="dashboard-content border-dashed p-3 m-4 view-height">
            <div>
            <input id="tagInput" type="text" placeholder="Wyszukaj po nazwie lub opisie" style="margin: 0 auto; padding: 10px; font-size: 17px; border: 2px solid grey; width: 100%; ">
            </div>
            <table class="table border-bottom schedules-content">
                <thead>
                <tr class="d-flex text-color-darker">
                    <th scope="col" class="col-1">ID</th>
                    <th scope="col" class="col-5">NAZWA</th>
                    <th scope="col" class="col-5">OPIS</th>
                    <th scope="col" class="col-1">AKCJE</th>
                </tr>
                </thead>
                <c:forEach var="list" items="${list}" >
                    <tbody class="text-color-lighter">
                    <tr class="d-flex">
                        <th scope="row" class="col-1">${list.id}</th>
                        <c:set var="name" value="${list.id}" scope="session" />
                        <td class="col-5" data-tag="${list.name}">
                                ${list.name}
                        </td>
                        <td class="col-5" data-tag="${list.description}">
                                ${list.description}
                        </td>
                        <td class="col-1"><a href="/app/main/details?id=${list.id}" class="btn btn-info rounded-0 text-light">Szczegóły</a>
                        </td>
                    </tr>
                    </tbody>
                </c:forEach>
            </table>

        </div>
    </div>
    </div>
</section>

<footer class="footer-section pt-3 pb-3">
    <div class="container text-center">
        <h5 class="text-light">Copyright <span class="footer-text-color">ZaplanujJedzonko.pl</span></h5>
    </div>
</footer>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
        crossorigin="anonymous"></script>
</body>
</html>
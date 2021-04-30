<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<c:choose>
    <c:when test="${superAdmin}">
        <section class="dashboard-section">
            <div class="row dashboard-nowrap">
                <ul class="nav flex-column long-bg">
                    <li class="nav-item">
                        <a class="nav-link" href="/desktop">
                            <span>Pulpit</span>
                            <i class="fas fa-angle-right"></i>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/app/recipe/list">
                            <span>Przepisy</span>
                            <i class="fas fa-angle-right"></i>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/app-schedules.html">
                            <span>Plany</span>
                            <i class="fas fa-angle-right"></i>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/edit/user/data">
                            <span>Edytuj dane</span>
                            <i class="fas fa-angle-right"></i>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link disabled" href="/edit/user/password">
                            <span>Zmień hasło</span>
                            <i class="fas fa-angle-right"></i>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/super/admin">
                            <span>Użytkownicy</span>
                            <i class="fas fa-angle-right"></i>
                        </a>
                    </li>
                </ul>
    </c:when>
    <c:otherwise>
        <section class="dashboard-section">
        <div class="row dashboard-nowrap">
        <ul class="nav flex-column long-bg">
            <li class="nav-item">
                <a class="nav-link" href="/desktop">
                    <span>Pulpit</span>
                    <i class="fas fa-angle-right"></i>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/app/recipe/list">
                    <span>Przepisy</span>
                    <i class="fas fa-angle-right"></i>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/app-schedules.html">
                    <span>Plany</span>
                    <i class="fas fa-angle-right"></i>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/edit/user/data">
                    <span>Edytuj dane</span>
                    <i class="fas fa-angle-right"></i>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link disabled" href="/edit/user/password">
                    <span>Zmień hasło</span>
                    <i class="fas fa-angle-right"></i>
                </a>
            </li>
        </ul>
    </c:otherwise>
</c:choose>
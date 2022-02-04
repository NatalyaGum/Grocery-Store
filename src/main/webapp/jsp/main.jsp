<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="pagecontent"/>
<c:set var="path" value="${pageContext.request.contextPath}"/>


<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <meta charset="utf-8">

    <title><fmt:message key="title"/></title>
</head>
<body>
<header>

    <div class="navbar navbar-dark bg-dark shadow-sm">

        <div class="container">
            <br>
            <a href="${pageContext.request.contextPath}/controller?command=go_to_main_page"
               class="navbar-brand d-flex align-items-center">

                <H1><strong>&divonx;WebStore</strong></H1>
            </a>


            <c:if test="${sessionScope.role eq 'guest'}">
                <div >
                    <form class="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3" method="post"
                          action="${pageContext.request.contextPath}/controller?command=sign_in">
                        <input class="form-control form-control-dark" type="email" name="email"
                               value="<c:out value="${requestScope.user.email}"/>" required placeholder=
                                   <fmt:message
                                           key="sign_up.email.placeholder"/> pattern="(([A-Za-z\d._]+){5,25}@([A-Za-z]+){3,10}\.([a-z]+){2,3})"/>

                        <input class="form-control form-control-dark" type="password" name="password"
                               value="<c:out value="${requestScope.user.password}"/>" required placeholder=
                                   <fmt:message key="sign_in.password.title"/> pattern="\S{6,20}"/>

                        <div class="text-end">

                            <button type="submit" class="btn btn-outline-light me-2"><fmt:message
                                    key="sign_in.title"/></button>

                            <a href="${pageContext.request.contextPath}/controller?command=go_to_registration">
                                <button type="button" class="btn btn-outline-light me-2"><fmt:message
                                        key="sign_up.submit"/></button>
                            </a>
                            </div>

                    </form></div>

                </div>
            </c:if>



            <div>
                <p><a href="${pageContext.request.contextPath}/controller?command=change_locale&language=EN">EN</a>
                </p>
                <p>
                    <a href="${pageContext.request.contextPath}/controller?command=change_locale&language=RU">RU</a></p>
            </div>
        </div>
    </div>

    <c:if test="${not empty message}"><p ><fmt:message key="${message}"/></p></c:if>

    <div class="container">
        <div class="row">
            <div class="col-sm-4 offset-md-1 py-4">
                <h4 class="text-black"><fmt:message key="sign_up.submit"/></h4>
                <ul class="list-unstyled">
                    <li><a href="${pageContext.request.contextPath}/controller?command=go_to_product_add" class="text-black"><fmt:message key="add_product"/></a></li>
                    <li><a href="#" class="text-black">Лайк на Facebook</a></li>
                    <li><a href="#" class="text-black">Напишите мне</a></li>
                </ul>
            </div>
        </div>
    </div>

</header>

<main>

    <section class="py-5 text-center container">
        <div class="row py-lg-5">
            <div class="col-lg-6 col-md-8 mx-auto">
                <h1 class="fw-light">Доставка по всему городу </h1>
                <p class="lead text-muted">в течении 2 часов после заказа</p>
                <p>
                    <a href="#" class="btn btn-primary my-2">Главный призыв к действию</a>
                    <a href="#" class="btn btn-secondary my-2">Вторичное действие</a>
                </p>
                <div id="headerTitle"><fmt:message key="title"/></div>
            </div>
        </div>
    </section>

    <div class="album py-5 bg-light">
        <div class="container">

            <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
                <div class="col">
                    <c:forEach var="element" items="${users_list}">
                    <div class="card shadow-sm">
                        <svg class="bd-placeholder-img card-img-top" width="100%" height="225"
                             xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: Эскиз"
                             preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title>
                            <rect width="100%" height="100%" fill="#55595c"/>
                            <text x="50%" y="50%" fill="#eceeef" dy=".3em">Эскиз</text>
                        </svg>

                        <div class="card-body">
                            <p class="card-text"> Email ${element.email}</p>
                            <div class="d-flex justify-content-between align-items-center">
                                <div class="btn-group">
                                    <button type="button" class="btn btn-sm btn-outline-secondary">Смотреть</button>
                                    <button type="button" class="btn btn-sm btn-outline-secondary">Редактировать
                                    </button>
                                </div>
                                <small class="text-muted">9 mins</small>
                            </div>
                        </div>
                    </div>
                </div>
                </c:forEach>
                <div class="col">
                    <div class="card shadow-sm">
                        <svg class="bd-placeholder-img card-img-top" width="100%" height="225"
                             xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: Эскиз"
                             preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title>
                            <rect width="100%" height="100%" fill="#55595c"/>
                            <text x="50%" y="50%" fill="#eceeef" dy=".3em">Эскиз</text>
                        </svg>

                        <div class="card-body">
                            <p class="card-text">Это более широкая карточка с вспомогательным текстом ниже как
                                естественный ввод к дополнительному контенту. Этот контент немного длиннее.</p>
                            <div class="d-flex justify-content-between align-items-center">
                                <div class="btn-group">
                                    <button type="button" class="btn btn-sm btn-outline-secondary">Смотреть</button>
                                    <button type="button" class="btn btn-sm btn-outline-secondary">Редактировать
                                    </button>
                                </div>
                                <small class="text-muted">9 mins</small>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="card shadow-sm">
                        <svg class="bd-placeholder-img card-img-top" width="100%" height="225"
                             xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: Эскиз"
                             preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title>
                            <rect width="100%" height="100%" fill="#55595c"/>
                            <text x="50%" y="50%" fill="#eceeef" dy=".3em">Эскиз</text>
                        </svg>

                        <div class="card-body">
                            <p class="card-text">Это более широкая карточка с вспомогательным текстом ниже как
                                естественный ввод к дополнительному контенту. Этот контент немного длиннее.</p>
                            <div class="d-flex justify-content-between align-items-center">
                                <div class="btn-group">
                                    <button type="button" class="btn btn-sm btn-outline-secondary">Смотреть</button>
                                    <button type="button" class="btn btn-sm btn-outline-secondary">Редактировать
                                    </button>
                                </div>
                                <small class="text-muted">9 mins</small>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col">
                    <div class="card shadow-sm">
                        <svg class="bd-placeholder-img card-img-top" width="100%" height="225"
                             xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: Эскиз"
                             preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title>
                            <rect width="100%" height="100%" fill="#55595c"/>
                            <text x="50%" y="50%" fill="#eceeef" dy=".3em">Эскиз</text>
                        </svg>

                        <div class="card-body">
                            <p class="card-text">Это более широкая карточка с вспомогательным текстом ниже как
                                естественный ввод к дополнительному контенту. Этот контент немного длиннее.</p>
                            <div class="d-flex justify-content-between align-items-center">
                                <div class="btn-group">
                                    <button type="button" class="btn btn-sm btn-outline-secondary">Смотреть</button>
                                    <button type="button" class="btn btn-sm btn-outline-secondary">Редактировать
                                    </button>
                                </div>
                                <small class="text-muted">9 mins</small>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="card shadow-sm">
                        <svg class="bd-placeholder-img card-img-top" width="100%" height="225"
                             xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: Эскиз"
                             preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title>
                            <rect width="100%" height="100%" fill="#55595c"/>
                            <text x="50%" y="50%" fill="#eceeef" dy=".3em">Эскиз</text>
                        </svg>

                        <div class="card-body">
                            <p class="card-text">Это более широкая карточка с вспомогательным текстом ниже как
                                естественный ввод к дополнительному контенту. Этот контент немного длиннее.</p>
                            <div class="d-flex justify-content-between align-items-center">
                                <div class="btn-group">
                                    <button type="button" class="btn btn-sm btn-outline-secondary">Смотреть</button>
                                    <button type="button" class="btn btn-sm btn-outline-secondary">Редактировать
                                    </button>
                                </div>
                                <small class="text-muted">9 mins</small>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="card shadow-sm">
                        <svg class="bd-placeholder-img card-img-top" width="100%" height="225"
                             xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: Эскиз"
                             preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title>
                            <rect width="100%" height="100%" fill="#55595c"/>
                            <text x="50%" y="50%" fill="#eceeef" dy=".3em">Эскиз</text>
                        </svg>

                        <div class="card-body">
                            <p class="card-text">Это более широкая карточка с вспомогательным текстом ниже как
                                естественный ввод к дополнительному контенту. Этот контент немного длиннее.</p>
                            <div class="d-flex justify-content-between align-items-center">
                                <div class="btn-group">
                                    <button type="button" class="btn btn-sm btn-outline-secondary">Смотреть</button>
                                    <button type="button" class="btn btn-sm btn-outline-secondary">Редактировать
                                    </button>
                                </div>
                                <small class="text-muted">9 mins</small>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col">
                    <div class="card shadow-sm">
                        <svg class="bd-placeholder-img card-img-top" width="100%" height="225"
                             xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: Эскиз"
                             preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title>
                            <rect width="100%" height="100%" fill="#55595c"/>
                            <text x="50%" y="50%" fill="#eceeef" dy=".3em">Эскиз</text>
                        </svg>

                        <div class="card-body">
                            <p class="card-text">Это более широкая карточка с вспомогательным текстом ниже как
                                естественный ввод к дополнительному контенту. Этот контент немного длиннее.</p>
                            <div class="d-flex justify-content-between align-items-center">
                                <div class="btn-group">
                                    <button type="button" class="btn btn-sm btn-outline-secondary">Смотреть</button>
                                    <button type="button" class="btn btn-sm btn-outline-secondary">Редактировать
                                    </button>
                                </div>
                                <small class="text-muted">9 mins</small>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="card shadow-sm">
                        <svg class="bd-placeholder-img card-img-top" width="100%" height="225"
                             xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: Эскиз"
                             preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title>
                            <rect width="100%" height="100%" fill="#55595c"/>
                            <text x="50%" y="50%" fill="#eceeef" dy=".3em">Эскиз</text>
                        </svg>

                        <div class="card-body">
                            <p class="card-text">Это более широкая карточка с вспомогательным текстом ниже как
                                естественный ввод к дополнительному контенту. Этот контент немного длиннее.</p>
                            <div class="d-flex justify-content-between align-items-center">
                                <div class="btn-group">
                                    <button type="button" class="btn btn-sm btn-outline-secondary">Смотреть</button>
                                    <button type="button" class="btn btn-sm btn-outline-secondary">Редактировать
                                    </button>
                                </div>
                                <small class="text-muted">9 mins</small>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="card shadow-sm">
                        <svg class="bd-placeholder-img card-img-top" width="100%" height="225"
                             xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: Эскиз"
                             preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title>
                            <rect width="100%" height="100%" fill="#55595c"/>
                            <text x="50%" y="50%" fill="#eceeef" dy=".3em">Эскиз</text>
                        </svg>

                        <div class="card-body">
                            <p class="card-text">Это более широкая карточка с вспомогательным текстом ниже как
                                естественный ввод к дополнительному контенту. Этот контент немного длиннее.</p>
                            <div class="d-flex justify-content-between align-items-center">
                                <div class="btn-group">
                                    <button type="button" class="btn btn-sm btn-outline-secondary">Смотреть</button>
                                    <button type="button" class="btn btn-sm btn-outline-secondary">Редактировать
                                    </button>
                                </div>
                                <small class="text-muted">9 mins</small>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</main>

<footer class="text-muted py-5">
    <div class="container">
        <p class="float-end mb-1">
            <a href="#">Вернуться наверх</a>
        </p>
        <p class="mb-1"> &copy; WebShop</p>

    </div>
</footer>


</body>
</html>


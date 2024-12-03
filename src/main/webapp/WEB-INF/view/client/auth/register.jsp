<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
            <!DOCTYPE html>
            <html lang="en">

            <head>
                <meta charset="utf-8" />
                <meta http-equiv="X-UA-Compatible" content="IE=edge" />
                <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
                <meta name="description" content="" />
                <meta name="author" content="" />
                <title>Register - DN Coffee</title>
                <link href="/css/styles.css" rel="stylesheet" />
                <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
                <style>
                    .toast-container {
                        position: fixed;
                        top: 20px;
                        right: 20px;
                        z-index: 1000;
                    }

                    .toast {
                        background: white;
                        border-left: 4px solid #28a745;
                        border-radius: 4px;
                        box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
                        margin-bottom: 10px;
                        min-width: 300px;
                        opacity: 0;
                        padding: 15px 20px;
                        transform: translateX(100%);
                        transition: all 0.3s ease-in-out;
                    }

                    .toast.show {
                        opacity: 1;
                        transform: translateX(0);
                    }

                    .toast-header {
                        color: #28a745;
                        font-weight: bold;
                        margin-bottom: 5px;
                    }

                    .toast-body {
                        color: #666;
                    }

                    .toast-close {
                        position: absolute;
                        right: 10px;
                        top: 10px;
                        background: none;
                        border: none;
                        color: #999;
                        cursor: pointer;
                    }

                    .toast-close:hover {
                        color: #666;
                    }
                </style>
            </head>

            <body class="bg-primary">
                <div id="layoutAuthentication">
                    <div id="layoutAuthentication_content">
                        <main>
                            <div class="container">
                                <div class="row justify-content-center">
                                    <div class="col-lg-7">
                                        <div class="card shadow-lg border-0 rounded-lg mt-5">
                                            <div class="card-header">
                                                <h3 class="text-center font-weight-light my-4">Create Account</h3>
                                            </div>
                                            <div class="card-body">
                                                <form:form method="post" action="/register"
                                                    modelAttribute="registerUser">
                                                    <c:set var="errorPassword">
                                                        <form:errors path="confirmPassword"
                                                            cssClass="invalid-feedback" />
                                                    </c:set>
                                                    <c:set var="errorEmail">
                                                        <form:errors path="email" cssClass="invalid-feedback" />
                                                    </c:set>
                                                    <c:set var="errorFirstName">
                                                        <form:errors path="firstName" cssClass="invalid-feedback" />
                                                    </c:set>
                                                    <div class="row mb-3">
                                                        <div class="col-md-6">
                                                            <div class="form-floating mb-3 mb-md-0">
                                                                <form:input
                                                                    class="form-control ${not empty errorFirstName ? 'is-invalid' : ''}"
                                                                    type="text" placeholder="Enter your first name"
                                                                    path="firstName" />
                                                                <label for="inputFirstName">First name</label>
                                                                ${errorFirstName}
                                                            </div>
                                                        </div>
                                                        <div class="col-md-6">
                                                            <div class="form-floating">
                                                                <form:input class="form-control" type="text"
                                                                    placeholder="Enter your last name"
                                                                    path="lastName" />
                                                                <label for="inputLastName">Last name</label>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="form-floating mb-3">
                                                        <form:input
                                                            class="form-control ${not empty errorEmail ? 'is-invalid' : ''}"
                                                            type="email" placeholder="name@example.com" path="email" />
                                                        <label>Email address</label>
                                                        ${errorEmail}
                                                    </div>
                                                    <div class="row mb-3">
                                                        <div class="col-md-6">
                                                            <div class="form-floating mb-3 mb-md-0">
                                                                <form:input
                                                                    class="form-control ${not empty errorPassword ? 'is-invalid' : ''}"
                                                                    type="password" placeholder="Create a password"
                                                                    path="password" />
                                                                <label>Password</label>
                                                                ${errorPassword}
                                                            </div>
                                                        </div>
                                                        <div class="col-md-6">
                                                            <div class="form-floating mb-3 mb-md-0">
                                                                <form:input class="form-control" type="password"
                                                                    placeholder="Confirm password"
                                                                    path="confirmPassword" />
                                                                <label>Confirm Password</label>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="mt-4 mb-0">
                                                        <div class="d-grid">
                                                            <button class="btn btn-primary btn-block">
                                                                Create Account
                                                            </button>
                                                        </div>
                                                    </div>
                                                </form:form>
                                            </div>
                                            <div class="card-footer text-center py-3">
                                                <div class="small"><a href="/login">Have an account? Go to login</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </main>
                    </div>

                </div>
                <div class="toast-container">
                    <c:if test="${registerSuccess}">
                        <div class="toast" id="registerToast">
                            <button class="toast-close" onclick="closeToast()">&times;</button>
                            <div class="toast-header">
                                Đăng ký thành công!
                            </div>
                            <div class="toast-body">
                                Vui lòng kiểm tra email ${email} để xác nhận tài khoản của bạn.
                            </div>
                        </div>
                    </c:if>
                </div>
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
                    crossorigin="anonymous"></script>
                <script src="/js/scripts.js"></script>
                <script>
                    // Hiển thị toast khi trang tải xong
                    document.addEventListener('DOMContentLoaded', function () {
                        const toast = document.getElementById('registerToast');
                        if (toast) {
                            setTimeout(() => {
                                toast.classList.add('show');
                            }, 100);

                            // Tự động ẩn toast sau 5 giây
                            setTimeout(() => {
                                closeToast();
                            }, 5000);
                        }
                    });

                    // Hàm đóng toast
                    function closeToast() {
                        const toast = document.getElementById('registerToast');
                        if (toast) {
                            toast.classList.remove('show');
                            setTimeout(() => {
                                toast.remove();
                            }, 300);
                        }
                    }
                </script>
            </body>

            </html>
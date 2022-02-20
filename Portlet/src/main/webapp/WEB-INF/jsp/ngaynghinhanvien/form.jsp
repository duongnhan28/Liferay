<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ include file="../init.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.1/themes/base/jquery-ui.css" rel="stylesheet" />
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.1/jquery-ui.min.js"></script>
    <script>
        $(document).ready(function () {
            $('input[id$=tbDate]').datepicker({dateFormat: 'yy-mm-dd'});
            $('input[id$=tbDate2]').datepicker({dateFormat: 'yy-mm-dd'});

        });
    </script>
</head>

<portlet:actionURL var="addNewURL">
    <portlet:param name="action" value="addNew" />
</portlet:actionURL>
<portlet:renderURL var="homeUrl">
    <portlet:param name="action" value="" />
</portlet:renderURL>

<c:if test="${empty item.id or item.id == 0}">
    <c:set var="title">
        Thêm
    </c:set>
</c:if>
<c:if test="${item.id > 0}">
    <c:set var="title">
        Chỉnh sửa
    </c:set>
</c:if>

<liferay-ui:error key="alert-error"
                  message="Your request was failed!." />

<div class="basic-form-area mg-b-15">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-12">
                <div class="sparkline12-list shadow-reset">
                    <div class="sparkline12-hd">
                        <div class="main-sparkline12-hd text-center">
                            <h1 class="title">${title}</h1>
                            <div class="sparkline12-outline-icon">
                                <span class="sparkline12-collapse-link"><i class="fa fa-chevron-up"></i></span>
                                <span><i class="fa fa-wrench"></i></span>
                                <span class="sparkline12-collapse-close"><i class="fa fa-times"></i></span>
                            </div>
                        </div>
                    </div>
                    <!-- end title -->

                    <!-- content -->
                    <div class="sparkline12-graph">
                        <div class="basic-login-form-ad">
                            <div class="row">
                                <div class="col-lg-12">
                                    <div class="all-form-element-inner">
                                        <form:form action="${addNewURL}" method="post" name="submitForm"
                                                   modelAttribute="item">
                                            <form:hidden path="id"></form:hidden>
                                            <div class="form-group-inner m-3">
                                                <div class="row">
                                                    <div class="col-lg-3">
                                                        <form:label path="soNgayDaNghi" class="login2 pull-right pull-right-pro">
                                                            Số ngày đã nghỉ<span class="text-danger">* </span> :</form:label>
                                                    </div>
                                                    <div class="col-lg-6">
                                                        <form:input path="soNgayDaNghi" id="soNgayDaNghi" class="form-control" type="number" disabled="true" />
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group-inner m-3">
                                                <div class="row">
                                                    <div class="col-lg-3">
                                                        <form:label path="soNgayNghiPhep" class="login2 pull-right pull-right-pro">
                                                            Số ngày nghỉ phép<span class="text-danger">* </span>:</form:label>
                                                    </div>
                                                    <div class="col-lg-6">
                                                        <form:input path="soNgayNghiPhep" id='soNgayNghiPhep' class="form-control" type="number" value="12.0" />
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group-inner m-3">
                                                <div class="row">
                                                    <div class="col-lg-3">
                                                        <form:label path="soNgayNghiTon" class="login2 pull-right pull-right-pro">
                                                            Số ngày nghỉ tồn<span class="text-danger">* </span>:</form:label>
                                                    </div>
                                                    <div class="col-lg-6">
                                                        <form:input path="soNgayNghiTon" id="soNgayNghiTon" class="form-control" type="number" value="0.0"/>
                                                    </div>
                                                </div>
                                            </div>
                                            <c:forEach var="role" items="${userRoles}">
                                                <c:if test="${(role.getName() == 'Administrator') || (role.getName() == 'Kế toán')}">
                                                    <div class="form-group-inner m-3">
                                                        <div class="row">
                                                            <div class="col-lg-3">
                                                                <label class="login2 pull-right pull-right-pro">Họ Tên
                                                                    <span class="text-danger">* </span></label>
                                                            </div>
                                                            <div class="col-lg-6">
                                                                <form:select path="nhanVien.id" class="form-control">
                                                                    <form:options items="${nhanVien}" itemLabel="tenNhanVien"
                                                                                  itemValue="id"/>
                                                                </form:select>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="form-group-inner m-3">
                                                        <div class="login-btn-inner">
                                                            <div class="row">
                                                                <div class="col-lg-3"></div>
                                                                <div class="col-lg-9">
                                                                    <div class="login-horizental cancel-wp pull-center">
                                                                        <button class="btn btn-sm btn-success login-submit-cs" type="submit">
                                                                            <i class="fas fa-save"></i>
                                                                        </button>
                                                                        <button class="btn btn-sm btn-danger login-submit-cs" type="button"
                                                                                onclick="location.href='${homeUrl}'">
                                                                            <i class="fas fa-arrow-circle-left"></i>
                                                                        </button>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </c:if>
                                                <c:if test="${role.getName() == 'Nhân viên'}">
                                                    <div class="form-group-inner m-3">
                                                        <div class="row">
                                                            <div class="col-lg-3">
                                                                <label class="login2 pull-right pull-right-pro">Họ
                                                                    tên<span class="text-danger">* :</span></label>
                                                            </div>
                                                            <div class="col-lg-6">
                                                                <label class="form-control">${nhanVien.tenNhanVien}</label>
                                                                <form:hidden path="nhanVien.userId" value="${nhanVien.userId}"/>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="form-group-inner m-3">
                                                        <div class="login-btn-inner">
                                                            <div class="row">
                                                                <div class="col-lg-3"></div>
                                                                <div class="col-lg-9">
                                                                    <div class="login-horizental cancel-wp pull-center">
                                                                        <button class="btn btn-sm btn-danger login-submit-cs"
                                                                                type="button"
                                                                                onclick="location.href='${homeUrl}'">
                                                                            <i class="fas fa-arrow-circle-left"></i>
                                                                        </button>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </c:if>
                                            </c:forEach>
                                        </form:form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ include file="../init.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script>
    $(document).ready(function () {
        $('input[id$=tbDate]').datepicker({dateFormat: 'yy-mm-dd'});
        $('input[id$=tbDate2]').datepicker({dateFormat: 'yy-mm-dd'});
    });
</script>

<portlet:actionURL var="addNewURL">
    <portlet:param name="action" value="addNew"/>
</portlet:actionURL>
<portlet:renderURL var="homeUrl">
    <portlet:param name="action" value=""/>
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
                  message="Your request was failed!."/>

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
                                                        <form:label path="start"
                                                                    class="login2 pull-right pull-right-pro">Ngay bat dau<span class="red">*</span>:</form:label>
                                                    </div>
                                                    <div class="col-lg-6">
                                                        <form:input path="start" id='tbDate' class="form-control"
                                                                    placeholder="dd/MM/yyyy"/>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group-inner m-3">
                                                <div class="row">
                                                    <div class="col-lg-3">
                                                        <form:label path="end"
                                                                    class="login2 pull-right pull-right-pro">Ngay ket thuc<span class="red">*</span>:</form:label>
                                                    </div>
                                                    <div class="col-lg-6">
                                                        <form:input path="end" id='tbDate2' class="form-control"
                                                                    placeholder="dd/MM/yyyy"/>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group-inner m-3">
                                                <div class="row">
                                                    <div class="col-lg-3">
                                                        <form:label path="title"
                                                                    class="login2 pull-right pull-right-pro">Tên ngay nghi<span class="red">*</span>:</form:label>
                                                    </div>
                                                    <div class="col-lg-6">
                                                        <form:input path="title" id="title" class="form-control"/>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group-inner m-3">
                                                <div class="row">
                                                    <div class="col-lg-3">
                                                        <form:label path="description"
                                                                    class="login2 pull-right pull-right-pro">Nội dung<span class="red">*</span>:</form:label>
                                                    </div>
                                                    <div class="col-lg-6">
                                                        <form:textarea rows="4" path="description" id="description"
                                                                       class="form-control"/>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group-inner m-3">
                                                <div class="login-btn-inner">
                                                    <div class="row">
                                                        <div class="col-lg-3"></div>
                                                        <div class="col-lg-9">
                                                            <div class="login-horizental cancel-wp pull-center">
                                                                <button class="btn btn-sm btn-primary login-submit-cs"
                                                                        type="submit">Lưu
                                                                </button>
                                                                <button class="btn btn-sm btn-danger login-submit-cs"
                                                                        type="button"
                                                                        onclick="location.href='${homeUrl}'">Quay Lại
                                                                </button>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>

                                        </form:form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- end content -->

                </div>
            </div>
        </div>
    </div>
</div>


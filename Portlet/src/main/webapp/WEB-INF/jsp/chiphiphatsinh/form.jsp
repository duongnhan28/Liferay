<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ include file="../init.jsp" %>
<link rel="stylesheet" href="//code.jquery.com/ui/1.13.0/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://code.jquery.com/ui/1.13.0/jquery-ui.js"></script>
<script>
        $(document).ready(function () {
            $('#txtDate').datepicker({
                changeMonth: true,
                changeYear: true,
                dateFormat: 'mm-yy',

                onClose: function () {
                    var iMonth = $("#ui-datepicker-div .ui-datepicker-month :selected").val();
                    var iYear = $("#ui-datepicker-div .ui-datepicker-year :selected").val();
                    $(this).datepicker('setDate', new Date(iYear, iMonth, 1));
                },

                beforeShow: function () {
                    if ((selDate = $(this).val()).length > 0) {
                        iYear = selDate.substring(selDate.length - 4, selDate.length);
                        iMonth = jQuery.inArray(selDate.substring(0, selDate.length - 5), $(this).datepicker('option', 'monthNames'));
                        $(this).datepicker('option', 'defaultDate', new Date(iYear, iMonth, 1));
                        $(this).datepicker('setDate', new Date(iYear, iMonth, 1));
                    }
                }
            });
        });
</script>
<style>
        .ui-datepicker-calendar {
            display: none;
        }
</style>

<portlet:actionURL var="addNewURL">
    <portlet:param name="action" value="addNew"/>
</portlet:actionURL>
<portlet:renderURL var="homeUrl">
    <portlet:param name="action" value=""/>
</portlet:renderURL>

<c:if test="${empty item.id or item.id == 0}">
    <c:set var="title">
        Chi phí phát sinh
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
                                                        <form:label path="tenChiPhi"
                                                                    class="login2 pull-right pull-right-pro">Tên chi phí
                                                            <span class="text-danger">* </span>:</form:label>
                                                    </div>
                                                    <div class="col-lg-6">
                                                        <form:input path="tenChiPhi" id="tenChiPhi"
                                                                    class="form-control"/>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group-inner m-3">
                                                <div class="row">
                                                    <div class="col-lg-3">
                                                        <form:label path="thang"
                                                                    class="login2 pull-right pull-right-pro">Tháng
                                                            <span class="text-danger">* </span>:</form:label>
                                                    </div>
                                                    <div class="col-lg-6">
                                                        <form:input path="thang" type='text' id='txtDate'
                                                                    class="form-control" placeholder="mm-yyyy"/>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group-inner m-3">
                                                <div class="row">
                                                    <div class="col-lg-3">
                                                        <form:label path="soTien"
                                                                    class="login2 pull-right pull-right-pro">Số tiền
                                                            <span class="text-danger">* </span>:</form:label>
                                                    </div>
                                                    <div class="col-lg-6">
                                                        <form:input path="soTien" id="soTien" class="form-control"/>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group-inner m-3">
                                                <div class="row">
                                                    <div class="col-lg-3">
                                                        <form:label path="noiDung"
                                                                    class="login2 pull-right pull-right-pro">Nội dung
                                                            <span class="text-danger">* </span>:</form:label>
                                                    </div>
                                                    <div class="col-lg-6">
                                                        <form:textarea rows="4" path="noiDung" id="noiDung"
                                                                       class="form-control"/>
                                                    </div>
                                                </div>
                                            </div>
                                            <c:forEach var="role" items="${userRoles}">
                                                <c:if test="${(role.getName() == 'Administrator') || (role.getName() == 'Kế toán')}">
                                                    <div class="form-group-inner m-3">
                                                        <div class="row">
                                                            <div class="col-lg-3">
                                                                <form:label path="nhanVien" class="login2 pull-right pull-right-pro">Nhân viên có CPPS
                                                                    <span class="text-danger">* </span>:</form:label>
                                                            </div>
                                                            <div class="col-lg-6">
                                                                <form:select path="nhanVien.id" class="form-control">
                                                                    <form:options items="${list}" itemLabel="tenNhanVien"
                                                                                  itemValue="id" class="form-control"/>
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
                                                                        <button class="btn btn-sm btn-success login-submit-cs"
                                                                                type="submit">
                                                                            <i class="fas fa-save"></i>
                                                                        </button>
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

                    <!-- end content -->

                </div>
            </div>
        </div>
    </div>
</div>


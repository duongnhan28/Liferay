<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ include file="../init.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<portlet:defineObjects/>

<portlet:actionURL var="add">
    <portlet:param name="action" value="add"/>
</portlet:actionURL>
<portlet:actionURL var="send">
    <portlet:param name="action" value="send"/>
</portlet:actionURL>
<portlet:actionURL var="cc">
    <portlet:param name="action" value="cc"/>
</portlet:actionURL>

<portlet:renderURL var="homeUrl">
    <portlet:param name="action" value=""/>
</portlet:renderURL>


<c:if test="${empty item.id or item.id == 0}">
    <c:set var="tieuDe">
        Thêm mới
    </c:set>
</c:if>
<c:if test="${item.id > 0}">
    <c:set var="tieuDe">
        Chỉnh sửa
    </c:set>

</c:if>

<liferay-ui:error key="alert-error"
                  message="Yêu cầu của bạn thực hiện không thành công!."/>
<div class="basic-form-area mg-b-15">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-12">
                <div class="sparkline12-list shadow-reset">
                    <div class="sparkline12-hd">
                        <div class="main-sparkline12-hd text-center">
                            <h1 class="tieude">${tieuDe}</h1>
                        </div>
                    </div>
                    <div class="sparkline12-graph">
                        <div class="basic-login-form-ad">
                            <div class="row">
                                <div class="col-lg-12">
                                    <div class="all-form-element-inner">
                                        <form:form action="${add}" method="post" modelAttribute="item" id ="send-form">
                                        <form:hidden path="id"/>
                                        <div class="form-group-inner m-3">
                                            <div class="row">
                                                <div class="col-lg-3">
                                                    <form:label path="ngayBatDauOT"
                                                                class="login2 pull-right pull-right-pro">Ngày bắt đầu OT<span class="red">*</span>:</form:label>
                                                </div>
                                                <div class="col-lg-3">
                                                    <form:input path="ngayBatDauOT" id="datepicker"
                                                                class="form-control" placeholder="dd/MM/yyyy"/>
                                                    <form:errors path="ngayBatDauOT" cssClass="error text-danger"/>
                                                </div>
                                                <div class="col-lg-1">
                                                    <form:label path="ngayKetThucOT"
                                                                class="login2 pull-right pull-right-pro">Đến<span class="red">*</span>:</form:label>
                                                </div>
                                                <div class="col-lg-3">
                                                    <form:input path="ngayKetThucOT" id="datepicker1"
                                                                class="form-control" placeholder="dd/MM/yyyy"/>
                                                    <form:errors path="ngayKetThucOT" cssClass="error text-danger"/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group-inner m-3">
                                            <div class="row">
                                                <div class="col-lg-3">
                                                    <form:label path="timeOT"
                                                                class="login2 pull-right pull-right-pro">Thời gian OT
                                                        <span class="red">*</span>:</form:label>

                                                </div>
                                                <div class="col-lg-7">
                                                    <form:input path="timeOT" id="timeOT" class="form-control"/>
                                                    <form:errors path="timeOT" cssClass="error text-danger"/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group-inner m-3">
                                            <div class="row">
                                                <div class="col-lg-3">
                                                    <form:label path="lyDo"
                                                                class="login2 pull-right pull-right-pro">Lý do <span
                                                            class="red">*</span>:</form:label>
                                                </div>
                                                <div class="col-lg-7">
                                                    <form:textarea path="lyDo" id="lyDo" class="form-control"/>
                                                    <form:errors path="lyDo" cssClass="error text-danger"/>

                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group-inner m-3">
                                            <div class="row">
                                                <div class="col-lg-3">
                                                    <label class="login2 pull-right pull-right-pro">Nhân Viên<span
                                                            class="red">*</span></label>
                                                </div>
                                                <div class="col-lg-7">
                                                    <label class="form-control">${nhanVien.tenNhanVien}</label>
                                                    <form:hidden path="nhanVien.id" value="${nhanVien.id}"/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group-inner m-3">
                                            <div class="row">
                                                <div class="col-lg-3">
                                                    <label class="login2 pull-right pull-right-pro">Tên Dự án<span
                                                            class="red">*</span></label>
                                                </div>
                                                <div class="col-lg-7">
                                                    <form:select path="duAn.id" class="form-control">
                                                        <form:options items="${duAn}" itemLabel="tenDuAn"
                                                                      itemValue="id"/>
                                                    </form:select>
                                                </div>
                                            </div>
                                        </div>
                                        <form:hidden path="tinhTrangDuyet" id="tinhTrangDuyet" value="CHODUYET"/>

                                        <div class="form-group-inner m-3">
                                            <div class="login-btn-inner">
                                                <div class="row">
                                                    <div class="col-lg-3"></div>
                                                    <div class="col-lg-9">
                                                        <div class="login-horizental cancel-wp pull-center">
                                                            <button type="button"
                                                                    class="btn btn-sm btn-primary login-submit-cs"
                                                                    data-toggle="modal" data-target="#myModal">Gửi mail
                                                            </button>
                                                            <button class="btn btn-sm btn-primary login-submit-cs"
                                                                    type="button"
                                                                    onclick="location.href='${homeUrl}'">Quay lại
                                                            </button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                            <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                                <div class="modal-dialog" role="document">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h5 class="modal-title">Gửi mail thông báo</h5>
                                                            <button type="button" class="close btn btn-danger" data-dismiss="modal" aria-label="Close"  id="close-button">
                                                                <span aria-hidden="true">&times;</span>
                                                            </button>
                                                        </div>
                                                        <div class="modal-body">
                                                            <form method="post" action="${cc}">
                                                                <div class="form-group">
                                                                    <input  type="text" class="form-control" name="from" value="${fromUser}" label="From" >
                                                                </div>
                                                                <div class="form-group">
                                                                    <label class="form-check-label">
                                                                        Admin
                                                                    </label>
                                                                    <input type="text" class="form-control" name="to" value="${emailAdmin}" label="to">
                                                                </div>

                                                                <div class="form-group">
                                                                    <label class="form-check-label">
                                                                       Nhân viên trong dự án
                                                                    </label>
                                                                    <c:forEach items="${emailPM}" var="email">
                                                                        <c:if test="${fromUser != email.emailAddress}">
                                                                            <input  type="checkbox" name="cc"
                                                                                   value="${email.emailAddress}">
                                                                            <label class="form-check-label">
                                                                                    ${email.screenName}
                                                                            </label>
                                                                        </c:if>
                                                                    </c:forEach>
                                                                </div>

                                                                <div class="form-group">
                                                                    <label  class="col-form-label">Chủ đề:</label>
                                                                    <input type="text" class="form-control" name="subject" value="Đăng ký OT"/>
                                                                </div>
                                                                <div class="form-group">
                                                                    <label class="col-form-label">Nội dung:</label>
                                                                    <textarea class="form-control" name="body">Đăng ký OT dự án:
                                                        </textarea>
                                                                </div>
                                                            </form>
                                                        </div>
                                                        <div class="modal-footer">
                                                            <button type="button" class="btn btn-danger" data-dismiss="modal" title="Quay lại">
                                                                <i class="fas fa-arrow-circle-left"></i>
                                                            </button>
                                                            <button type="button" class="btn btn-success" title="Gửi mail"  id="submit-button"
                                                                    value="Send Mail"
                                                                    onclick="submitForm();">
                                                                <i class="fas fa-file-import" ></i>
                                                            </button>
                                                        </div>
                                                    </div>
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

<script>
    function submitForm() {
        $("#close-button").trigger("click");
        $("#send-form").submit();
    }

    $(function () {
        $.datepicker.setDefaults({
            dateFormat: "dd/mm/yy",
            minDate: 0,
            onClose: function (date, inst) {
                $("#selectedDtaeVal").html(date);
            }
        });
        $("#datepicker").datepicker();
        $("#datepicker1").datepicker();
    });

</script>
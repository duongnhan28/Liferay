<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ include file="../init.jsp"%>
<portlet:actionURL var="add">
    <portlet:param name="action" value="add" />
</portlet:actionURL>

<portlet:renderURL var="homeUrl">
    <portlet:param name="action" value="" />
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
                  message="Yêu cầu của bạn thực hiện không thành công!." />

<div class="basic-form-area mg-b-15">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-12">
                <div class="sparkline12-list shadow-reset">
                    <div class="sparkline12-hd">
                        <div class="main-sparkline12-hd text-center">
                            <h4 class="tieude">${tieuDe}</h4>
                        </div>
                    </div>

                    <div class="sparkline12-graph">
                        <div class="basic-login-form-ad">
                            <div class="row">
                                <div class="col-lg-12">
                                    <div class="all-form-element-inner">
                                        <form:form action="${add}" method="post" name="submitForm"
                                                   modelAttribute="item">
                                            <form:hidden path="id"/>
                                            <div class="form-group-inner m-3">
                                                <div class="row">
                                                    <div class="col-lg-3">
                                                        <form:label path="tenVaiTro" class="login2 pull-right pull-right-pro">Tên dự án<span class="red">*</span>:</form:label>
                                                    </div>
                                                    <div class="col-lg-7">
                                                        <form:input path="tenVaiTro" id="name" class="form-control"/>
                                                        <form:errors path="tenVaiTro" cssClass="error text-danger"/>
                                                    </div>
                                                </div>
                                            </div>


                                            <div class="form-group-inner m-3">
                                                <div class="login-btn-inner">
                                                    <div class="row">
                                                        <div class="col-lg-3"></div>
                                                        <div class="col-lg-9">
                                                            <div class="login-horizental cancel-wp pull-center">
                                                                <button class="btn btn-sm btn-primary login-submit-cs" type="submit">Lưu</button>
                                                                <button class="btn btn-sm btn-primary login-submit-cs" type="button" onclick="location.href='${homeUrl}'">Quay lại</button>
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



<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ include file="../init.jsp"%>
<portlet:actionURL var="add">
    <portlet:param name="action" value="add" />
</portlet:actionURL>

<portlet:renderURL var="homeUrl">
    <portlet:param name="action" value="" />
    <portlet:param name="id" value="${duAnId}" />
    <portlet:param name="name" value="${tenDuAn}" />
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
                            <h4 class="tieude">Dự án:&nbsp;${tenDuAn}</h4>
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
                                            <form:hidden path="duAn.id" value="${duAnId}"/>
                                            <div class="form-group-inner m-3">
                                                <div class="row">
                                                    <div class="col-lg-2">
                                                        <label class="col-form-label">Nhân viên:&nbsp;</label><label class="text-danger">&#10031;</label>
                                                    </div>
                                                    <div class="col-lg-10">
                                                        <form:select path="nhanVien.id" class="form-control">
                                                            <form:options items="${listNhanVien}" itemLabel="tenNhanVien" itemValue="id"/>
                                                        </form:select>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="form-group-inner m-3">
                                                <div class="row">
                                                    <div class="col-lg-2">
                                                        <label class="col-form-label">Vai trò:&nbsp;</label><label class="text-danger">&#10031;</label>
                                                    </div>
                                                    <div class="col-lg-10">
                                                        <form:select path="vaiTro.id" class="form-control">
                                                            <form:options items="${listVaiTro}" itemLabel="tenVaiTro" itemValue="id"/>
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
                                                                <button class="btn btn-sm btn-primary login-submit-cs" type="submit">Lưu</button>
                                                                <button class="btn btn-sm btn-danger login-submit-cs" type="button" onclick="location.href='${homeUrl}'">Quay lại</button>
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



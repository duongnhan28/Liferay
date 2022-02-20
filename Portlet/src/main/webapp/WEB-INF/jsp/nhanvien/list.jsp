<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html" %>
<%@ page pageEncoding="UTF-8" %>
<%@ include file="../init.jsp" %>

<portlet:renderURL var="addNewURL">
    <portlet:param name="action" value="addNew"/>
</portlet:renderURL>
<portlet:renderURL var="addChiPhiURL">
    <portlet:param name="action" value="addChiPhi"/>
</portlet:renderURL>
<portlet:actionURL var="deleteURL">
    <portlet:param name="action" value="delete"/>
</portlet:actionURL>
<liferay-ui:success key="form-success"
                    message="Your request was successfully!."/>
<liferay-ui:error key="form-error"
                  message="Your request was failed!"/>
<div class="admin-dashone-data-table-area">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-12">
                <div class="sparkline8-list shadow-reset">
                    <div class="sparkline8-hd">
                        <div class="main-sparkline8-hd text-center">
                            <h1 class="title">Danh sách </h1>
                        </div>
                    </div>
                    <c:forEach var="role" items="${userRoles}">
                        <c:if test="${(role.getName() == 'Administrator') || (role.getName() == 'Kế toán')}">
                            <div class="col-lg-12">
                                <div class="datatable-dashv1-list custom-datatable-overright">

                                    <table id="table" class="table-fit table table-striped table-borderd">
                                        <thead>
                                        <tr>
                                            <th>STT</th>
                                            <th>Họ Tên</th>
                                            <th>Ngày sinh</th>
                                            <th>Chức vụ</th>
                                            <th class="align-middle text-center">Thao tác</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="nv" items="${listView}" varStatus="num">
                                            <tr>
                                                <td class="align-middle text-center">${nv.userId}</td>
                                                <td class="align-middle text-left">${nv.fullName}</td>
                                                <td class="align-middle text-left">
                                                    <fmt:formatDate value="${nv.birthday}" pattern="MM/dd/yyyy"/>
                                                </td>
                                                <td class="align-middle text-left">${nv.jobTitle}</td>
                                                <td class="align-middle text-center">
                                                    <button class="btn btn-info"
                                                            onclick="location.href='${addNewURL}&userId=${nv.userId}'">
                                                        <i class="fas fa-pencil-alt"></i></button>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </c:if>

                        <c:if test="${role.getName() == 'Nhân viên'}">
                            <div class="col-lg-12">
                                <div class="datatable-dashv1-list custom-datatable-overright">

                                    <table id="table1" class="table-fit table table-striped table-borderd">
                                        <thead>
                                        <tr>
                                            <th>STT</th>
                                            <th>Họ Tên</th>
                                            <th>Ngày sinh</th>
                                            <th>Giới tính</th>
                                            <th>Số điện thoại</th>
                                            <th>Lương chính thức</th>
                                            <th>Thuế TNCN</th>
                                            <th class="align-middle text-center">Thao tác</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <tr>
                                            <td class="align-middle text-center">${nhanVien.userId}</td>
                                            <td class="align-middle text-left">${nhanVien.tenNhanVien}</td>
                                            <td class="align-middle text-left">
                                                <fmt:formatDate value="${nhanVien.ngaySinh}" pattern="MM/dd/yyyy"/>
                                            </td>
                                            <c:choose>
                                                <c:when test="${nhanVien.gioiTinh}">
                                                    <td class="align-middle text-center text-success">Nam</td>
                                                </c:when>
                                                <c:otherwise>
                                                    <td class="align-middle text-center text-danger">Nu</td>
                                                </c:otherwise>
                                            </c:choose>
                                            <td class="align-middle text-left">${nhanVien.sdt}</td>
                                            <td class="align-middle text-left">${nhanVien.luongChinhThuc}</td>
                                            <td class="align-middle text-left">${nhanVien.thueTNCN}</td>
                                            <td class="align-middle text-center">
                                                <button class="btn btn-outline-warning"
                                                        onclick="location.href='${chiphiphatsinhUrl}&userId=${nhanVien.userId}'">
                                                    <i class="fas fa-money-check-edit-alt"></i></button>
                                                <button class="btn btn-outline-info"
                                                        onclick="location.href='${ngaynghinhanvienUrl}&userId=${nhanVien.userId}'">
                                                    <i class="fad fa-calendar-day"></i></button>
                                            </td>
                                        </tr>
                                        <tbody>
                                    </table>
                                </div>
                            </div>
                        </c:if>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</div>

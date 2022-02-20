<%@ page contentType="text/html" %>
<%@ page pageEncoding="UTF-8" %>
<%@ include file="../init.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<portlet:renderURL var="addNewURL">
    <portlet:param name="action" value="addNew"/>
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
                            <h1 class="title">Chi phí phát sinh</h1>
                        </div>
                    </div>
                    <c:forEach var="role" items="${userRoles}">
                        <c:if test="${(role.getName() == 'Administrator') || (role.getName() == 'Kế toán')}">
                            <div class="col-md-12 form-group text-left">
                                <button class="btn btn-success" onclick="location.href='${addNewURL}&id=0'">
                                    <i class="fad fa-plus-circle"></i></button>
                            </div>
                        </c:if>
                    </c:forEach>
                    <div class="col-lg-12">
                        <div class="datatable-dashv1-list custom-datatable-overright">

                            <table id="table" class="table-fit table table-striped table-borderd">
                                <thead>
                                <tr>
                                    <th>STT</th>
                                    <th>Tên chi phí</th>
                                    <th>Tháng</th>
                                    <th>Số tiền</th>
                                    <th>Nội dung</th>
                                    <th>Tên nhân viên</th>
                                    <th class="align-middle text-center">Thao tác</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="role" items="${userRoles}">
                                    <c:if test="${(role.getName() == 'Administrator') || (role.getName() == 'Kế toán')}">
                                        <c:forEach var="nv" items="${listView}" varStatus="num">
                                            <tr>
                                                <td class="align-middle text-center">${num.index +1}</td>
                                                <td class="align-middle text-left">${nv.tenChiPhi}</td>
                                                <td class="align-middle text-left">
                                                    <fmt:formatDate value="${nv.thang}" pattern="MM/yyyy"/></td>
                                                <td class="align-middle text-left">${nv.soTien}</td>
                                                <td class="align-middle text-left">${nv.noiDung}</td>
                                                <td class="align-middle text-left">${nv.nhanVien.tenNhanVien}</td>
                                                <td class="align-middle text-center">
                                                    <button class="btn btn-info"
                                                            onclick="location.href='${addNewURL}&id=${nv.id}'">
                                                        <i class="fas fa-pencil-alt"></i></button>
                                                    <button class="btn btn-danger"
                                                            onclick="location.href='${deleteURL}&id=${nv.id}'">
                                                        <i class="fas fa-trash-alt"></i></button>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </c:if>
                                    <c:if test="${role.getName() == 'Nhân viên'}">
                                        <c:forEach var="item" items="${CPPSByUser}" varStatus="num">
                                            <tr>
                                                <td class="align-middle text-center">${num.index +1}</td>
                                                <td class="align-middle text-left">${item.tenChiPhi}</td>
                                                <td class="align-middle text-left">
                                                    <fmt:formatDate value="${item.thang}" pattern="MM/yyyy"/></td>
                                                <td class="align-middle text-left">${item.soTien}</td>
                                                <td class="align-middle text-left">${item.noiDung}</td>
                                                <td class="align-middle text-left">${item.nhanVien.tenNhanVien}</td>
                                                <td class="align-middle text-center">
                                                    <button class="btn btn-outline-info"
                                                            onclick="location.href='${addNewURL}&id=${item.id}'">
                                                        <i class="fas fa-eye"></i></button>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </c:if>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


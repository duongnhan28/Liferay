<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../init.jsp"%>
<portlet:renderURL var="addduan">
    <portlet:param name="action" value="add" />
</portlet:renderURL>
<portlet:actionURL var="deleteduan">
    <portlet:param name="action" value="delete" />
</portlet:actionURL>

<liferay-ui:success key="form-success"
                    message="Yêu cầu của bạn đã được thực hiện thành công !." />
<liferay-ui:error key="form-error"
                  message="Yêu cầu của bạn thực hiện không thành công!" />

<div class="admin-dashone-data-table-area">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-12">
                <div class="sparkline8-list shadow-reset">
                    <div class="sparkline8-hd">
                        <div class="main-sparkline8-hd text-center">
                            <h4 class="tieude">Danh sách dự án</h4>
                        </div>
                    </div>
                    <c:forEach var="role" items="${userRoles}">
                        <c:if test="${(role.getName() == 'Administrator') || (role.getName() == 'Kế toán')}">
                            <div class="col-md-12 form-group text-right">
                                <button class="btn btn-info" onclick="location.href='${addduan}&productId=0'">Thêm mới</button>
                            </div>
                        </c:if>
                    </c:forEach>
                    <div class="col-lg-12">
                        <div class="datatable-dashv1-list custom-datatable-overright">

                            <table id="table" class="table-fit table table-striped table-borderd" >
                                <thead>
                                <tr>

                                    <th class="align-middle text-center">STT</th>
                                    <th class="align-middle text-center">Tên</th>
                                    <th class="align-middle text-center">Ngày bắt đầu</th>
                                    <th class="align-middle text-center">Ngày kết thúc</th>
                                    <th class="align-middle text-center">Trạng thái</th>
                                    <th class="align-middle text-center">Thao tác</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="item" items="${listView}" varStatus="num">
                                    <tr>
                                        <td class="align-middle text-center">${num.index +1}</td>
                                        <td class="align-middle text-center">${item.tenDuAn}</td>
                                        <td class="align-middle text-center">
                                            <fmt:formatDate value="${item.ngayBatDau}" pattern="dd/MM/yyyy" />
                                        </td>
                                        <td class="align-middle text-center">
                                            <fmt:formatDate value="${item.ngayKetThuc}" pattern="dd/MM/yyyy" />
                                        </td>
                                        <c:choose>
                                            <c:when test="${item.trangThai=='DANGDAUTHAU'}">
                                                <td class="align-middle text-center text-danger">Đang đấu thầu</td>
                                            </c:when>
                                            <c:when test="${item.trangThai=='DANGTHUCHIEN'}">
                                                <td class="align-middle text-center text-primary">Đang thực hiện</td>
                                            </c:when>
                                            <c:otherwise>
                                                <td class="align-middle text-center text-success">Đã hoàn thành</td>
                                            </c:otherwise>
                                        </c:choose>
                                        <td class="align-middle text-center">
                                            <c:forEach var="role" items="${userRoles}">
                                                <c:if test="${(role.getName() == 'Administrator') || (role.getName() == 'Kế toán')}">
                                                    <button class="btn btn-info w-6 p-1" title="Chỉnh sủa" onclick="location.href='${addduan}&id=${item.id}'"><i class="fas fa-pen"></i></button>
                                                    <button class="btn btn-danger w-6 p-1" title="Xóa" onclick="location.href='${deleteduan}&id=${item.id}'"><i class="fas fa-trash-alt"></i></button>
                                                </c:if>
                                            </c:forEach>
                                            <button  class="btn btn-success w-6 p-1" title="Nhân viên dự án" onclick="location.href='${nhanVienDuAnUrl}&id=${item.id}&name=${item.tenDuAn}'">
                                                <i class="fas fa-user-friends"></i>
                                            </button>
                                            <button class="btn btn-secondary w-6 p-1" title="Công tác" onclick="location.href='${congTacUrl}&id=${item.id}&name=${item.tenDuAn}'">
                                                <i class="fas fa-car"></i>
                                            </button>
                                            <button  class="btn btn-success w-6 p-1" title="OT" onclick="location.href='${oTURL}&id=${item.id}'">
                                                <i class="fas fa-clock"></i>
                                            </button>

                                        </td>
                                    </tr>
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
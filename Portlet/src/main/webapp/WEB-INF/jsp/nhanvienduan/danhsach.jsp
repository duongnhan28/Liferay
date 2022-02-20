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
                            <c:choose>
                                <c:when test="${listView.isEmpty()}">
                                    <h4 class="tieude">Dự án:&nbsp;${tenDuAn}</h4>
                                </c:when>
                                <c:when test="${listView != null}">
                                    <h4 class="tieude">Dự án:&nbsp;${listView.get(0).getDuAn().getTenDuAn()}</h4>
                                </c:when>
                            </c:choose>
                        </div>
                    </div>
                    <c:forEach var="role" items="${userRoles}">
                        <c:if test="${(role.getName() == 'Administrator') || (role.getName() == 'Kế toán')}">
                    <div class="col-md-12 form-group text-right">
                        <button class="btn btn-info" onclick="location.href='${addduan}&duAn.id=${duAnId}&name=${tenDuAn}'">Thêm mới</button>
                    </div>
                        </c:if>
                    </c:forEach>
                    <div class="col-lg-12">
                        <div class="datatable-dashv1-list custom-datatable-overright">
                            <table id="table" class="table-fit table table-striped table-borderd" >
                                <thead>
                                <tr>
                                    <th class="align-middle text-center">STT</th>
                                    <th class="align-middle text-center">Tên nhân viên</th>
                                    <th class="align-middle text-center">Vai trò</th>
                                    <th class="align-middle text-center">Thao tác</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="item" items="${listView}" varStatus="num">
                                    <tr>
                                        <td class="align-middle text-center">${num.index +1}</td>
                                        <td class="align-middle text-center">${item.nhanVien.tenNhanVien}</td>
                                        <td class="align-middle text-center">${item.vaiTro.tenVaiTro}</td>
                                        <td class="align-middle text-center">
                                            <c:forEach var="role" items="${userRoles}">
                                                <c:if test="${(role.getName() == 'Administrator') || (role.getName() == 'Kế toán')}">
                                            <button class="btn btn-info w-6 p-1" title="Chỉnh sửa" onclick="location.href='${addduan}&id=${item.id}&duAn.id=${duAnId}&name=${tenDuAn}'"><i class="fas fa-pen"></i></button>
                                            <button class="btn btn-danger w-6 p-1" title="Xóa" onclick="location.href='${deleteduan}&id=${item.id}'"><i class="fas fa-trash-alt"></i></button>
                                                </c:if>
                                            </c:forEach>
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

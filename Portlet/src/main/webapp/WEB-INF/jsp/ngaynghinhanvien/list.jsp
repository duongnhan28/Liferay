<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ include file="../init.jsp"%>
<portlet:renderURL var="addNew">
    <portlet:param name="action" value="addNew" />
</portlet:renderURL>
<portlet:actionURL var="delete">
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
                            <h1 class="tieude">Danh sách ngày nghỉ</h1>
                        </div>
                    </div>
                    <c:forEach var="role" items="${userRoles}">
                        <c:if test="${(role.getName() == 'Administrator') || (role.getName() == 'Kế toán')}">
                            <div class="col-md-12 form-group text-left">
                                <button class="btn btn-success" onclick="location.href='${addNew}&id=0'">
                                    <i class="fad fa-plus-circle"></i></button>
                            </div>
                        </c:if>
                    </c:forEach>
                    <div class="col-lg-12">
                        <div class="datatable-dashv1-list custom-datatable-overright">
                            <table id="table" class="table-fit table table-striped table-borderd" >
                                <thead>
                                <tr>
                                    <th class="align-middle text-center">STT</th>
                                    <th class="align-middle text-center">Họ Tên</th>
                                    <th class="align-middle text-center hidden">Số ngày đã nghỉ</th>
                                    <th class="align-middle text-center">Số ngày nghỉ phép</th>
                                    <th class="align-middle text-center">Số ngày nghỉ tồn</th>
                                    <th class="align-middle text-center">Thao tác</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="role" items="${userRoles}">
                                    <c:if test="${(role.getName() == 'Administrator') || (role.getName() == 'Kế toán')}">
                                        <c:forEach var="item" items="${listView}" varStatus="num">
                                            <tr>
                                                <td class="align-middle text-center">${num.index +1}</td>
                                                <td class="align-middle text-center">${item.nhanVien.tenNhanVien}</td>
                                                <td class="align-middle text-center ui-helper-hidden">${item.soNgayDaNghi}</td>
                                                <td class="align-middle text-center">${item.soNgayNghiPhep}</td>
                                                <td class="align-middle text-center">${item.soNgayNghiTon}</td>
                                                <td class="align-middle text-center">
                                                    <button class="btn btn-info" onclick="location.href='${addNew}&id=${item.id}'">
                                                        <i class="fas fa-pencil-alt"></i></button>
                                                    <button class="btn btn-danger" onclick="location.href='${delete}&id=${item.id}'">
                                                        <i class="fas fa-trash-alt"></i></button>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </c:if>
                                    <c:if test="${role.getName() == 'Nhân viên'}">
                                        <c:forEach var="nn" items="${ngayNghiByUser}" varStatus="num">
                                            <tr>
                                                <td class="align-middle text-center">${num.index +1}</td>
                                                <td class="align-middle text-center">${nn.nhanVien.tenNhanVien}</td>
                                                <td class="align-middle text-center ui-helper-hidden">${nn.soNgayDaNghi}</td>
                                                <td class="align-middle text-center">${nn.soNgayNghiPhep}</td>
                                                <td class="align-middle text-center">${nn.soNgayNghiTon}</td>
                                                <td class="align-middle text-center">
                                                    <button class="btn btn-outline-info"
                                                            onclick="location.href='${addNew}&id=${nn.id}'">
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

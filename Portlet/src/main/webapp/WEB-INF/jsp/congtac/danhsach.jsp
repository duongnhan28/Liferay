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
                    <c:forEach var="nv" items="${nhanVienDuAnDTOList}" varStatus="num">
                        <c:forEach var="nvda" items="${nhanVienDuAns}" varStatus="num">
                            <c:if test="${nv.nhanVien.id == nvda.nhanVien.id}" >
                    <div class="col-md-12 form-group text-right">
                        <button class="btn btn-info" onclick="location.href='${addduan}&duAn.id=${duAnId}&name=${tenDuAn}'">Thêm mới</button>
                    </div>
                            </c:if>
                        </c:forEach>
                    </c:forEach>
                    <div class="col-lg-12">
                        <div class="datatable-dashv1-list custom-datatable-overright">
                            <table id="table" class="table-fit table table-striped table-borderd" >
                                <thead>
                                <tr>
                                    <th class="align-middle text-center">STT</th>
                                    <th class="align-middle text-center">Người phụ trách</th>
                                    <th class="align-middle text-center">Ngày bắt đầu</th>
                                    <th class="align-middle text-center">Ngày kết thúc</th>
                                    <th class="align-middle text-center">Nhiệm vụ</th>
                                    <th class="align-middle text-center">Địa điểm</th>
                                    <th class="align-middle text-center">Ghi chú</th>
                                    <th class="align-middle text-center">Trạng thái</th>
                                    <th class="align-middle text-center">Thao tác</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="item" items="${listView}" varStatus="num">
                                    <tr>
                                        <td class="align-middle text-center">${num.index +1}</td>
                                        <td class="align-middle text-center">${item.nhanVien.tenNhanVien}</td>
                                        <td class="align-middle text-center">
                                            <fmt:formatDate value="${item.ngayBatDau}" pattern="dd/MM/yyyy" />
                                        </td>
                                        <td class="align-middle text-center">
                                            <fmt:formatDate value="${item.ngayKetThuc}" pattern="dd/MM/yyyy" />
                                        </td>
                                        <td class="align-middle text-center">${item.nhiemVu}</td>
                                        <td class="align-middle text-center">${item.diaDiem}</td>
                                        <td class="align-middle text-center">${item.ghiChu}</td>
                                        <c:choose>
                                            <c:when test="${item.trangThai=='MOICAPNHAT'}">
                                                <td class="align-middle text-center text-danger">Mới cập nhật</td>
                                            </c:when>
                                            <c:when test="${item.trangThai=='DADUYET'}">
                                                <td class="align-middle text-center text-primary">Đã duyệt</td>
                                            </c:when>
                                            <c:when test="${item.trangThai=='DAUNG'}">
                                                <td class="align-middle text-center text-success">Đã ứng</td>
                                            </c:when>
                                            <c:when test="${item.trangThai=='DAHOANUNG'}">
                                                <td class="align-middle text-center text-primary">Đã hoàn ứng</td>
                                            </c:when>
                                            <c:otherwise>
                                                <td class="align-middle text-center text-danger">Đã từ chối</td>
                                            </c:otherwise>
                                        </c:choose>

                                        <td class="align-middle text-center">
                                        <c:forEach var="nv" items="${nhanVienDuAnDTOList}" varStatus="num">
                                            <c:forEach var="nvda" items="${nhanVienDuAns}" varStatus="num">

                                                <c:if test="${nv.nhanVien.id == nvda.nhanVien.id}" >
                                                    <button class="btn btn-info w-6 p-1" title="Chỉnh sửa" onclick="location.href='${addduan}&id=${item.id}&duAn.id=${duAnId}&name=${tenDuAn}'">
                                                        <i class="fas fa-pen"></i>
                                                    </button>
                                                    <button class="btn btn-danger w-6 p-1" title="Xóa" onclick="location.href='${deleteduan}&id=${item.id}'">
                                                        <i class="fas fa-trash-alt"></i>
                                                    </button>
                                                </c:if>
                                            </c:forEach>
                                        </c:forEach>
                                            <button class="btn btn-success w-6 p-1" title="Chi tiết công tác" onclick="location.href='${chiTietCongTacUrl}&id=${item.id}&nhiemVu=${item.nhiemVu}&duAnId=${duAnId}&nhanVienId=${item.nhanVien.userId}&name=${item.duAn.tenDuAn}&trangThai=${item.trangThai}'">
                                                <i class="far fa-id-card"></i>
                                            </button>
                                            <button class="btn btn-warning w-6 p-1" title="Nhân viên công tác" onclick="location.href='${nhanVienCongTacUrl}&id=${item.id}&nhiemVu=${item.nhiemVu}&duAnId=${duAnId}&nhanVienId=${item.nhanVien.userId}&name=${item.duAn.tenDuAn}'">
                                                <i class="fas fa-user-friends"></i>
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

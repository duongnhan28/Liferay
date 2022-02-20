<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../init.jsp" %>
<portlet:renderURL var="addduan">
    <portlet:param name="action" value="add"/>
</portlet:renderURL>
<portlet:renderURL var="addTienUng">
    <portlet:param name="action" value="addTienUng"/>
</portlet:renderURL>
<portlet:actionURL var="deleteduan">
    <portlet:param name="action" value="delete"/>
</portlet:actionURL>
<portlet:actionURL var="sendMail">
    <portlet:param name="action" value="sendMail"/>
</portlet:actionURL>

<portlet:actionURL var="duyetChiphi">
    <portlet:param name="action" value="duyetChiphi"/>
</portlet:actionURL>

<liferay-ui:success key="form-success"
                    message="Yêu cầu của bạn đã được thực hiện thành công !."/>
<liferay-ui:error key="form-error"
                  message="Yêu cầu của bạn thực hiện không thành công!"/>
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
                                    <h4 class="tieude">Dự
                                        án:&nbsp;${listView.get(0).getCongTac().getDuAn().getTenDuAn()}</h4>
                                </c:when>
                            </c:choose>
                            <h4 class="tieude">Công tác:&nbsp;${nhiemVu}</h4>
                        </div>
                    </div>

                    <c:if test="${(userId == nhanVienId) && (trangThai != 'DADUYET')}">
                        <div class="col-md-12 form-group text-right">
                            <button class="btn btn-info"
                                    onclick="location.href='${addduan}&congTac.id=${id}&duAn.id=${duAnId}&userId=${userId}&name=${tenDuAn}&nhiemVu=${nhiemVu}&nhanVienId=${nhanVienId}&trangThai=${trangThai}'">
                                Thêm mới
                            </button>
                        </div>

                    </c:if>
                    <div class="col-lg-12">
                        <div class="datatable-dashv1-list custom-datatable-overright">

                            <table id="table" class="table-fit table table-striped table-borderd">
                                <thead>
                                <tr>

                                    <th class="align-middle text-center">STT</th>
                                    <th class="align-middle text-left">Tên chi phí</th>
                                    <th class="align-middle text-left">Đơn vị tính</th>
                                    <th class="align-middle text-center">Số lượng</th>
                                    <th class="align-middle text-right">Đơn giá</th>
                                    <th class="align-middle text-right">Thành tiền</th>
                                    <th class="align-middle text-center">Thao tác</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="item" items="${listView}" varStatus="num">
                                    <tr>
                                        <td class="align-middle text-center">${num.index +1}</td>
                                        <td class="align-middle text-left">${item.tenChiPhi}</td>
                                        <td class="align-middle text-left">${item.donViTinh}</td>
                                        <td class="align-middle text-center">${item.soLuong}</td>
                                        <td class="align-middle text-right">
                                            <fmt:formatNumber type="number" pattern="###,###" value="${item.donGia}"/>
                                        </td>
                                        <td class="align-middle text-right thanhTien">
                                            <fmt:formatNumber type="number" pattern="###,###"
                                                              value="${item.thanhTien}"/>
                                        </td>
                                        <td class="align-middle text-center">
                                            <c:if test="${(userId == nhanVienId) && (trangThai != 'DADUYET')}">
                                                <button class="btn btn-info w-6 p-1" title="Chỉnh sửa"
                                                        onclick="location.href='${addduan}&id=${item.id}&congTac.id=${id}&duAn.id=${duAnId}&userId=${userId}&name=${tenDuAn}&nhiemVu=${nhiemVu}&nhanVienId=${nhanVienId}&trangThai=${trangThai}'">
                                                    <i class="fas fa-pen"></i></button>
                                                <button class="btn btn-danger w-6 p-1" title="Xóa"
                                                        onclick="location.href='${deleteduan}&id=${item.id}'"><i
                                                        class="fas fa-trash-alt"></i></button>
                                            </c:if>
                                        </td>
                                    </tr>

                                </c:forEach>

                                <tr>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td class="align-middle text-right">Tổng tiền</td>
                                    <td class="align-middle text-right tongTien">

                                    </td>
                                    <td></td>
                                </tr>
                                </tbody>
                            </table>
                            <c:forEach var="nv" items="${nhanVienDuAnDTOList}" varStatus="num">
                                <c:forEach var="nvda" items="${nhanVienDuAns}" varStatus="num">

                                    <c:if test="${nv.nhanVien.id == nvda.nhanVien.id && (trangThai == 'MOICAPNHAT' || trangThai == 'DATUCHOI')}">
                                        <div class="d-flex justify-content-center">
                                            <button class="btn btn-sm btn-primary login-submit-cs m-2" type="button"
                                                    data-toggle="modal"
                                                    data-target="#myModalDuyet" onclick="myFunction(0,${id})">Duyệt
                                            </button>
                                            <button class="btn btn-sm btn-danger login-submit-cs m-2" type="button"
                                                    data-toggle="modal"
                                                    data-target="#myModal" onclick="myFunctionTuChoi(1,${id})">Từ chối
                                            </button>
                                        </div>
                                    </c:if>
                                </c:forEach>
                            </c:forEach>

                            <div class="modal fade" id="myModalDuyet" role="dialog" tabindex="-1" data-backdrop="false">
                                <div class="modal-dialog modal-full-height modal-right modal-notify modal-info"
                                     role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <p class="heading lead">Email duyệt công tác phí</p>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true" class="white-text"><i class="fas fa-window-close"></i></span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            <form method="post" action="${sendMail}" id="send-form">
                                                <input id="status" name="status" type="hidden" value="0"/>
                                                <input id="congTacId" name="congTacId" type="hidden"/>
                                                <div class="form-group">
                                                    <label class="col-form-label">From</label>
                                                    <input class="form-control"
                                                           name="from"
                                                           type="text"
                                                           value="${emailGui}"
                                                           label="From"/>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-form-label">To</label>
                                                    <input class="form-control"
                                                           name="to"
                                                           type="text"
                                                           value="${emailNhan}"
                                                           label="To"/>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-form-label">Subject</label>
                                                    <input class="form-control"
                                                           name="subject"
                                                           type="text"
                                                           value="Duyệt công tác phí"
                                                           label="Subject"/>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-form-label">Body</label>

                                                    <textarea class="form-control text-start" name="body">Dự án: ${tenDuAn}&#10;Nhiệm vụ: ${nhiemVu}

                                                    </textarea>
                                                </div>
                                                <div class="form-group d-flex justify-content-center">
                                                    <button type="button"
                                                            class="btn btn-primary waves-effect waves-light"
                                                            id="submit-button"
                                                            value="Send Mail"
                                                            onclick="submitForm();">
                                                        Send
                                                        <i class="fa fa-paper-plane ml-1"></i>
                                                    </button>
                                                    <button type="button" class="btn btn-outline-warning waves-effect ml-2" data-dismiss="modal">Cancel</button>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="modal fade" id="myModal" role="dialog" tabindex="-1" data-backdrop="false">
                                <div class="modal-dialog modal-full-height modal-right modal-notify modal-info"
                                     role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <p class="heading lead">Email từ chối công tác phí</p>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true" class="white-text"><i class="fas fa-window-close"></i></span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            <form method="post" action="${sendMail}" id="send-form-tuChoi">
                                                <input id="status1" name="status" type="hidden" value="0"/>
                                                <input id="congTacId1" name="congTacId" type="hidden"/>
                                                <div class="form-group">
                                                    <label class="col-form-label">From</label>
                                                    <input class="form-control"
                                                           name="from"
                                                           type="text"
                                                           value="${emailGui}"
                                                           label="From"/>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-form-label">To</label>
                                                    <input class="form-control"
                                                           name="to"
                                                           type="text"
                                                           value="${emailNhan}"
                                                           label="To"/>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-form-label">Subject</label>
                                                    <input class="form-control"
                                                           name="subject"
                                                           type="text"
                                                           value="Từ chối công tác phí"
                                                           label="Subject"/>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-form-label">Body</label>

                                                    <textarea class="form-control text-start" name="body">Dự án: ${tenDuAn}&#10;Nhiệm vụ: ${nhiemVu}&#10;Lý do:

                                                    </textarea>
                                                </div>
                                                <div class="form-group d-flex justify-content-center">
                                                    <button type="button"
                                                            class="btn btn-primary waves-effect waves-light"
                                                            id="submit-button1"
                                                            value="Send Mail"
                                                            onclick="submitFormTuChoi();">
                                                        Send
                                                        <i class="fa fa-paper-plane ml-1"></i>
                                                    </button>
                                                    <button type="button" class="btn btn-outline-warning waves-effect ml-2" data-dismiss="modal">Cancel</button>
                                                </div>
                                            </form>
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

    function myFunction(status, id) {
        document.getElementById("status").value = status;
        document.getElementById("congTacId").value = id;
        submitForm();
    }

    function myFunctionTuChoi(status, id) {
        document.getElementById("status1").value = status;
        document.getElementById("congTacId1").value = id;
    }

    function submitForm() {
        $("#close-button").trigger("click");
        $("#send-form").submit();
    }

    function submitFormTuChoi() {
        $("#close-button").trigger("click");
        $("#send-form-tuChoi").submit();
    }

    var total = $('td.tongTien'),
        td_thanhTien = $('td.thanhTien');

    total.text(function () {
        var sum = 0
        td_thanhTien.each(function () {
            sum += +Number($(this).text().replace(/[^0-9.-]+/g, ""));
        });
        var formatter = new Intl.NumberFormat('en-US', {
            style: 'decimal',
            maximumFractionDigits: 2,
        });
        return formatter.format(sum);
    })

</script>
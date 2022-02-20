<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ include file="../init.jsp" %>
<portlet:defineObjects/>
<portlet:renderURL var="add">
    <portlet:param name="action" value="add"/>
</portlet:renderURL>
<portlet:actionURL var="delete">
    <portlet:param name="action" value="delete"/>
</portlet:actionURL>
<portlet:actionURL var="sendMail">
    <portlet:param name="action" value="sendMail"/>
</portlet:actionURL>

<portlet:renderURL var="findDuAn">
    <portlet:param name="action" value=""/>
</portlet:renderURL>



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
                            <h1 class="tieude">Danh sách đăng ký OT</h1>
                        </div>
                    </div>
                    <div class="col-md-4 form-group text-left">
                        <c:forEach var="role" items="${userRoles}">
                            <c:if test="${(role.getName() == 'Administrator')}">
                                <label class="login2 pull-left pull-left-pro">Dự án</label>
                                <select class="form-control" name="id" id="selectDuAn">
                                    <option value="0" selected disabled hidden></option>
                                    <c:forEach var="item" items="${duAn}">
                                        <option value="${item.id}" id="${item.id}">${item.tenDuAn}</option>
                                    </c:forEach>
                                </select>
                            </c:if>
                            <c:if test="${role.getName() == 'Nhân viên'}">
                                <c:forEach var="nv" items="${nhanVienDuAnDTOList}" varStatus="num">
                                    <c:forEach var="nvda" items="${nhanVienDuAns}" varStatus="num">
                                        <c:if test="${nv.nhanVien.id == nvda.nhanVien.id && nvda.vaiTro.tenVaiTro == 'PM'}" >
                                            <label class="login2 pull-left pull-left-pro">Dự án</label>
                                            <select class="form-control" name="id" id="selectDuAn">
                                                <option value="0" selected disabled hidden></option>
                                                <c:forEach var="item" items="${duAn}">
                                                    <option value="${item.id}" id="${item.id}">${item.tenDuAn}</option>
                                                </c:forEach>
                                            </select>
                                        </c:if>
                                    </c:forEach>
                                </c:forEach>
                            </c:if>
                        </c:forEach>

                    </div>
                    <c:forEach var="role" items="${userRoles}">
                        <c:if test="${role.getName() == 'Nhân viên'}">
                            <c:forEach var="nv" items="${nhanVienDuAnDTOList}" varStatus="num">
                                <c:forEach var="nvda" items="${nhanVienDuAns}" varStatus="num">
                                    <c:if test="${nv.nhanVien.id == nvda.nhanVien.id && nvda.vaiTro.tenVaiTro == 'DEV'}" >
                                        <div class="col-md-12 form-group text-right">
                                            <button class="btn btn-info" onclick="location.href='${add}&duAnId=${id}'">Thêm mới</button>
                                        </div>
                                    </c:if>
                                </c:forEach>
                            </c:forEach>
                        </c:if>
                    </c:forEach>
                    <div class="col-lg-12">
                        <div class="datatable-dashv1-list custom-datatable-overright">
                            <table class="table-fit table table-striped table-borderd">
                                <thead>
                                <tr>
                                    <th class="align-middle text-center">STT</th>
                                    <th class="align-middle text-center">Dự án</th>
                                    <th class="align-middle text-center">Lý do</th>
                                    <th  class="align-middle text-center">Ngày bắt đầu OT</th>
                                    <th class="align-middle text-center">Ngày kết thúc OT</th>
                                    <th class="align-middle text-center">Thời gian OT(Giờ)</th>
                                    <th class="align-middle text-center">Nhân Viên</th>
                                    <th class="align-middle text-center">Trạng thái</th>
                                    <c:forEach var="nv" items="${nhanVienDuAnDTOList}" varStatus="num">
                                        <c:forEach var="nvda" items="${nhanVienDuAns}" varStatus="num">
                                            <c:if test="${nv.nhanVien.id == nvda.nhanVien.id}" >
                                                <th class="align-middle text-center">Thao tác</th>
                                            </c:if>
                                        </c:forEach>
                                    </c:forEach>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="role" items="${userRoles}">
                                    <c:if test="${role.getName() == 'Administrator'}">
                                        <c:forEach var="item" items="${listView}" varStatus="num">
                                            <tr>
                                            <td class="align-middle text-center">${num.index +1}</td>
                                            <td class="align-middle text-center">${item.duAn.tenDuAn}</td>
                                            <td class="align-middle text-center">${item.lyDo}</td>
                                            <td class="align-middle text-center">
                                                <fmt:formatDate value="${item.ngayBatDauOT}" pattern="dd/MM/yyyy"/>
                                            </td>
                                            <td class="align-middle text-center">
                                                <fmt:formatDate value="${item.ngayKetThucOT}" pattern="dd/MM/yyyy"/>
                                            </td>
                                            <td class="align-middle text-center">${item.timeOT}</td>
                                            <td class="align-middle text-center">${item.nhanVien.tenNhanVien}</td>
                                            <c:choose>
                                                <c:when test="${item.tinhTrangDuyet=='CHODUYET'}">
                                                    <td class="align-middle text-center text-success">Chờ duyệt</td>
                                                </c:when>
                                                <c:when test="${item.tinhTrangDuyet=='DADUYET'}">
                                                    <td class="align-middle text-center text-primary">Đã duyệt</td>
                                                </c:when>
                                                <c:otherwise>
                                                    <td class="align-middle text-center text-danger">Từ chối</td>
                                                </c:otherwise>
                                            </c:choose>
                                            </tr>
                                        </c:forEach>

                                    </c:if>
                                    <c:if test="${role.getName() == 'Nhân viên'}">
                                        <c:forEach var="nv" items="${nhanVienDuAnDTOList}" varStatus="num">
                                            <c:forEach var="nvda" items="${nhanVienDuAns}" varStatus="num">
                                                <c:if test="${nv.nhanVien.id == nvda.nhanVien.id && nvda.vaiTro.tenVaiTro == 'PM'}" >
                                                    <c:forEach var="item" items="${listView}" varStatus="num">
                                                        <tr>
                                                            <td hidden class="align-middle text-center">${item.id}</td>
                                                            <td class="align-middle text-center">${num.index +1}</td>
                                                            <td class="align-middle text-center">${item.duAn.tenDuAn}</td>
                                                            <td class="align-middle text-center">${item.lyDo}</td>
                                                            <td class="align-middle text-center">
                                                                <fmt:formatDate value="${item.ngayBatDauOT}" pattern="dd/MM/yyyy"/>
                                                            </td>
                                                            <td class="align-middle text-center">
                                                                <fmt:formatDate value="${item.ngayKetThucOT}" pattern="dd/MM/yyyy"/>
                                                            </td>
                                                            <td class="align-middle text-center">${item.timeOT}</td>
                                                            <td class="align-middle text-center">${item.nhanVien.tenNhanVien}</td>
                                                            <c:choose>
                                                                <c:when test="${item.tinhTrangDuyet=='CHODUYET'}">
                                                                    <td class="align-middle text-center text-success">Chờ duyệt</td>
                                                                </c:when>
                                                                <c:when test="${item.tinhTrangDuyet=='DADUYET'}">
                                                                    <td class="align-middle text-center text-primary">Đã duyệt</td>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <td class="align-middle text-center text-danger">Từ chối</td>
                                                                </c:otherwise>
                                                            </c:choose>
                                                            <c:if test="${item.tinhTrangDuyet=='CHODUYET'}">
                                                                <td align="center">
                                                                    <button type="button" data-toggle="modal"
                                                                            data-target="#myModalDuyet" onclick="myFunction(0,${item.id}, '${email[num.index].emailAddress}')"
                                                                            class="btn btn-success"><i class="fas fa-check"></i></button>
                                                                    <button type="button" data-toggle="modal"
                                                                            data-target="#myModalTuChoi" onclick="myFunctionTuChoi(1,${item.id},'${email[num.index].emailAddress}')"
                                                                            class="btn btn-danger"><i class="fa fa-ban" aria-hidden="true"></i></button>
                                                                </td>
                                                            </c:if>
                                                            <c:if test="${item.tinhTrangDuyet=='DADUYET' || item.tinhTrangDuyet=='TUCHOI' }">
                                                            <td align="center">
                                                                <button disabled type="button" data-toggle="modal"
                                                                        data-target="#myModalDuyet" onclick="myFunction(0,${item.id}, '${email[num.index].emailAddress}')"
                                                                        class="btn btn-success"><i class="fas fa-check"></i></button>
                                                                <button disabled type="button" data-toggle="modal"
                                                                        data-target="#myModalTuChoi" onclick="myFunctionTuChoi(1,${item.id},'${email[num.index].emailAddress}')"
                                                                        class="btn btn-danger"><i class="fa fa-ban" aria-hidden="true"></i></button>
                                                            </td>
                                                            </c:if>

                                                        </tr>
                                                    </c:forEach>
                                                </c:if>
                                            </c:forEach>
                                        </c:forEach>
                                        <c:forEach var="item" items="${otByUser}" varStatus="num">
                                            <tr>
                                                <td hidden class="align-middle text-center">${item.nhanVien.userId}</td>
                                                <td class="align-middle text-center">${num.index +1}</td>
                                                <td class="align-middle text-center">${item.duAn.tenDuAn}</td>
                                                <td class="align-middle text-center">${item.lyDo}</td>
                                                <td class="align-middle text-center">
                                                    <fmt:formatDate value="${item.ngayBatDauOT}" pattern="dd/MM/yyyy"/>
                                                </td>
                                                <td class="align-middle text-center">
                                                    <fmt:formatDate value="${item.ngayKetThucOT}" pattern="dd/MM/yyyy"/>
                                                </td>
                                                <td class="align-middle text-center">${item.timeOT}</td>
                                                <td class="align-middle text-center">${item.nhanVien.tenNhanVien}</td>
                                                <c:choose>
                                                    <c:when test="${item.tinhTrangDuyet=='CHODUYET'}">
                                                        <td class="align-middle text-center text-success">Chờ duyệt</td>
                                                    </c:when>
                                                    <c:when test="${item.tinhTrangDuyet=='DADUYET'}">
                                                        <td class="align-middle text-center text-primary">Đã duyệt</td>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <td class="align-middle text-center text-danger">Từ chối</td>
                                                    </c:otherwise>
                                                </c:choose>
                                                <td class="align-middle text-center">
                                                    <button class="btn btn-info"
                                                            onclick="location.href='${add}&id=${item.id}&duAnId=${item.duAn.id}'">
                                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                                             fill="currentColor" class="bi bi-pencil"
                                                             viewBox="0 0 16 16">
                                                            <path d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5 13.5 4.793 14.793 3.5 12.5 1.207 11.207 2.5zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293l6.5-6.5zm-9.761 5.175-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z"/>
                                                        </svg>
                                                    </button>
                                                    <button class="btn btn-danger"
                                                            onclick="location.href='${delete}&id=${item.id}'">
                                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                                             fill="currentColor" class="bi bi-trash"
                                                             viewBox="0 0 16 16">
                                                            <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"/>
                                                            <path fill-rule="evenodd"
                                                                  d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"/>
                                                        </svg>
                                                    </button>
                                                </td>

                                            </tr>
                                        </c:forEach>
                                    </c:if>
                                </c:forEach>

                                <div class="modal fade" id="myModalDuyet" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title">Gửi mail thông báo</h5>
                                                <button type="button" class="close btn btn-danger" data-dismiss="modal" aria-label="Close"  id="close-button">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                            </div>
                                            <div class="modal-body">
                                                <form method="post" action="${sendMail}"
                                                      id="send-form">
                                                    <input id="status" name="status" type="hidden"
                                                           value="0"/>
                                                    <input id="otId" name="otId" type="hidden"/>
                                                    <div class="form-group">
                                                        <label  class="col-form-label">From:</label>
                                                        <input type="text" class="form-control" name="from" value="${emailGui}">
                                                    </div>
                                                    <div class="form-group">
                                                        <label  class="col-form-label">To:</label>
                                                        <input type="text" class="form-control"  name = "to" id="email">
                                                    </div>
                                                    <div class="form-group">
                                                        <label  class="col-form-label">Chủ đề:</label>
                                                        <input type="text" class="form-control" name="subject" value="Duyệt đăng ký OT"/>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="col-form-label">Nội dung:</label>
                                                        <textarea class="form-control" name="body">Đồng ý OT dự án: ${tenDuAn}
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

                                <div class="modal fade" id="myModalTuChoi" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="exampleModalLabel">Gửi mail từ chối OT</h5>
                                                <button type="button" class="close btn btn-danger" data-dismiss="modal" aria-label="Close">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                            </div>
                                            <div class="modal-body">
                                                <form method="post" action="${sendMail}"
                                                      id="send-form-tuChoi">
                                                    <input id="status1" name="status" type="hidden"
                                                           value="0"/>
                                                    <input id="otId1" name="otId" type="hidden"/>
                                                    <div class="form-group">
                                                        <label  class="col-form-label">From:</label>
                                                        <input type="text" class="form-control" name="from" value="${emailGui}">
                                                    </div>
                                                    <div class="form-group">
                                                        <label  class="col-form-label">To:</label>
                                                                <input type="text" class="form-control"  name = "to" id="email1">
                                                    </div>

                                                    <div class="form-group">
                                                        <label  class="col-form-label">Chủ đề:</label>
                                                        <input type="text" class="form-control" name="subject" value="Từ chối OT"/>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="col-form-label">Nội dung:</label>
                                                        <textarea class="form-control" name="body">Từ chối OT: Dự án ${tenDuAn}&#10;Lý do:
                                                        </textarea>
                                                    </div>
                                                </form>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-danger" data-dismiss="modal" title="Quay lại">
                                                    <i class="fas fa-arrow-circle-left"></i>
                                                </button>
                                                <button type="button" class="btn btn-success" title="Gửi mail"  id="submit-button1"
                                                        value="Send Mail"
                                                        onclick="submitFormTuChoi();">
                                                    <i class="fas fa-file-import" ></i>
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                </tbody>
                            </table>
                            <script>
                                function myFunction(status, id, email) {
                                    document.getElementById("status").value = status;
                                    document.getElementById("otId").value = id;
                                    document.getElementById("email").value = email
                                    submitForm();
                                }

                                function myFunctionTuChoi(status, id,email1) {
                                    document.getElementById("status1").value = status;
                                    document.getElementById("otId1").value = id;
                                    document.getElementById("email1").value = email1;
                                }

                                function submitForm() {
                                    $("#close-button").trigger("click");
                                    $("#send-form").submit();
                                }

                                function submitFormTuChoi() {
                                    $("#close-button").trigger("click");
                                    $("#send-form-tuChoi").submit();
                                }


                                $("#selectDuAn").change(function(){
                                    var val = $(this).val();
                                    location.href="${findDuAn}&id=" + val;
                                });

                                window.onload = function() {
                                    var selItem = sessionStorage.getItem("SelItem");
                                    $('#selectDuAn').val(selItem);
                                }
                                $('#selectDuAn').change(function() {
                                    var selVal = $(this).val();
                                    sessionStorage.setItem("SelItem", selVal);
                                });


                            </script>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

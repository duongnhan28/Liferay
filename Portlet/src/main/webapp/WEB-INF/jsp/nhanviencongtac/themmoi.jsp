<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ include file="../init.jsp"%>
<portlet:actionURL var="add">
    <portlet:param name="action" value="add" />
</portlet:actionURL>

<portlet:renderURL var="homeUrl">
    <portlet:param name="action" value="" />
    <portlet:param name="id" value="${congTacId}" />
    <portlet:param name="duAnId" value="${duAnId}" />
    <portlet:param name="nhanVienId" value="${nhanVienId}" />
    <portlet:param name="name" value="${tenDuAn}" />
    <portlet:param name="nhiemVu" value="${nhiemVu}" />
    <portlet:param name="trangThai" value="trangThai" />
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
                            <h4 class="tieude">Công tác:&nbsp;${nhiemVu}</h4>
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
                                            <form:hidden path="congTac.id" value="${congTacId}"/>

                                            <div class="form-group-inner m-3">
                                                <div class="row">
                                                    <div class="col-lg-2">
                                                        <label class="col-form-label">Tên nhân viên:&nbsp;</label><label class="text-danger">&#10031;</label>
                                                    </div>
                                                    <div class="col-lg-10">
                                                        <form:select path="nhanVien.id" class="form-select" id="nhanVien" name="nhanVien" multiple="multiple">
                                                            <form:options items="${listNhanVien}" itemLabel="tenNhanVien" itemValue="id"/>
                                                            <form:options class="text-danger" items="${listView}" itemLabel="tenNhanVien" itemValue="id" disabled="true"/>
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
<script>
    $( function() {
        $.datepicker.setDefaults({
            dateFormat:"dd/mm/yy",
            changeMonth: true,
            changeYear: true,
            minDate: 0,
            onClose:function(date, inst){
                $("#selectedDtaeVal").html(date);
            }
        });

        $( "#datepicker" ).datepicker();
        $( "#datepicker2" ).datepicker();
    });

    $(document).ready(function(){

        $( "#duAnId" ).change(function() {
            $.ajax({
                url: "${findNhanVienByDuAn}" ,
                type: 'POST',
                datatype:'json',
                data: {
                    duAnId: $("#tenDuAn").val()
                },
                success: function(data){
                    var content= JSON.parse(data);
                    $('#nhanVien').html('');// to clear the previous option
                    $.each(content, function(i, nhanVien) {
                        $('#nhanVien').append($('<option>').text(nhanVien.name).attr('value', nhanVien.id));
                    });
                }
            });
        });
    });
</script>



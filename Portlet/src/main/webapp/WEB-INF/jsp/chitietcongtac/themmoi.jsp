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

<div class="basic-form-area mg-b-15" onclick="ex()">
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
                                                   modelAttribute="item" id="save">
                                            <form:hidden path="id"/>
                                            <form:hidden path="congTac.id" value="${congTacId}"/>
<%--                                            <c:if test="${empty item.id or item.id == 0}">--%>
<%--                                            <button class="add-row btn btn-info" type="button">--%>
<%--                                                Thêm chi phí--%>
<%--                                            </button>--%>
<%--                                            </c:if>--%>
                                            <table id="table" class="table-fit table table-striped table-borderd" >
                                                <thead>
                                                <tr>

                                                    <th class="align-middle text-center">STT</th>
                                                    <th class="align-middle text-center">Tên chi phí</th>
                                                    <th class="align-middle text-center">Đơn vị tính</th>
                                                    <th class="align-middle text-center">Số lượng</th>
                                                    <th class="align-middle text-center">Đơn giá</th>
                                                    <th class="align-middle text-center">Thành tiền</th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                <tr>
                                                    <td>1</td>
                                                    <td><form:input path="tenChiPhi" id="tenChiPhi" class="form-control text-left" name="tenChiPhi"/></td>
                                                    <td><form:input path="donViTinh" id="donViTinh" class="form-control text-left" name="donViTinh"/></td>
                                                    <td><form:input path="soLuong" id="soLuong" class="form-control text-center" onblur="findTotal()" name="soLuong"/></td>
                                                    <td><form:input path="donGia" id="donGia" class="form-control text-right CurrencyInput Currency" onblur="findTotal()" name="donGia"/></td>
                                                    <td><form:input path="thanhTien" id="thanhTien" class="form-control text-right Currency" name="thanhTien" readonly="true" /></td>

                                                </tr>
                                                <tr>
                                                    <td></td>
                                                    <td><form:errors path="tenChiPhi" cssClass="error text-danger" /></td>
                                                    <td><form:errors path="donViTinh" cssClass="error text-danger" /></td>
                                                    <td><form:errors path="soLuong" cssClass="error text-danger" /></td>
                                                    <td><form:errors path="donGia" cssClass="error text-danger" /></td>
                                                    <td><form:errors path="thanhTien" cssClass="error text-danger" /></td>

                                                </tr>

                                                </tbody>
                                            </table>



                                            <div class="form-group-inner m-3">
                                                <div class="login-btn-inner">
                                                    <div class="row">
                                                        <div class="col-lg-3"></div>
                                                        <div class="col-lg-9">
                                                            <div class="login-horizental cancel-wp pull-center">
                                                                <button class="btn btn-sm btn-primary login-submit-cs" type="button" id="saveChiPhi" onclick="submitChiPhi()">Lưu</button>
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
<script type="text/javascript">
    $('input.CurrencyInput').on('keyup', function() {

        var value = $(this).val().replace(/,/g, '');
        value = parseFloat(value).toLocaleString('en-US', {
            style: 'decimal',
            maximumFractionDigits: 2,

        });

        $(this).val(value);
    });

    $('input.Currency').on('click', function() {
        var value = this.value.replace(/,/g, '');
        this.value = parseFloat(value).toLocaleString('en-US', {
            style: 'decimal',
            maximumFractionDigits: 2,

        });
    });

    var lineNo = 2;
    $(document).ready(function () {
        $(".add-row").click(function () {
            markup =
                "<tr>" +
                "<td>" + lineNo + "</td>" +
                "<td><input id=\"tenChiPhi\" class=\"form-control text-left\" name=\"tenChiPhi\" required/></td>"+
                "<td><input id=\"donViTinh\" class=\"form-control text-left\" name=\"donViTinh\" /></td>"+
                "<td><input id=\"soLuong\" onblur=\"findTotal()\" class=\"soluong form-control text-center\" name=\"soLuong\" /></td>"+
                "<td><input id=\"donGia\" onblur=\"findTotal()\" class=\"form-control text-right\" name=\"donGia\" /></td>"+
                "<td><input id=\"thanhTien\" class=\"form-control text-right Currency\" name=\"thanhTien\" readonly/></td>"+
                "</tr>";
            tableBody = $("table tbody");
            tableBody.append(markup);
            lineNo++;
        });
    });

    function ex(){
        var donGia = document.getElementsByName('donGia');
        var formatter = new Intl.NumberFormat('en-US', {
            style: 'decimal',
            maximumFractionDigits: 2,
        });
        for(var i=0;i<donGia.length;i++){
            if (!isNaN(donGia[i].value) )
            {
                donGia[i].value = formatter.format(donGia[i].value);
            }
            // else {
            //     donGia[i].value = formatter.format(donGia[i].value);
            // }
        }
    }

    function findTotal(){
        var soLuong = document.getElementsByName('soLuong');
        var donGia = document.getElementsByName('donGia');
        var thanhTien = document.getElementsByName('thanhTien');
        var formatter = new Intl.NumberFormat('en-US', {
            style: 'decimal',
            maximumFractionDigits: 2,
        });

        for(var i=0;i<soLuong.length;i++){
            for(var j=0;j<donGia.length;j++){
                if(i==j)
                    var tot = parseInt(soLuong[i].value) * Number(donGia[j].value.replace(/[^0-9.-]+/g,""));
                thanhTien[i].value = formatter.format(tot);
            }
        }

    }

    function submitChiPhi() {
        var donGia = document.getElementsByName('donGia');
        var thanhTien = document.getElementsByName('thanhTien');
        for (var i=0; i<donGia.length; i++){
            donGia[i].value = Number(donGia[i].value.replace(/[^0-9.-]+/g,"")) ;
            thanhTien[i].value = Number(thanhTien[i].value.replace(/[^0-9.-]+/g,""));
        }

        $("#save").submit();
    }
</script>




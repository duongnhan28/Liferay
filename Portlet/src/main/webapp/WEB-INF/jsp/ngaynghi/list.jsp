<%@ page contentType="text/html" %>
<%@ page pageEncoding="UTF-8" %>
<%@ include file="../init.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<portlet:renderURL var="addNewURL">
    <portlet:param name="action" value="addNew" />
</portlet:renderURL>
<portlet:actionURL var="deleteURL">
    <portlet:param name="action" value="delete" />
</portlet:actionURL>

<liferay-ui:success key="form-success"
                    message="Your request was successfully!." />
<liferay-ui:error key="form-error"
                  message="Your request was failed!" />

<div class="admin-dashone-data-table-area">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-12">
                <div class="sparkline8-list shadow-reset">
                    <div class="sparkline8-hd">
                        <div class="main-sparkline8-hd text-center">
                            <h1 class="title">Ngay nghi</h1>
                        </div>
                    </div>
                    <div class="col-md-12 form-group text-left">
                        <button class="btn btn-success" onclick="location.href='${addNewURL}&id=0'">
                            <i class="fad fa-plus-circle"></i></button>
                    </div>
                    <div class="col-lg-12">
                        <div class="datatable-dashv1-list custom-datatable-overright">

                            <table id="table" class="table-fit table table-striped table-borderd" >
                                <thead>
                                <tr>
                                    <th>STT</th>
                                    <th>start</th>
                                    <th>end</th>
                                    <th>title</th>
                                    <th>description</th>
                                    <th class="align-middle text-center">Thao tác</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="e" items="${events}" varStatus="num">
                                    <tr>
                                        <td class="align-middle text-center">${num.index +1}</td>
                                        <td class="align-middle text-left">
                                            <fmt:formatDate value="${e.start}" pattern="dd/MM/yyyy" /></td>
                                        <td class="align-middle text-left">
                                            <fmt:formatDate value="${e.end}" pattern="dd/MM/yyyy" /></td>
                                        <td class="align-middle text-left">${e.title}</td>
                                        <td class="align-middle text-left">${e.description}</td>
                                        <td class="align-middle text-center">
                                            <button class="btn btn-info" onclick="location.href='${addNewURL}&id=${e.id}'">
                                                <i class="fas fa-pencil-alt"></i></button>
                                            <button class="btn btn-danger" onclick="location.href='${deleteURL}&id=${e.id}'">
                                                <i class="fas fa-trash-alt"></i></button>
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
<%@ include file="./calendar.jsp"%>

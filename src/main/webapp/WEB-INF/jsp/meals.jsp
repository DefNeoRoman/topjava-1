<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="resources/js/mealDataUtil.js" defer></script>
<script type="text/javascript" src="resources/js/mealDataTables.js" defer></script>
<jsp:include page="fragments/bodyHeader.jsp"/>

<section>
    <h3><spring:message code="meal.title"/></h3>


    <div class="jumbotron">
        <div class="container">
            <div class="shadow">
                <div class="view-box">
                    <form id="mealFilter" method="post" action="/ajax/profile/meals/filter">
                        <dl>
                            <dt><spring:message code="meal.startDate"/>:</dt>
                            <dd><input type="date" name="startDate" value="${param.startDate}"></dd>
                        </dl>
                        <dl>
                            <dt><spring:message code="meal.endDate"/>:</dt>
                            <dd><input type="date" name="endDate" value="${param.endDate}"></dd>
                        </dl>
                        <dl>
                            <dt><spring:message code="meal.startTime"/>:</dt>
                            <dd><input type="time" name="startTime" value="${param.startTime}"></dd>
                        </dl>
                        <dl>
                            <dt><spring:message code="meal.endTime"/>:</dt>
                            <dd><input type="time" name="endTime" value="${param.endTime}"></dd>
                        </dl>
                        <button type="submit"><spring:message code="meal.filter"/></button>
                    </form>
                    <hr>
                    <%--<a href="meals/create"><spring:message code="meal.add"/></a>--%>
                    <a class="btn btn-primary" onclick="addMeal()">
                        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                        <spring:message code="meal.add"/>
                    </a>
                    <hr>
                    <table class="table table-striped display" id="mealDataTable" border="1" cellpadding="8" cellspacing="0">
                        <thead>
                        <tr>
                            <th><spring:message code="meal.dateTime"/></th>
                            <th><spring:message code="meal.description"/></th>
                            <th><spring:message code="meal.calories"/></th>
                            <th>edit</th>
                            <th>delete</th>
                        </tr>
                        </thead>
                        <c:forEach items="${meals}" var="meal">
                            <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.to.MealWithExceed"/>
                            <tr class="${meal.exceed ? 'exceeded' : 'normal'}">
                                <td>
                                        <%--${meal.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}--%>
                                        <%--<%=TimeUtil.toString(meal.getDateTime())%>--%>
                                        <%--${fn:replace(meal.dateTime, 'T', ' ')}--%>
                                        ${fn:formatDateTime(meal.dateTime)}
                                </td>
                                <td>${meal.description}</td>
                                <td>${meal.calories}</td>
                                <td><a class="update" id="${meal.id}"><spring:message code="common.update"/></a></td>
                                <td><a class="delete" id="${meal.id}"><spring:message code="common.delete"/></a></td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>
    </div>

</section>
<jsp:include page="fragments/footer.jsp"/>
<div class="modal fade" id="mealAddRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h2 class="modal-title"><spring:message code="meal.add"/></h2>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="mealDetailsForm">
                    <input type="hidden" id="id" name="id">

                    <div class="form-group">
                        <label for="description" class="control-label col-xs-3"><spring:message code="meal.description"/></label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="description" name="description" placeholder="<spring:message code="meal.description"/>">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="calories" class="control-label col-xs-3"><spring:message code="meal.calories"/></label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="calories" name="calories" placeholder="<spring:message code="meal.calories"/>">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="dateTime" class="control-label col-xs-3"><spring:message code="meal.dateTime"/></label>

                        <div class="col-xs-9">
                            <input type="datetime-local" class="form-control" id="dateTime" name="dateTime" placeholder="<spring:message code="meal.dateTime"/>">
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-xs-offset-3 col-xs-9">
                            <button type="submit" class="btn btn-primary">
                                <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="editMeal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h2 class="modal-title">Meal Edit</h2>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="mealDetailsEditForm">
                    <input type="hidden" id="mealId" name="id">

                    <div class="form-group">
                        <label for="editDescription" class="control-label col-xs-3"><spring:message code="meal.description"/></label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="editDescription" name="description" placeholder="<spring:message code="meal.description"/>">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="editCalories" class="control-label col-xs-3"><spring:message code="meal.calories"/></label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="editCalories" name="calories" placeholder="<spring:message code="meal.calories"/>">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="editDateTime" class="control-label col-xs-3"><spring:message code="meal.dateTime"/></label>
                        <div class="col-xs-9">
                            <input type="datetime-local" class="form-control" id="editDateTime" name="dateTime" placeholder="<spring:message code="meal.dateTime"/>">
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-xs-offset-3 col-xs-9">
                            <button type="submit" class="btn btn-primary">
                                <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="resources/js/test.js" defer></script>
</body>
</html>
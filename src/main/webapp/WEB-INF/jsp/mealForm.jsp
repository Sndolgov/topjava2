<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<section>
    <h2>${param.action == 'create' ? 'Create meal' : 'Edit meal'}</h2>
    <hr>
    <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.Meal" scope="request"/>

    <form:form method="POST" action="${pageContext.request.contextPath}/meals" modelAttribute="meal">
        <table>
            <dl>
                <%--<td><form:label path="id">Id</form:label></td>--%>
                <dd><form:input path="id" type="hidden" name="id" value="${meal.id}"/></dd>
            </dl>

            <dl>
                <dt><form:label path="dateTime">DateTime:</form:label></dt>
                <dd><form:input path="dateTime" type="datetime-local" value="${meal.dateTime}" name="dateTime"/></dd>
            </dl>
            <dl>
                <dt><form:label path="description">Description:</form:label></dt>
                <dd><form:input path="description" type="text" value="${meal.description}" name="description"/></dd>
            </dl>
            <dl>
                <dt><form:label path="calories">Calories:</form:label></dt>
                <dd><form:input path="calories" type="number" value="${meal.calories}" name="calories"/></dd>
            </dl>

                <button type="submit">Save</button>
                <button onclick="window.history.back()" type="button">Cancel</button>
        </table>
    </form:form>

<%--    <form method="post" action="meals">
        <input type="hidden" name="id" value="${meal.id}">
        <dl>
            <dt>DateTime:</dt>
            <dd><input type="datetime-local" value="${meal.dateTime}" name="dateTime" required></dd>
        </dl>
        <dl>
            <dt>Description:</dt>
            <dd><input type="text" value="${meal.description}" size=40 name="description" required></dd>
        </dl>
        <dl>
            <dt>Calories:</dt>
            <dd><input type="number" value="${meal.calories}" name="calories" required></dd>
        </dl>
        <button type="submit">Save</button>
        <button onclick="window.history.back()" type="button">Cancel</button>
    </form>--%>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h1>Student</h1>

<form:form modelAttribute="student"
           action="${pageContext.request.contextPath}/students/save">
    <table>
        <tr>
            <td>First name:</td>
            <td><form:input path="firstName"/></td>
        </tr>
        <tr>
            <td>Last name:</td>
            <td><form:input path="lastName"/></td>
        </tr>
        <tr>
            <td>Middle name:</td>
            <td><form:input path="middleName"/></td>
        </tr>
        <tr>
            <td>Date of birth:</td>
            <td><form:input path="dateOfBirth"/></td>
        </tr>
        <tr>
            <td>email:</td>
            <td><form:input path="email"/></td>
        </tr>
        <tr>
            <td>phone:</td>
            <td><form:input path="phone"/></td>
        </tr>
<%--        <tr>--%>
<%--            <td>groupID:</td>--%>
<%--            <td><form:input path="group"/></td>--%>
<%--        </tr>--%>

        <tr>
            <td>Group:</td>
            <td>
                <form:select path="group">
                    <c:forEach var="group" items="${student_groups}">
                        <form:option value="${group.getId()}"> ${group.getName()} </form:option>
                    </c:forEach>
                </form:select>
            </td>
        </tr>

        <td colspan="2">
            <input type="submit" value="Save" />
        </td>
    </table>
</form:form>
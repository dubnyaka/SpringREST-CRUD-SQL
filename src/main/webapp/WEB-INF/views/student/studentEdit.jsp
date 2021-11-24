<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<h1>Student</h1>

<form:form modelAttribute="student"
           action="${pageContext.request.contextPath}/student/save">
    <form:hidden path="id" />
    <table>
        <tr>
            <td>Name:</td>
            <td><form:input path="name"/></td>
        </tr>
        <tr>
            <td>email:</td>
            <td><form:input path="email"/></td>
        </tr>
        <tr>
            <td>phone:</td>
            <td><form:input path="phone"/></td>
        </tr>
        <tr>
            <td>groupID:</td>
            <td><form:input path="groupID"/></td>
        </tr>
        <td colspan="2">
            <input type="submit" value="Save" />
        </td>
    </table>
</form:form>
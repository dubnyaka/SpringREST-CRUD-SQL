<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<h1>Student</h1>

<table>
    <tr>
        <td>ID:</td>
        <td>${student.id}</td>
    </tr>
    <tr>
        <td>First name:</td>
        <td>${student.firstName}</td>
    </tr>
    <tr>
        <td>Last name:</td>
        <td>${student.lastName}</td>
    </tr>
    <tr>
        <td>Middle name:</td>
        <td>${student.middleName}</td>
    </tr>
    <tr>
        <td>Date of birth:</td>
        <td>${student.dateOfBirth}</td>
    </tr>
    <tr>
        <td>Email:</td>
        <td>${student.email}</td>
    </tr>
    <tr>
        <td>Phone:</td>
        <td>${student.phone}</td>
    </tr>
    <tr>
        <td>GroupId:</td>
        <td>${student.groupId}</td>
    </tr>
    <tr>
    <!-- Добавлена ссылка Edit -->
    <td colspan="2">
        <a href="${pageContext.request.contextPath}/students/${student.id}/edit">Edit</a>
    </td>
    </tr>
</table>

<form:form modelAttribute="student"
           action="${pageContext.request.contextPath}/students/delete">
    <form:hidden path="id" value="${student.id}" />
    <input type="submit" value="Delete" />
</form:form>
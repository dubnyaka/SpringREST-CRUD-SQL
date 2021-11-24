<h1>Student</h1>

<table>
    <tr>
        <td>ID:</td>
        <td>${student.id}</td>
    </tr>
    <tr>
        <td>Name:</td>
        <td>${student.name}</td>
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
        <td>GroupID:</td>
        <td>${student.groupID}</td>
    </tr>
    <tr>
    <!-- Добавлена ссылка Edit -->
    <td colspan="2">
        <a href="${pageContext.request.contextPath}/student/${student.id}/edit">Edit</a>
    </td>
    </tr>

</table>
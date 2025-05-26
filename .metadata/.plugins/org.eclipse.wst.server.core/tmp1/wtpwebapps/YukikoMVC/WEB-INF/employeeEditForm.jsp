<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:if test="${empty employee}">
    <c:redirect url="EmployeeServlet?message=編集する従業員が選択されていません。"/>
</c:if>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>従業員編集</title>
</head>
<body>
    <h2>従業員編集</h2>
    <form action="EmployeeUpdateServlet" method="post">
        <fieldset>
            <legend>従業員編集</legend>

            <input type="hidden" id="empId" name="empId" value="${employee.empId}" />

            <label for="empName">氏名:</label><br/>
            <input type="text" id="empName" name="empName" value="${employee.empName}" required /><br/><br/>

            <label for="depId">部署:</label><br/>
            <select id="depId" name="depId" required>
                <option value="">-- 選択してください --</option>
                <c:forEach var="dep" items="${departments}">
                    <option value="${dep.depId}" <c:if test="${dep.depId == employee.department.depId}">selected</c:if>>
                        ${dep.depName}
                    </option>
                </c:forEach>
            </select><br/><br/>

            <label for="registrationDate">登録日:</label><br/>
            <input type="datetime-local" id="registrationDate" name="registrationDate"
                   value="<fmt:formatDate value="${employee.registrationDateUtil}" pattern="yyyy-MM-dd'T'HH:mm"/>"/><br/><br/>

            <button type="submit">変更を保存</button>
        </fieldset>
    </form>

    <script>
        document.addEventListener('DOMContentLoaded', function() {
            fetch('/YukikoMVC/GetDepartments')
                .then(response => response.json())
                .then(data => {
                    const departmentSelect = document.getElementById('depId');
                    // Limpar as opções existentes (exceto a padrão)
                    for (let i = departmentSelect.options.length - 1; i >= 1; i--) {
                        departmentSelect.remove(i);
                    }
                    data.forEach(department => {
                        const option = document.createElement('option');
                        option.value = department.id;
                        option.textContent = department.name;
                        // A seleção do departamento correto será feita no lado do servidor (JSP)
                        departmentSelect.appendChild(option);
                    });
                })
                .catch(error => {
                    console.error("Erro ao buscar departamentos:", error);
                });
        });
    </script>
</body>
</html>
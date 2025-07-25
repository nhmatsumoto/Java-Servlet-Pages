<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>社員一覧</title>
</head>
<body>
    <h2>社員一覧</h2>
    
    <p>
    	<a href="EmployeeFormServlet">新規登録</a>
    </p>
    <table border="1">
        <tr>
            <th>社員ID</th>
            <th>社員名</th>
            <th>所属部署</th>
            <th>登録日時</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="e" items="${employees}">
            <tr>
                <td>${e.empId}</td>
                <td>${e.empName}</td>
                <td>${e.department.depName}</td>
                <td>
                	<c:if test="${not empty e.registrationDateUtil}">
			        	<fmt:formatDate value="${e.registrationDateUtil}" pattern="yyyy/MM/dd HH:mm:ss" />
				    </c:if>
                </td>
                <td>
                    <a href="EmployeeEditServlet?empId=${e.empId}" class="button-like">編集</a>
                    <a href="EmployeeDeleteServlet?empId=${e.empId}" class="button-like delete-button" onclick="return confirm('この従業員を削除してもよろしいですか？');">削除</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
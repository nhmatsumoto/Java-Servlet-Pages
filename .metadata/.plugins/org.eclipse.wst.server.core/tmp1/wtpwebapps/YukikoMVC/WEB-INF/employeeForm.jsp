<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="EmployeeServlet" method="post">
        <fieldset>
            <legend>従業員登録</legend>

            <label for="empName">氏名:</label><br/>
            <input type="text" id="empName" name="empName" required /><br/><br/>

            <label for="depId">部署:</label><br/>
            <select id="depId" name="depId" required>
                <option value="">-- 選択してください --</option>
            </select><br/><br/>

            <label for="registrationDate">登録日:</label><br/>
            <input type="datetime-local" id="registrationDate" name="registrationDate" /><br/><br/>

            <button type="submit">登録</button>
        </fieldset>
    </form>
     <script>
        document.addEventListener('DOMContentLoaded', function() {
            fetch('/YukikoMVC/GetDepartments')
                .then(response => response.json())
                .then(data => {
                    const departmentSelect = document.getElementById('depId');
                    data.forEach(department => {
                        const option = document.createElement('option');
                        option.value = department.id;
                        option.textContent = department.name;
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
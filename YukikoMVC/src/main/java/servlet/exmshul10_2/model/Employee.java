package servlet.exmshul10_2.model;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class Employee {
    private String empId;
    private String empName;
    private Department department;
    private LocalDateTime registrationDateTime;

    public Employee() {}

    public Employee(String empId, String empName) {
        setEmpId(empId);
        setEmpName(empName);
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public LocalDateTime getRegistrationDateTime() {
        return registrationDateTime;
    }

    public void setRegistrationDateTime(LocalDateTime registrationDateTime) {
        this.registrationDateTime = registrationDateTime;
    }
    
    // Convert LocalDateTime para java.util.Date
    public Date getRegistrationDateUtil() {
        if (registrationDateTime == null) {
            return null;
        }
        return Date.from(registrationDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    // Converter java.util.Date para LocalDateTime
    public void setRegistrationDateUtil(Date registrationDateUtil) {
        if (registrationDateUtil == null) {
            this.registrationDateTime = null;
        } else {
            this.registrationDateTime = registrationDateUtil.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
        }
    }
}

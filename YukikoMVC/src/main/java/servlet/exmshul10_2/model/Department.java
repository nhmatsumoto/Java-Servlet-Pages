package servlet.exmshul10_2.model;

public class Department {
    private String depId;
    private String depName;

    public Department() {}
    
    public Department(String id, String name) {
    	setDepId(id);
    	setDepName(name);
    }

    public String getDepId() {
        return depId;
    }

    public void setDepId(String depId) {
        this.depId = depId;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }
}
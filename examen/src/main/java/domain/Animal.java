package domain;

public class Animal extends Entity<Integer>{
    private String name;
    private Integer centreID;
    private Type type;

    public Animal(String name, Integer centreID, Type type) {
        this.name = name;
        this.centreID = centreID;
        this.type = type;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getCentreID() {
        return centreID;
    }
    public void setCentreID(Integer centreID) {
        this.centreID = centreID;
    }
    public Type getType() {
        return type;
    }
    public void setType(Type type) {
        this.type = type;
    }

}

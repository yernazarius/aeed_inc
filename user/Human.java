package user;

public abstract class Human {
    private int id;
    private String name;
    private String surname;

    public Human(){}
    public Human(int id, String name, String surname){
        setId(id); setName(name); setSurname(surname);
    }
    public Human(String name, String surname){
        setName(name); setSurname(surname);
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}
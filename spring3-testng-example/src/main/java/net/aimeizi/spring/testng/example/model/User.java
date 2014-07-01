package net.aimeizi.spring.testng.example.model;

/**
 * Created by 冯靖 on 2014/7/1 0001.
 */
public class User {

    private String id;
    private String name;
    private String surname;

    /**
     * Gets User Id
     *
     * @return String id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets User Id
     *
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets User Name
     *
     * @return String name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets User Name
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets User Surname
     *
     * @return String Surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets User Surname
     *
     * @param surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append("Id : ").append(getId());
        strBuilder.append(", Name : ").append(getName());
        strBuilder.append(", Surname : ").append(getSurname());
        return strBuilder.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((surname == null) ? 0 : surname.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (surname == null) {
            if (other.surname != null)
                return false;
        } else if (!surname.equals(other.surname))
            return false;
        return true;
    }
}

package com.company.model;

import javax.xml.crypto.Data;
import java.util.Arrays;
import java.util.Objects;

public class Description {
    private Data birthday;
    private String name;
    private String surname;
    private String description;
    private String[] interesting;

    public Description(Data birthday, String name, String surname, String description, String[] interesting) {
        this.birthday = birthday;
        this.name = name;
        this.surname = surname;
        this.description = description;
        this.interesting = interesting;
    }

    public Data getBirthday() {
        return birthday;
    }
    public void setBirthday(Data birthday) {
        this.birthday = birthday;
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

    public String getDescription() {
        return description;
    }
    public void setDescription(String discription) {
        this.description = discription;
    }

    public String[] getInteresting() {
        return interesting;
    }
    public void setInteresting(String[] interesting) {
        this.interesting = interesting;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Description)) return false;
        Description that = (Description) o;
        return  Objects.equals(getBirthday(), that.getBirthday()) &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getSurname(), that.getSurname()) &&
                Objects.equals(getDescription(), that.getDescription()) &&
                Arrays.equals(getInteresting(), that.getInteresting());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getBirthday(), getName(), getSurname(), getDescription());
        result = 31 * result + Arrays.hashCode(getInteresting());
        return result;
    }

    @Override
    public String toString() {
        return "Информация об" + name + " " + surname + '\'' +
                ". Родился:" + birthday +
                ". Кратко о себе: " + description + '\'' +
                ". Интересуется: " + Arrays.toString(interesting);
    }
}

package ru.sbt.model;

public class Person {
    private final String firstName;
    private final String lastName;
    private final int age;
    private int hashcode = 0;

    private Person() {
        throw new IllegalArgumentException("Incorrect object creation \'Person\'. Use \'new Person(firstName, lastName, age);\'");
    }

    public Person(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return this.firstName + " " + this.lastName + ", age " + this.age;
    }

    @Override
    public int hashCode() {
        if (this.hashcode == 0) {
            int result = 17;
            result = 37 * result * this.firstName.hashCode();
            result = 37 * result * this.lastName.hashCode();
            result = 37 * result * this.age;
            this.hashcode = result;
        }
        return this.hashcode;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Person)) {
            return false;
        }
        Person person = (Person) obj;
        if ((this.firstName.equals(person.getFirstName())) &&
                this.lastName.equals(person.getLastName()) &&
                this.age == person.getAge()) {
            return true;
        } else {
            return false;
        }
    }
}
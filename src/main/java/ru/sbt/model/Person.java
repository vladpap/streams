package ru.sbt.model;

public class Person {
    private final String firstName;
    private final String lastName;
    private final int age;

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
        // TODO: 23.08.16
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        // TODO: 23.08.16
        return super.equals(obj);
    }
}

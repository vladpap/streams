package ru.sbt.streams;

import org.junit.Test;
import ru.sbt.model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class StreamsTest {

    @Test
    public void testSuperStreams() {
        List<Person> someCollection = new ArrayList<>();
        someCollection.add(new Person("Bob", "Bobrov", 17));
        someCollection.add(new Person("Alex", "Pavlov", 35));
        someCollection.add(new Person("Oeg", "Egorov", 23));
        someCollection.add(new Person("Piter", "Gusev", 21));
        someCollection.add(new Person("Dima", "Kiselev", 22));
        someCollection.add(new Person("Pavlik", "Morozov", 19));
        someCollection.add(new Person("Maxim", "Orlov", 33));
        someCollection.add(new Person("Andrey", "Makarov", 29));
        someCollection.add(new Person("Slava", "Jukov", 20));
        someCollection.add(new Person("Mihail", "Knyazev", 22));

        Map<String, Person> map = Streams.of(someCollection)
                .filter(p -> p.getAge() > 20)
                .transform(p -> new Person(p.getFirstName(), p.getLastName(), p.getAge() + 30))
                .toMap(p -> p.toString(), p -> p);

        assertEquals(10, someCollection.size());
        assertEquals(7, map.size());
        assertEquals(65, map.get("Alex Pavlov, age 65").getAge());

    }

}
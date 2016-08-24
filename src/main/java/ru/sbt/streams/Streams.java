package ru.sbt.streams;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Streams<T> {

    private List<T> list;
    private final List<Object> flowStreams;
    private int count = 0;

    private Streams() {
        throw new IllegalArgumentException("Incorrect object creation \'Streams\'. Use \' Stream.of(List)\'");
    }

    private Streams(List<T> list) {
        this.list = new ArrayList<>(list);
        this.flowStreams = new ArrayList<>();
    }

    public static <T> Streams<T> of(List<T> list) {
        return new Streams(list);
    }

    public Streams<T> filter(Predicate<? super T> predicate) {
        flowStreams.add(predicate);

        return this;
    }

    public Streams<T> transform (Function<? super T, ? extends T> function) {

        flowStreams.add(function);
        return this;
    }

    public<K, V> Map<K, V> toMap(Function<? super T, ? extends K> function1, Function<? super T, ? extends V> function2) {
        Map<K, V> map = new HashMap<>();
        for (T t : list) {
            Iterator<Object> iterator = flowStreams.iterator();
            while (iterator.hasNext()) {
                Object flowObj = iterator.next();
                if (flowObj instanceof Predicate) {
                    Predicate predicate = (Predicate) flowObj;
                    if (predicate.test(t)) {
                }
            }
            for (int i = 0; i < count; i++) {
                Object flowObj = flowStreams.get(i);
                if (flowObj instanceof Predicate) {
                    Predicate predicate = (Predicate) flowObj;
                    if (!(predicate.test(t))) {
                        break;
                    }
                } else if (flowObj instanceof Function) {
                    Function function = (Function) flowObj;
//                    list.set(list.indexOf(t), function.apply(t));
                    t = (T) function.apply(t);
                }
            }
            map.put(function1.apply(t), function2.apply(t));
        }

        return map;
    }
}

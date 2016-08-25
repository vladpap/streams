package ru.sbt.streams;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

public class Streams<T> implements SuperStreams<T> {

    private static final Object TOMAP = "StreamsToMapMethod";
    private final List<T> list;
    private final List<Object> flowStreams;


    public Streams() {
        throw new IllegalArgumentException("Incorrect object creation \'Streams\'. Use \' Stream.of(List)\'");
    }

    private Streams(List<T> list) {
        this.list = new ArrayList<>(list);
        this.flowStreams = new ArrayList<>();
    }

    public static <T> Streams<T> of(List<T> list) {
        return new Streams(list);
    }

    @Override
    public Streams<T> filter(Predicate<? super T> predicate) {
        flowStreams.add(predicate);

        return this;
    }

    @Override
    public Streams<T> transform(Function<? super T, ? extends T> function) {

        flowStreams.add(function);
        return this;
    }

    @Override
    public <K, V> Map<K, V> toMap(Function<? super T, ? extends K> function1, Function<? super T, ? extends V> function2) {
        flowStreams.add(TOMAP);

        Map<K, V> map = new HashMap<>();

        for (T t : list) {

            for (Object flowObj : flowStreams) {
                if (getPredicate(t, flowObj)) break;

                t = getFunction(t, flowObj);

                if (flowObj.equals(TOMAP)) {
                    map.put(function1.apply(t), function2.apply(t));
                }
            }
        }

        return map;
    }

    private boolean getPredicate(T t, Object flowObj) {
        if (flowObj instanceof Predicate) {
            Predicate predicate = (Predicate) flowObj;
            if (!(predicate.test(t))) {
                return true;
            }
        }
        return false;
    }

    private T getFunction(T t, Object flowObj) {
        if (flowObj instanceof Function) {
            Function function = (Function) flowObj;
            t = (T) function.apply(t);
        }
        return t;
    }
}
package ru.sbt.streams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Streams<T> {

    private List<T> list;

    private Streams(List<T> list) {
        this.list = new ArrayList<>(list);
    }

    public static <T> Streams<T> of(List<T> list) {
        return new Streams(list);
    }

    public Streams<T> filter(Predicate<? super T> predicate) {
        List<T> result = new ArrayList<>();
        for (T t : list) {
            if (predicate.test(t)) {
                result.add(t);
            }
        }
        list = new ArrayList<>(result);
//        for (T t : list) {
//            if (!predicate.test(t)) {
//                list.remove(t);
//            }
//        }
        return this;
    }

    public Streams<T> transform (Function<? super T, ? extends T> function) {
//        List<T> result = new ArrayList<>();
//        for (T t : list) {
//            result.add(function.apply(t));
//        }
//        list = result;
        for (T t : list) {
            list.set(list.indexOf(t), function.apply(t));
        }
        return this;
    }

    public<K, V> Map<K, V> toMap(Function<? super T, ? extends K> function1, Function<? super T, ? extends V> function2) {
        Map<K, V> map = new HashMap<>();
        for (T t : list) {
            map.put(function1.apply(t), function2.apply(t));
        }

        return map;
    }
}

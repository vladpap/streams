package ru.sbt.streams;

import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

interface SuperStreams<T> {
    Streams<T> filter(Predicate<? super T> predicate);
    Streams<T> transform(Function<? super T, ? extends T> function);
    <K, V> Map<K, V> toMap(Function<? super T, ? extends K> function1, Function<? super T, ? extends V> function2);
}
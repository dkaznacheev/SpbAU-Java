package ru.spbau.streams;

import javafx.util.Pair;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class SecondPartTasks {

    private SecondPartTasks() {}

    // Найти строки из переданных файлов, в которых встречается указанная подстрока.
    public static List<String> findQuotes(List<String> paths, CharSequence sequence) {
        return paths.stream().flatMap(path -> {
            try {
                return Files.lines(Paths.get(path));
            } catch (IOException e) {
                throw new RuntimeException();
            }
        }).filter(s -> s.contains(sequence)).collect(Collectors.toList());

    }

    // В квадрат с длиной стороны 1 вписана мишень.
    // Стрелок атакует мишень и каждый раз попадает в произвольную точку квадрата.
    // Надо промоделировать этот процесс с помощью класса java.util.Random и посчитать, какова вероятность попасть в мишень.
    public static double piDividedBy4() {
        final int sampleSize = 1000;
        final int targetSize = 1000;
        return new Random().ints(sampleSize, 0, targetSize * targetSize)
                .filter(value ->
                                (value % targetSize) * (value % targetSize)
                              + (value / targetSize) * (value / targetSize)
                              < targetSize * targetSize
                  ).count() / (double) sampleSize;
    }

    // Дано отображение из имени автора в список с содержанием его произведений.
    // Надо вычислить, чья общая длина произведений наибольшая.
    public static String findPrinter(Map<String, List<String>> compositions) {
        return compositions.entrySet().stream().sorted(new Comparator<Map.Entry<String, List<String>>>() {
            private int sumLength(List<String> novels) {
                return novels.stream().mapToInt(String::length).sum();
            }

            @Override
            public int compare(Map.Entry<String, List<String>> o1, Map.Entry<String, List<String>> o2) {
                return sumLength(o2.getValue()) - sumLength(o1.getValue());
            }
        }).findFirst().get().getKey();
    }

    // Вы крупный поставщик продуктов. Каждая торговая сеть делает вам заказ в виде Map<Товар, Количество>.
    // Необходимо вычислить, какой товар и в каком количестве надо поставить.
    public static Map<String, Integer> calculateGlobalOrder(List<Map<String, Integer>> orders) {
        return orders.stream().flatMap(m -> m.entrySet().stream()).collect(Collectors.groupingBy(Map.Entry::getKey, Collectors.summingInt(Map.Entry::getValue)));
    }
}
package com.practice.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class StreamApp{

    public static void main(String... args){

        //Create range of numbers
        IntStream.rangeClosed(1, 10).forEach(t -> System.out.println(t));

        System.out.println("");

        //Iterates number
        Stream.iterate(0, n -> n + 2).limit(10).forEach(System.out::println);

        System.out.println("");

        //Fibonacci Numbers pairs
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]})
        .limit(20).forEach(t -> System.out.print("(" + t[0] + "," + t[1] + ") "));

        System.out.println("");
        //Fibonacci Numbers
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]})
        .limit(20).map(t -> t[0])
        .forEach(t -> System.out.print(t + " "));

        System.out.println("");
        //Generating random numbers
        Stream.generate(Math::random).limit(5).forEach(System.out::println);

        //Debug
        System.out.println("");
        List<Integer> numbers = Arrays.asList(2, 3, 4, 5);
        numbers.stream()
        .peek(x -> System.out.println("from stream: " + x))
        .map(x -> x + 17)
        .peek(x -> System.out.println("after map: " + x))
        .filter(x -> x % 2 == 0)
        .peek(x -> System.out.println("after filter: " + x))
        .limit(3)
        .peek(x -> System.out.println("after limit: " + x))
        .forEach(System.out::println);

    



    }

}
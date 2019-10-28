package com.practice.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

import com.practice.util.Dish;

import static java.util.stream.Collectors.toList;


public class StreamDishesApp {

    public static void main(String... args) {
        List<Dish> menu = Arrays.asList(
            new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("salmon", false, 450, Dish.Type.FISH) );


        //ForEach
        List<Dish> vegetarianMenu = menu.stream()
                                            .filter(Dish::isVegetarian)
                                            .collect(toList());
        vegetarianMenu.forEach(System.out::println);

        //Filtering
        List<Integer> numbers = Arrays.asList(1,2,1,3,3,2,4);
        numbers.stream().filter(i->i%2==0).distinct().forEach(System.out::println);

        //Truncating
        menu.stream()
        .filter(d->d.getCalories()>300)
        .limit(3)
        .forEach(System.out::println);

        //Mapping
        List<Integer> square = Arrays.asList(1,2,3,4,5);
        square.stream()
        .map(i->i*i)
        .forEach(System.out::println);

        //Flat Mapping
        List<Integer> numbers1 = Arrays.asList(1,2,3);
        List<Integer> numbers2 = Arrays.asList(3,4);
        List<int[]> pairs = numbers1.stream()
                            .flatMap(
                                i-> numbers2.stream()
                                .map(j->new int[]{i,j})
                            )
                            .collect(toList());
        pairs.forEach(
            a->System.out.print("(" + a[0] + ","+ a[1] + ") ")
        );

        //Finding and Matching
        if(menu.stream().anyMatch(Dish::isVegetarian)){
            System.out.println("The menu is (somewhat) vegetarian friendly!!");
        }

        //Optional
        menu.stream()
        .filter(Dish::isVegetarian)
        .findAny()
        .ifPresent(d -> System.out.println(d.getName()));

        List<Integer> someNumbers = Arrays.asList(1,2,3,4,5);
        Optional<Integer> firstNumber = someNumbers.stream()
                                        .map(x->x*x)
                                        .filter(x->x%3 == 0)
                                        .findFirst();
        firstNumber.ifPresent(System.out::println);

        //Sum
        List<Integer> numbersList = Arrays.asList(1,2,3,4,5);
        int sum = numbersList.stream().reduce(0, (a, b)->a+b);
        System.out.println(sum);
        int plus = numbersList.stream().reduce(1, (a, b)->a*b);
        System.out.println(plus);

        //Sum
        int calories = menu.stream().mapToInt(Dish::getCalories).sum();
        System.out.println(calories);

        //Maximun with Optional
        OptionalInt maxCalories = menu.stream()
                                    .mapToInt(Dish::getCalories)
                                    .max();
        int max = maxCalories.orElse(1);
        System.out.println(max);


    }
}
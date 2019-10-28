package com.practice.collector;

import java.util.Arrays;
import java.util.HashSet;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import com.practice.util.Dish;
import com.practice.util.Dish.Type;

import static java.util.stream.Collectors.*;
import static java.util.Comparator.comparingInt;

public class CollectorApp {

    public enum CaloricLevel { DIET, NORMAL, FAT }

    public static void main(String... args) {

        List<Dish> menu = Arrays.asList(new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT), new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER), new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER), new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH), new Dish("salmon", false, 450, Dish.Type.FISH));

        // Counting
        long howManyDishes = menu.stream().collect(counting());
        System.out.println(howManyDishes);

        long howManyDishesII = menu.stream().count();
        System.out.println(howManyDishesII);

        // Summarization
        int totalCalories = menu.stream().collect(summingInt(Dish::getCalories));
        System.out.println(totalCalories);

        double avgCalories = menu.stream().collect(averagingInt(Dish::getCalories));
        System.out.println(avgCalories);

        IntSummaryStatistics menuStatistics = menu.stream().collect(summarizingInt(Dish::getCalories));
        System.out.println(menuStatistics);

        // Joining Strings
        String shortMenu = menu.stream().map(Dish::getName).collect(joining());
        System.out.println(shortMenu);

        String shortMenuII = menu.stream().map(Dish::getName).collect(joining(", "));
        System.out.println(shortMenuII);

        // Generalized summarization with reduction
        int totalCaloriesII = menu.stream().collect(reducing(0, Dish::getCalories, (i, j) -> i + j));
        System.out.println(totalCaloriesII);

        // Grouping
        Map<Type, List<Dish>> dishesByType = menu.stream().collect(groupingBy(Dish::getType));
        System.out.println(dishesByType);

        Map<CaloricLevel, List<Dish>> dishesByCaloricLevel = menu.stream().collect(
            groupingBy(dish -> {
                            if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                            else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                            else return CaloricLevel.FAT;
                            } ));
        System.out.println(dishesByCaloricLevel);

        Map<Type, Map<CaloricLevel, List<Dish>>> dishesByTypeCaloricLevel = menu.stream()
        .collect(
            groupingBy(Dish::getType,
                       groupingBy( dish -> {
                            if(dish.getCalories() <= 400) return CaloricLevel.DIET;
                            else if(dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                            else return CaloricLevel.FAT;
                        })
            )
        );
        System.out.println(dishesByTypeCaloricLevel);

        //Collecting data in subgroups
        Map<Type, Long> typesCount = menu.stream().collect(groupingBy(Dish::getType, counting()));
        System.out.println(typesCount);

        Map<Dish.Type, Optional<Dish>> mostCaloricByType = menu.stream()
        .collect(groupingBy(Dish::getType, maxBy(comparingInt(Dish::getCalories))));
        System.out.println(mostCaloricByType);

        Map<Dish.Type, Dish> mostCaloricByTypeII = menu.stream().collect(
            groupingBy(Dish::getType,
            collectingAndThen(maxBy(comparingInt(Dish::getCalories)), Optional::get))
        );
        System.out.println(mostCaloricByTypeII);

        //Know which CaloricLevels are available in the menu for each type of Dish.
        Map<Dish.Type, Set<CaloricLevel>> caloricLevelByType = menu.stream().collect(
            groupingBy(Dish::getType, mapping(dish -> {
                if(dish.getCalories() <= 400) return CaloricLevel.DIET;
                else if(dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                else return CaloricLevel.FAT;
                }, toCollection(HashSet::new)))
        );
        System.out.println(caloricLevelByType);

        //Partitioning
        Map<Boolean, List<Dish>> partitionedMenu = menu.stream().collect(partitioningBy(Dish::isVegetarian));
        System.out.println(partitionedMenu);

        List<Dish> vegetarianDishes = partitionedMenu.get(true);
        System.out.println(vegetarianDishes);

    }


   

}
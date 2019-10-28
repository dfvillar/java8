package com.practice.lambda;

import static java.util.Comparator.comparing;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

import com.practice.util.Apple;
import com.practice.util.AppleComparator;

/**
 * Hello world!
 */
public final class LampdaApp {
    private LampdaApp() {
    }

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {

        List<Apple> appleList = new ArrayList<>();
        appleList.add(new Apple(5));
        appleList.add(new Apple(3));
        appleList.add(new Apple(1));
        appleList.add(new Apple(4));
        appleList.add(new Apple(2));

        System.out.println(appleList.toString());
        //1
        appleList.sort(new AppleComparator());
        //2
        appleList.sort(new Comparator<Apple>(){
            @Override
            public int compare(Apple a1, Apple a2){
                return a1.getWeight().compareTo(a2.getWeight());
            }
        }); 
        //3
        appleList.sort((Apple a1, Apple a2)->a1.getWeight().compareTo(a2.getWeight()));
        //4
        appleList.sort((a1, a2)->a1.getWeight().compareTo(a2.getWeight()));
        //5
        Comparator<Apple> c = Comparator.comparing((Apple a)->a.getWeight());
        appleList.sort(c);
        //6
        appleList.sort(comparing((a)->a.getWeight()));
        //7
        appleList.sort(comparing(Apple::getWeight).reversed());

        System.out.println(appleList.toString());


        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> x * 2;
        Function<Integer, Integer> h = f.andThen(g);
        Function<Integer, Integer> h2 = f.compose(g);
        System.out.println(f.apply(2));
        System.out.println(g.apply(2));
        System.out.println(h.apply(2));
        System.out.println(h2.apply(2));




    }
}



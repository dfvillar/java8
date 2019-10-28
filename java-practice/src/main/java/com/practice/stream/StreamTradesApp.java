
package com.practice.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.practice.util.Trader;
import com.practice.util.Transaction;

import static java.util.stream.Collectors.toList;
import static java.util.Comparator.comparing;

public class StreamTradesApp {

    public static void main(String... args) {

        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
            new Transaction(brian, 2011, 300),
            new Transaction(raoul, 2012, 1000), 
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 710), 
            new Transaction(mario, 2012, 700), 
            new Transaction(alan, 2012, 950));


        //Find all transactions in the year 2011 and sort them by value (small to high).
        transactions.stream()
        .filter(t->t.getYear() == 2011)
        .sorted(comparing((Transaction t) -> t.getValue()))
        .forEach(System.out::println);

        System.out.println("");

        //What are all the unique cities where the traders work?
        transactions.stream()
        .map((Transaction t) -> t.getTrader().getCity())
        .distinct()
        .forEach(System.out::println);


        List<String> cities = transactions.stream()
        .map((Transaction t) -> t.getTrader().getCity())
        .distinct()
        .collect(toList());
        
        System.out.println(cities);
        System.out.println("");

        //Find all traders from Cambridge and sort them by name
        transactions.stream()
        .filter((Transaction t)-> t.getTrader().getCity().equals("Cambridge"))
        .map((Transaction t)-> t.getTrader())
        .distinct()
        .sorted(comparing((Trader t)-> t.getName()))
        .forEach(System.out::println);
        System.out.println("");

        //Return a string of all traders’ names sorted alphabetically
        String names = transactions.stream()
        .map(transaction->transaction.getTrader().getName())
        .distinct()
        .sorted()
        .reduce("", (n1, n2)-> n1 + n2);
        System.out.println(names);

        System.out.println("");

        //What’s the highest value of all the transactions?
        Optional<Integer> highestValue = transactions.stream()
        .map(t->t.getValue())
        .reduce(Integer::max);
        System.out.println(highestValue.get());

        System.out.println("");
        

    }

    
}
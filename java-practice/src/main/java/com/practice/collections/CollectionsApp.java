package com.practice.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import com.practice.util.Trader;
import com.practice.util.Transaction;

public class CollectionsApp {

    public static void main(String... args) {

        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = new ArrayList<>();
            transactions.add(new Transaction(brian, 2011, 300, "E100RFSC"));
            transactions.add(new Transaction(raoul, 2012, 1000, "ABCD"));
            transactions.add(new Transaction(raoul, 2011, 400, "DFSD"));
            transactions.add(new Transaction(mario, 2012, 710, "1DFGH"));
            transactions.add(new Transaction(mario, 2012, 700, "WERT"));
            transactions.add(new Transaction(alan, 2012, 950, "DFGFD"));

        System.out.println("");
        //removeIf
        transactions.removeIf(transaction -> Character.isDigit(transaction.getReferenceCode().charAt(0)));
        transactions.forEach(System.out::println);

        //Maps Iterate
        Map<String, Integer> ageOfFriends = new HashMap<>();
        ageOfFriends.put("Daniel", 10);
        ageOfFriends.put("Pedro", 15);
        ageOfFriends.put("Juan", 20);
        
        System.out.println("");
        ageOfFriends.forEach((friend, age) -> System.out.println(friend + " " + age));

        String str = null;

        List<String> list = Arrays.asList(str);

        System.out.println(list +" " + list.isEmpty());






    }
}
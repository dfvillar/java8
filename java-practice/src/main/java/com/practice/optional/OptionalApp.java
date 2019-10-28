package com.practice.optional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.practice.util.Person;

public class OptionalApp{

    public static void main(String[] args) {
        System.out.println("--- Optional ---");

        System.out.println("-- Creating Optional Objects --");
        Optional<String> empty = Optional.empty();
        System.out.println(empty.isPresent());

        System.out.println("-- Given no null --");
        String name = "baeldung";
        Optional<String> opt = Optional.of(name);
        System.out.println(opt.isPresent());

        System.out.println("-- Given  null --");
        String name1 = "baeldung";
        Optional<String> opt1 = Optional.ofNullable(name1);
        System.out.println(opt1.isPresent());

        System.out.println("-- If present --");
        Optional<String> opt2 = Optional.of("baeldung");
        opt2.ifPresent(n -> System.out.println(n.length()));

        System.out.println("-- Or Else --");
        String nullName = null;
        String name2 = Optional.ofNullable(nullName).orElse("john");
        System.out.println(name2);

        System.out.println("-- Filter --");
        Integer year = 2016;
        Optional<Integer> yearOptional = Optional.of(year);
        boolean is2016 = yearOptional.filter(y -> y == 2016).isPresent();
        System.out.println("is2016" + is2016);


        System.out.println("-- Map --");
        List<String> companyNames = Arrays.asList(
        "paypal", "oracle", "", "microsoft", "", "apple");
        Optional<List<String>> listOptional = Optional.of(companyNames);
    
        int size = listOptional
        .map(List::size)
        .orElse(0);
        System.out.println(size);

        System.out.println("-- Map --");
        String nameb = "baeldung";
        Optional<String> nameOptional = Optional.of(nameb);
 
        int len = nameOptional
        .map(String::length)
        .orElse(0);
        System.out.println(len);

        System.out.println("-- Filtering --");
        String password = " password ";
        Optional<String> passOpt = Optional.of(password);
        boolean correctPassword = passOpt.filter(
        pass -> pass.equals("password")).isPresent();
        System.out.println(correctPassword);
    
        correctPassword = passOpt
        .map(String::trim)
        .filter(pass -> pass.equals("password"))
        .isPresent();
        System.out.println(correctPassword);

        System.out.println("-- Flap Map --");
        Person person = new Person("john", 26);
        Optional<Person> personOptional = Optional.of(person);
    
        Optional<Optional<String>> nameOptionalWrapper  
        = personOptional.map(Person::getName);

        Optional<String> nameOptionalA 
        = nameOptionalWrapper.orElseThrow(IllegalArgumentException::new);

        String nameX = nameOptionalA.orElse("");
        System.out.println(nameX);
    
        String nameY = personOptional
        .flatMap(Person::getName)
        .orElse("");
        System.out.println(nameY);

        System.out.println("-- Optional With Primitive --");
        String prop = "5";
        int i = Optional.ofNullable(prop)
        .flatMap(s -> stringToInt(s))
        .filter(j -> j > 0)
        .orElse(0);
        System.out.println(i);



    }


    public static Optional<Integer> stringToInt(String s) {
        try {
        return Optional.of(Integer.parseInt(s));
        } catch (NumberFormatException e) {
        return Optional.empty();
        }
        }
    
}
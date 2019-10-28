
package com.practice.future;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Supplier;
import java.util.Arrays;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class FutureApp {
    static List<Shop> shops = Arrays.asList(new Shop("BestPrice"), 
    new Shop("LetsSaveBig"), 
    new Shop("MyFavoriteShop"),
    new Shop("BuyItAll"),
    new Shop("Shophyfy"),
    new Shop("Merchant"),
    new Shop("Industries"));

    private final static Executor executor = Executors.newFixedThreadPool(shops.size(), (Runnable r) -> {
        Thread t = new Thread(r);
        t.setDaemon(true);
        return t;
      });

    public static void main(String... args) {

        execute("findPricesSequentially", () -> findPricesSequentially("myPhone27S"));

        execute("findPricesParallelizing", () -> findPricesParallelizing("myPhone27S"));

        execute("findPricesCompletableFuture", () -> findPricesCompletableFuture("myPhone27S"));

        execute("findPricesCompletableFutureWithFixedExecutor", () -> findPricesCompletableFutureWithFixedExecutor("myPhone27S"));

        execute("findPricesWithDiscountSequential", () -> findPricesWithDiscountSequential("myPhone27S"));

        execute("findPricesWithDiscountParallel", () -> findPricesWithDiscountParallel("myPhone27S"));

        execute("findPricesWithCompletableFutures", () -> findPricesWithCompletableFutures("myPhone27S"));
    }

    private static void execute(String msg, Supplier<List<String>> s) {
        System.out.println("");
        long start = System.nanoTime();
        System.out.println(s.get());
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println(msg + " done in " + duration + " msecs");
      }
    

    public static void processFuture() {
        Shop shop = new Shop("BestShop");
        long start = System.nanoTime();
        Future<Double> futurePrice = shop.getPriceAsync("my favorite product");
        long invocationTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Invocation returned after " + invocationTime + " msecs");
        // Do some more tasks, like querying other shops
        doSomethingElse();
        // while the price of the product is being calculated

        try {
            double price = futurePrice.get();
            System.out.printf("Price is %.2f%n", price);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        long retrievalTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Price returned after " + retrievalTime + " msecs");
    }

    public static List<String> findPricesSequentially(String product) {
        return shops.stream()
        .map(shop -> String.format("%s price is %.2f",
        shop.getName(), shop.getPrice(product)))
        .collect(toList());
    }

    public static List<String> findPricesParallelizing(String product) {
        return shops.parallelStream()
        .map(shop -> String.format("%s price is %.2f",
        shop.getName(), shop.getPrice(product)))
        .collect(toList());
    }

    public static List<String> findPricesCompletableFuture(String product) {
        List<CompletableFuture<String>> priceFutures =
        shops.stream()
        .map(shop -> CompletableFuture.supplyAsync(() -> shop.getName() + " price is " +
        shop.getPrice(product)))
        .collect(Collectors.toList());
        return priceFutures.stream()
        .map(CompletableFuture::join)
        .collect(toList());
    }

    public static List<String> findPricesCompletableFutureWithFixedExecutor(String product) {
        List<CompletableFuture<String>> priceFutures =
        shops.stream()
        .map(shop -> CompletableFuture.supplyAsync(() -> shop.getName() + " price is " +  shop.getPrice(product), executor))
        .collect(Collectors.toList());
        return priceFutures.stream()
        .map(CompletableFuture::join)
        .collect(toList());
    }


    public static List<String> findPricesWithDiscountSequential(String product) {
        return shops.stream()
            .map(shop -> shop.getPriceFormat(product))
            .map(Quote::parse)
            .map(Discount::applyDiscount)
            .collect(Collectors.toList());
      }
    
      public static List<String> findPricesWithDiscountParallel(String product) {
        return shops.parallelStream()
            .map(shop -> shop.getPriceFormat(product))
            .map(Quote::parse)
            .map(Discount::applyDiscount)
            .collect(Collectors.toList());
      }

      public static List<String> findPricesWithCompletableFutures(String product) {
        List<CompletableFuture<String>> priceFutures = shops.stream()
        .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPriceFormat(product), executor))
        .map(future -> future.thenApply(Quote::parse))
        .map(future -> future.thenCompose(quote ->
        CompletableFuture.supplyAsync(
        () -> Discount.applyDiscount(quote), executor)))
        .collect(toList());
        return priceFutures.stream()
        .map(CompletableFuture::join)
        .collect(toList());
        }
   

    private static void doSomethingElse(){
        int a = 0;
        for(int i=0; i<100_000; i++){
            a++;
        }
        System.out.println(a);
    }
    
}
package com.practice.future;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class Shop {

    private String name;

    public Shop(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public double getPrice(String product) {
        return calculatePrice(product);
    }
    public String getPriceFormat(String product) {
        Random randomno = new Random();
        double price = calculatePrice(product);
        Discount.Code code = Discount.Code.values()[
        randomno.nextInt(Discount.Code.values().length)];
        return String.format("%s:%.2f:%s", name, price, code);
    }

    public static void delay() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private double calculatePrice(String product) {
        delay();
        Random randomno = new Random();
        return randomno.nextDouble() * product.charAt(0) + product.charAt(1);
    }

    public Future<Double> getPriceAsync(String product) {
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread( () -> {
            try {
                double price = calculatePrice(product);
                futurePrice.complete(price);
            } catch (Exception ex) {
                futurePrice.completeExceptionally(ex);
            }
        }).start();
        return futurePrice;
    }

}
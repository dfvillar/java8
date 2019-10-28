package com.practice.util;

public class Apple{
    private int weight;

    public Apple(int w){
        this.weight = w;
    }

    public Integer getWeight(){
        return this.weight;
    }

    public void setWeight(int w){
        this.weight = w;
    }

    @Override
    public String toString(){
        return   this.weight + "";
    }
}
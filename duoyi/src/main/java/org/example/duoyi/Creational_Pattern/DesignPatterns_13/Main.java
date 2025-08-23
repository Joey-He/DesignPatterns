package org.example.duoyi.Creational_Pattern.DesignPatterns_13;

import java.util.Scanner;

// 【原型模式】-- 矩形原型
abstract class Prototype implements Cloneable {
    public abstract Prototype clone();
    public abstract void getDetails();

    // 公共的 clone 方法
    public Prototype clonePrototype() {
        try {
            return (Prototype) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
class RectanglePrototype extends Prototype {
    private String color;
    private int width;
    private int height;

    public RectanglePrototype(String color, int width, int height) {
        this.color = color;
        this.width = width;
        this.height = height;
    }
    @Override
    public Prototype clone() {
        return super.clonePrototype() ;
    }

    @Override
    public void getDetails() {
        System.out.println("Color: " + color +"," +" Width: " + width +"," + " Height: " + height);
    }
}
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String color = scanner.next();
        int width = scanner.nextInt();
        int height = scanner.nextInt();
        RectanglePrototype prototype = new RectanglePrototype(color, width, height);
        int n = scanner.nextInt();
        for(int i = 0; i < n; i++){
            Prototype clone = prototype.clone();
            clone.getDetails();
        }
    }
}

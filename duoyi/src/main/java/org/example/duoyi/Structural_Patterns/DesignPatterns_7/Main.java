package org.example.duoyi.Structural_Patterns.DesignPatterns_7;

import java.util.Scanner;

// 结构型 -- 装饰器模式
interface Coffee{
    void making();
}
class BlackCoffee implements Coffee{
    @Override
    public void making() {
        System.out.println("Brewing Black Coffee");
    }
}
class Latte implements Coffee{
    @Override
    public void making() {
        System.out.println("Brewing Latte");
    }
}

// 抽象的装饰器
abstract class CoffeeDecorator implements Coffee{
    private Coffee coffee;

    public CoffeeDecorator(Coffee coffee){
        this.coffee = coffee;
    }
    @Override
    public void making() {
        coffee.making();
    }
}
// 加入牛奶的具体Coffee装饰类
class MilkDecorator extends CoffeeDecorator{

    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }
    @Override
    public void making() {
        super.making();
        System.out.println("Adding Milk");
    }
}
// 添加糖的Coffee装饰类
class SugarDecorator extends CoffeeDecorator{
    public SugarDecorator(Coffee coffee) {
        super(coffee);
    }
    @Override
    public void making() {
        super.making();
        System.out.println("Adding Sugar");
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Coffee coffee = null;
        while (scanner.hasNext()){
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            if (n == 1){
                coffee = new BlackCoffee();
            }else if (n == 2){
                coffee = new Latte();
            } else {
                System.out.println("无效的Coffee类型");
            }
            if (m == 1){
                coffee = new MilkDecorator(coffee);
            } else if (m == 2){
                coffee = new SugarDecorator(coffee);
            }else {
                System.out.println("无效的添加类型");
            }
            coffee.making();
        }
    }
}

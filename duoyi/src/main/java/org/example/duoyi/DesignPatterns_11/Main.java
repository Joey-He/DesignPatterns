package org.example.duoyi.DesignPatterns_11;

import java.util.Scanner;

// 抽象类
abstract class CoffeeMakerTemplate {
    private String coffeeName; // 添加咖啡名称字段

    // 构造函数，接受咖啡名称参数
    public CoffeeMakerTemplate(String coffeeName) {
        this.coffeeName = coffeeName;
    }

    // 模板方法定义咖啡制作过程
    final void makeCoffee() {
        System.out.println("Making " + coffeeName + ":");
        grindCoffeeBeans();
        brewCoffee();
        addCondiments();
        System.out.println();
    }

    // 具体步骤的具体实现由子类提供
    abstract void grindCoffeeBeans();
    abstract void brewCoffee();

    // 添加调料的默认实现
    void addCondiments() {
        System.out.println("Adding condiments");
    }
}

// 具体的美式咖啡类
class AmericanCoffeeMaker extends CoffeeMakerTemplate {
    // 构造函数传递咖啡名称
    public AmericanCoffeeMaker() {
        super("American Coffee");
    }

    @Override
    void grindCoffeeBeans() {
        System.out.println("Grinding coffee beans");
    }

    @Override
    void brewCoffee() {
        System.out.println("Brewing coffee");
    }
}

// 具体的拿铁咖啡类
class LatteCoffeeMaker extends CoffeeMakerTemplate {
    // 构造函数传递咖啡名称
    public LatteCoffeeMaker() {
        super("Latte");
    }

    @Override
    void grindCoffeeBeans() {
        System.out.println("Grinding coffee beans");
    }

    @Override
    void brewCoffee() {
        System.out.println("Brewing coffee");
    }

    // 添加调料的特定实现
    @Override
    void addCondiments() {
        System.out.println("Adding milk");
        System.out.println("Adding condiments");
    }
}

// 客户端代码
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            int coffeeType = scanner.nextInt();

            CoffeeMakerTemplate coffeeMaker = null;

            if (coffeeType == 1) {
                coffeeMaker = new AmericanCoffeeMaker();
            } else if (coffeeType == 2) {
                coffeeMaker = new LatteCoffeeMaker();
            } else {
                System.out.println("Invalid coffee type");
                continue;
            }

            // 制作咖啡
            coffeeMaker.makeCoffee();
        }
    }
}
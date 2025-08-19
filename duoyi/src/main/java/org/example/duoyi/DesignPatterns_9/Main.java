package org.example.duoyi.DesignPatterns_9;

import java.util.Scanner;

// 行为模式 -- 策略模式
interface Strategy {
    public abstract int algorithm(int count);
}
class ConcreteStrategyA implements Strategy {
    @Override
    public int algorithm(int  count) {
        return (int) (count * 0.9);
    }
}
class ConcreteStrategyB implements Strategy {
    @Override
    public int algorithm(int count) {
        int ans = count;
        if(count >= 100 && count < 150 ){
            ans =  count - 5;
        }else if(count >= 150 && count < 200){
            ans =  count - 10;
        }else if(count >= 200 && count < 300){
            ans =  count - 25;
        }else if(count >= 300){
            ans =  count - 40;
        }
        return ans;
    }
}

class Context{
    private Strategy strategy;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    public int executeStrategy(int count){
        return strategy.algorithm(count);
    }
}
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Strategy strategy = null;
        for(int i = 0; i < n; i++){
            int count = scanner.nextInt();
            int type = scanner.nextInt();
            switch (type){
                case 1:
                    strategy = new ConcreteStrategyA();
                    break;
                case 2:
                    strategy = new ConcreteStrategyB();
                    break;
                default:
                    System.out.println("Invalid type.");
                    return;
            }
            Context context = new Context(strategy);
            System.out.println(context.executeStrategy(count));
        }
    }
}

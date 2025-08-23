package org.example.duoyi.Behavioral_Patterns.DesignPatterns_12;
// 行为模式 -- 状态模式

import java.util.Scanner;

interface State {
    void handle();
}
class ONState implements State {
    @Override
    public void handle() {
        System.out.println("Light is ON");
    }
}
class OFFState implements State {
    @Override
    public void handle() {
        System.out.println("Light is OFF");
    }
}

class BLINKState implements  State{
    @Override
    public void handle() {
        System.out.println("Light is Blinking");
    }
}
class Light {
    private State state;

    public Light() {
        state = new OFFState();
    }

    public void setState(State state) {
        this.state = state;
    }

    public void performOperation(){
        state.handle();
    }
}
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Light light = new Light();
        for(int i = 0; i < n; i++){
            String operation = scanner.next();
            switch (operation){
                case "ON":
                    light.setState(new ONState());
                    break;
                case "OFF":
                    light.setState(new OFFState());
                    break;
                case "BLINK":
                    light.setState(new BLINKState());
                    break;
                default:
                    System.out.println("Invalid operation");
                }
                light.performOperation();
            }
        }
    }

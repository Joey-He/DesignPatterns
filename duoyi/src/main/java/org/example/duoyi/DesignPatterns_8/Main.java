package org.example.duoyi.DesignPatterns_8;

import java.util.Scanner;

class AirConditioner {
    public void turnOff() {
        System.out.println("Air Conditioner is turned off.");
    }
}
class DeskLamp {
    public void turnOff() {
        System.out.println("Desk Lamp is turned off.");
    }
}

class Television {
    public void turnOff() {
        System.out.println("Television is turned off.");
    }
}

class PowerSwitchFacade {
    private DeskLamp deskLamp;
    private AirConditioner airConditioner;
    private Television television;

    public PowerSwitchFacade() {
        this.deskLamp = new DeskLamp();
        this.airConditioner = new AirConditioner();
        this.television = new Television();
    }

    public void turnOffDevice(int deviceCode) {
        switch (deviceCode) {
            case 1:
                airConditioner.turnOff();
                break;
            case 2:
                deskLamp.turnOff();
                break;
            case 3:
                television.turnOff();
                break;
            case 4:
                System.out.println("All devices are off.");
                break;
            default:
                System.out.println("Invalid device code.");
        }
    }
}
public class Main {
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        // 读取输入
        int n = scanner.nextInt();
        int[] input = new int[n];

        for (int i = 0; i < n; i++) {
            input[i] = scanner.nextInt();
        }

        // 创建电源总开关外观
        PowerSwitchFacade powerSwitch = new PowerSwitchFacade();

        // 执行操作
        for (int i = 0; i < n; i++) {
            powerSwitch.turnOffDevice(input[i]);
        }
    }
}

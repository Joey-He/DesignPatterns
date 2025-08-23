package org.example.duoyi.Structural_Patterns.DesignPatterns_5;

import java.util.Scanner;
// 结构型模式 -- 适配器模式
interface TypeC{
    void chargeWithTypeC();
}

interface USB{
    void chargeWithUSB();
}
// 新电脑类，使用 TypeC 接口
class NewComputer implements TypeC {
    @Override
    public void chargeWithTypeC() {
        System.out.println("TypeC");
    }
}

// USB充电器类，使用 USB 接口
class USBCharger implements USB {
    @Override
    public void chargeWithUSB() {
        System.out.println("USB Adapter");
    }
}

class typeC2USBAdapter implements TypeC{

    private USB usb;

    public typeC2USBAdapter(USB usb) {
        this.usb = usb;
    }

    @Override
    public void chargeWithTypeC() {
        usb.chargeWithUSB();
    }
}
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TypeC c = null; // 定义一个原始的接口
        int n = scanner.nextInt();
        // 有着不同的输入流程，但都是调用相同的原始接口
        for (int i = 0; i < n; i++){
            int m = scanner.nextInt();
            if(m == 1){
                c = new NewComputer();  // 原始类
            }else if(m == 2){
                c = new typeC2USBAdapter(new USBCharger()); // 适配器(被适配者)
            }
            c.chargeWithTypeC();  // 调用原始接口
        }
        scanner.close();
    }
}

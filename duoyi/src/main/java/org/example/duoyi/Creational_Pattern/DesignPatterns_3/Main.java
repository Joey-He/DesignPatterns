package org.example.duoyi.Creational_Pattern.DesignPatterns_3;


import java.util.Scanner;

interface Chair{
    void pruduce();
}

interface Sofa{
    void pruduce();
}

class modernChair implements Chair{
    @Override
    public void pruduce() {
        System.out.println("modern chair");
    }
}

class modernSofa implements Sofa{
    @Override
    public void pruduce() {
        System.out.println("modern sofa");
    }
}

class classicalChair implements Chair{
    @Override
    public void pruduce() {
        System.out.println("classical chair");
    }
}

class classicalSofa implements Sofa{
    @Override
    public void pruduce() {
        System.out.println("classical sofa");
    }
}

interface AbstractFactory{
    Chair createChair();
    Sofa createSofa();
}

class modernFactory implements AbstractFactory{

    @Override
    public Chair createChair() {
        return new modernChair();
    }

    @Override
    public Sofa createSofa() {
        return new modernSofa();
    }
}

class classicalFactory implements AbstractFactory{

    @Override
    public Chair createChair() {
        return new classicalChair();
    }

    @Override
    public Sofa createSofa() {
        return new classicalSofa();
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        for(int j = 0; j < i; j++){
            String style  = scanner.next();
            AbstractFactory factory = null;
            if(style.equals("modern")){
                factory = new modernFactory();
            }else if(style.equals("classical")) {
                factory = new classicalFactory();
            }
            Chair chair = factory.createChair();
            chair.pruduce();
            Sofa sofa = factory.createSofa();
            sofa.pruduce();
        }
    }
}

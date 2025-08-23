package org.example.duoyi.Behavioral_Patterns.DesignPatterns_10;
// 行为模式 -- 观察者模式

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// 观察者
interface Observer {
    void update(int hours);
}
// 被观察者
interface Subject {
    // 注册观察者
    void registerObserver(Observer observer);
    // 移除观察者
    void removeObserver(Observer observer);
    // 通知观察者
    void notifyObservers();
}

class Clock implements Subject {

    private List<Observer> observerList= new ArrayList<>();
    private int hour = 0;
    @Override
    public void registerObserver(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observerList.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observerList) {
            observer.update(hour);
        }
    }

    public void tick() {
        hour = (hour + 1) % 24; // 模拟时间的推移
        notifyObservers();
    }
}

class Student implements Observer {

    private String name;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Override
    public void update(int hours) {
        System.out.println(name + " " + hours);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Clock clock = new Clock();
        for (int i = 0; i < n; i++) {
            String name = scanner.next();
            Student student = new Student();
            student.setName(name);
            clock.registerObserver(student);
        }
        int hours = scanner.nextInt();
        for(int i = 0; i < hours; i++){
            clock.tick();
        }
        scanner.close();
    }
}

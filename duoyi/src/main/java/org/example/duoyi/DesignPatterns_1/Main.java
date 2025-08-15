package org.example.duoyi.DesignPatterns_1;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

//私有的构造函数：防止外部代码直接创建类的实例
//私有的静态实例变量：保存该类的唯一实例
//公有的静态方法：通过公有的静态方法来获取类的实例

 // 饿汉式实现单例
// class ShoppingCart{
//    private static final ShoppingCart instance = new ShoppingCart ();
//
//    // map静态成员变量 key为name value为number
//    private Map<String,Integer> map;
//
//    private ShoppingCart () {
//        map = new HashMap<>();
//    }
//    // 获取单例
//    public static ShoppingCart getInstance() {
//        return instance;
//    }
//    // 添加购物车商品方法
//    public void addItem(String name, Integer number) {
//        map.put(name,map.getOrDefault(name, 0) + number);
//    }
//
//    // 打印map的key和value
//    public void printCat(){
//        for (Map.Entry<String, Integer> entry : map.entrySet()) {
//            System.out.println(entry.getKey() + " " + entry.getValue());
//        }
//    }
//}
// 懒汉式实现单例 考虑多线程
class ShoppingCart{
    private static ShoppingCart instance;
    // 如果还要考虑Map的线程安全，那么可以使用ConcurrentHashMap
    private Map<String,Integer> map;
    private ShoppingCart () {
        map = new ConcurrentHashMap<>();
    }
    // 双重校验锁
    public static ShoppingCart getInstance() {
        if (instance == null) {
            synchronized (ShoppingCart.class){
                if (instance == null) {
                    instance = new ShoppingCart();
                }
            }
        }
        return instance;
    }
     //添加购物车商品方法
    public void addItem(String name, Integer number) {
        map.put(name, map.getOrDefault(name, 0) + number);
    }

    // 打印map的key和value 保证顺序打印
    public void printCat(){
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}
public class Main {
    // 饿汉式
    public static void main(String[] args) {
        ShoppingCart shoppingCart=  ShoppingCart.getInstance();
        Scanner scanner = new Scanner(System.in);
        // 控制台循环输入map的key和value
        while (scanner.hasNext()) {
            String key = scanner.next();
            int value = scanner.nextInt();

            if (key.equals("exit")) {
                break;
            }
            shoppingCart.addItem(key, value);
        }
        scanner.close();
        shoppingCart.printCat();
    }
}

package org.example.duoyi.Structural_Patterns.DesignPatterns_6;
// 结构型模式 -- 代理模式
import java.util.Scanner;
// 抽象主题
interface HomePurchase {
    void requestPurchase(int area);
}

// 真实主题
class HomeBuyer implements HomePurchase {
    @Override
    public void requestPurchase(int area) {
        System.out.println("YES");
    }
}
// 代理
class HomeBuyerProxy implements HomePurchase {
    private HomeBuyer homeBuyer = new HomeBuyer();
    @Override
    public void requestPurchase(int area) {
        if (area < 100) {
            System.out.println("NO");
        } else {
            homeBuyer.requestPurchase(area);
        }
    }
}
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 代理对象 对买房子者要买房进行代理 -- 决策哪些房子符合买房子的条件，进行推荐
        HomePurchase homeBuyerProxy = new HomeBuyerProxy();
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++){
            int area = scanner.nextInt();
            homeBuyerProxy.requestPurchase(area);
        }
        scanner.close();
    }
}

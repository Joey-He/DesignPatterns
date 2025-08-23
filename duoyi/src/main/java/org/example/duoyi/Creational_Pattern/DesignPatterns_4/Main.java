package org.example.duoyi.Creational_Pattern.DesignPatterns_4;

import java.util.Scanner;

// 自行车产品
class Bike {
    private String frame;
    private String tires;

    public void setFrame(String frame) {
        this.frame = frame;
    }

    public void setTires(String tires) {
        this.tires = tires;
    }

    @Override
    public String toString() {
        return frame + " " + tires;
    }
}

// 自行车建造者接口 （BikeBuilder只定义构建步骤，不关心具体的构建顺序（由 Director控制）。）
interface BikeBuilder {
    void buildFrame();
    void buildTires();
    Bike getResult();
}
// 相同的构建步骤（buildFrame()+ buildTires()），但不同的实现
// 山地自行车建造者
class MountainBikeBuilder implements BikeBuilder {
    private Bike bike;

    public MountainBikeBuilder() {
        this.bike = new Bike();
    }

    @Override
    public void buildFrame() {
        bike.setFrame("Aluminum Frame");
    }

    @Override
    public void buildTires() {
        bike.setTires("Knobby Tires");
    }

    @Override
    public Bike getResult() {
        return bike;
    }
}

// 公路自行车建造者
class RoadBikeBuilder implements BikeBuilder {
    private Bike bike;

    public RoadBikeBuilder() {
        this.bike = new Bike();
    }

    @Override
    public void buildFrame() {
        bike.setFrame("Carbon Frame");
    }

    @Override
    public void buildTires() {
        bike.setTires("Slim Tires");
    }

    @Override
    public Bike getResult() {
        return bike;
    }
}

// 自行车Director，负责构建自行车（只关心构建流程（先造车架，再装轮胎），不关心具体实现）
class BikeDirector {
    public Bike construct(BikeBuilder builder) {
        builder.buildFrame();
        builder.buildTires();
        return builder.getResult();
    }
}
//客户端只需指定 BikeBuilder类型（山地车或公路车），Director负责执行相同的构建流程。
//构建过程（construct）不变，但 最终产品表示不同（MountainBikevs. RoadBike）。
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();  // 订单数量
        scanner.nextLine();

        BikeDirector director = new BikeDirector();

        for (int i = 0; i < N; i++) {
            String bikeType = scanner.nextLine();

            BikeBuilder builder;
            // 根据输入类别，创建不同类型的具体建造者  -- 构建过程
            if (bikeType.equals("mountain")) {
                builder = new MountainBikeBuilder();
            } else {
                builder = new RoadBikeBuilder();
            }
            // Director负责指导生产产品。-- 表示过程
            Bike bike = director.construct(builder);
            System.out.println(bike);
        }
        scanner.close();
    }
}
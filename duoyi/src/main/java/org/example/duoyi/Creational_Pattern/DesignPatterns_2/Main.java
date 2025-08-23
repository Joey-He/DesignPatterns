package org.example.duoyi.Creational_Pattern.DesignPatterns_2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

interface Block{
    void produce();
}
 class Circle implements Block{
    @Override
    public void produce() {
        System.out.println("Circle Block");
    }
}
 class Square implements Block{
    @Override
    public void produce() {
        System.out.println("Square Block");
    }
}
interface BlockFactory{
    Block creatBlock();
}

class CricleBlockFactory implements BlockFactory{
    @Override
    public Block creatBlock() {
        return new Circle();
    }
}

class SquareBlockFactory implements BlockFactory{
    @Override
    public Block creatBlock() {
        return new Square();
    }
}

class BlockFactorySystem {

    // 存放创建的方块
    private List<Block> blocks = new ArrayList<>();

    public void produceBlocks(BlockFactory shapeFactory,Integer number){
        for(int i =0; i < number; i++){
            Block block = shapeFactory.creatBlock();
            blocks.add(block);
            block.produce();
        }
    }

    public List< Block> getBlocks(){
        return blocks;
    }
}
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 创建积木工厂系统
        BlockFactorySystem blockFactorySystem = new BlockFactorySystem();

        int i = scanner.nextInt();
        for(int j = 0; j < i; j++){
            String shapeType = scanner.next();
            Integer number = scanner.nextInt();
            if(shapeType.equals("Circle")){
                blockFactorySystem.produceBlocks(new CricleBlockFactory(), number);
            }else if(shapeType.equals("Square")){
                blockFactorySystem.produceBlocks(new SquareBlockFactory(), number);
            }
        }
        scanner.close();
        //获取blocks列表实体对象
//        blockFactorySystem.getBlocks().forEach(System.out::println);
    }
}

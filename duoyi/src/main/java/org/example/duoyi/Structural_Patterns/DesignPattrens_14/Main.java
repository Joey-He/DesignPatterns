package org.example.duoyi.Structural_Patterns.DesignPattrens_14;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

// 【组合模式】-- 公司组织架构
interface Component{
    void display(int depth);
}

class Department implements Component{
    private String name;
    private List<Component> children;
    public Department(String name)
    {
        this.name = name;
        this.children = new ArrayList<>();
    }

    public void add(Component component)
    {
        children.add(component);
    }
    public void remove(Component component)
    {
        children.remove(component);
    }
    @Override
    public void display(int depth)
    {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < depth; i++){
            sb.append("  ");
        }
        System.out.println(sb + name);
        for(Component component : children){
            component.display(depth + 1);
        }
    }
}

class Employee implements Component
{
    private String name;

    public Employee(String name)
    {
        this.name = name;
    }

    @Override
    public void display(int depth) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < depth; i++){
            sb.append("  ");
        }
        System.out.println(sb + name);
    }
}

class Company {
    private String name;
    private Department root;
    public Company(String name){
        this.name = name;
        this.root = new Department(name);
    }

    //------提供获取Department的方法------
    public Department getRoot() { // 提供获取根部门的方法
        return root;
    }

    public void display() {
        System.out.println("Company Structure:");
        root.display(0);  // 从 1 开始，以适配指定的缩进格式
    }
}
public class Main {
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        // 读取公司名称
        String companyName = scanner.nextLine();
        Company company = new Company(companyName);

        // 读取部门和员工数量
        int n = scanner.nextInt();
        scanner.nextLine();
//---------------实现“多层级部分”的业务代码-----------------
        //todo —— 核心修改部分：使用 栈 来管理部门层级，以实现多层部门，
        //                      因为用 栈 可以跟踪当前正在构建的部门路径。
        //                      栈顶的部门始终是当前正在添加子部门或员工的直接父部门（直接父部门会不断被更新）。
        Stack<Department> departmentStack = new Stack<>();  //顺序栈
        departmentStack.push(company.getRoot());        // 将公司根部门（MyCompany）压入栈底，作为最顶层的父部门

        // ------ 关键变量（仅针对当前卡码网的这道题目） ------
        Department lastCreatedDepartment = company.getRoot(); // 记录最近创建的部门，用于无缩进格式输入的员工
        // ------ 关键变量（仅针对当前卡码网的这道题目） ------


        // 读取部门和员工信息
        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine(); // 读取整行，包括前导空格（因为前导空格直接决定了层级的判断）

            // 1. 计算当前行的缩进深度（以此为依据来判断部分的层级关系）
            int currentDepth = 0;
            while (currentDepth < line.length() && line.charAt(currentDepth) == ' ') {
                currentDepth++;
            }
            // 获取实际内容（包含currentDepth索引位置处的元素）
            String validContent = line.substring(currentDepth);

            // 计算当前元素所在的逻辑层级
            // 假设每个缩进层级是2个空格（以2个空格的缩进 作为层级变化的判断标准）
            int currentLevel = currentDepth / 2;

            // 2.获取组件信息
            String[] components = validContent.split(" ", 2);

            String type = components[0];
            String name = components[1];

            // 3. 根据缩进深度 动态调整栈
            if (currentDepth == 0) {    //如果输入的当前行没有缩进，就采取默认行为
                if ("D".equals(type)) {
                    // 对于0缩进的部门，总是默认添加到公司根下（MyCompany）
                    Department department = new Department(name);
                    company.getRoot().add(department);

                    // 更新 lastCreatedDepartment
                    lastCreatedDepartment = department;

                    // 清空栈，只保留根，然后压入新部门，因为它是新的顶级部门
                    departmentStack.clear();
                    departmentStack.push(company.getRoot());
                    departmentStack.push(department);
                } else if ("E".equals(type)) {
                    // 对于0缩进的员工，默认添加到最近创建的部门下（即离他最近的上一个部门）
                    Employee employee = new Employee(name);
                    lastCreatedDepartment.add(employee);

                    // 注意：这里不会影响栈，因为员工不压栈
                }
            } else {    //如果输入的当前行有缩进，说明要处理多层级部门

                //---------------实现“多层级部分”的业务代码-----------------
                /*
                    如果当前层级 currentLevel 小于栈顶元素的层级，说明有部门结束了，需要从栈中弹出父部门
                    下面while循环中的 (departmentStack.size() - 1) 是指栈顶的层级。（要求输入合法）
                */
                while (currentLevel < departmentStack.size() - 1) {
                    // System.out.println("Popping " + departmentStack.peek().getName() + " from stack. Current level: " + currentLevel); // Debugging
                    departmentStack.pop();
                }

                // 4. 获取当前父部门 (栈顶元素)，但不弹出
                Department currentParent = departmentStack.peek();

                if ("D".equals(type)) {
                    Department department = new Department(name);
                    currentParent.add(department);      // 将新部门添加到当前父部门下
                    departmentStack.push(department);   // 新加入的部门作为新的父部门，压入栈
                    lastCreatedDepartment = department; // 更新 lastCreatedDepartment
                } else if ("E".equals(type)) {
                    Employee employee = new Employee(name);
                    currentParent.add(employee);        // 将员工添加到当前父部门下
                    // 因为员工是叶子节点，所以不需要进行压栈
                }
            }
        }

        // 输出公司组织结构
        company.display();
        // 释放资源
        scanner.close();
    }
}

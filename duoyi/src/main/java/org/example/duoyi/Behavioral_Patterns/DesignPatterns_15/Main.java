package org.example.duoyi.Behavioral_Patterns.DesignPatterns_15;

import java.util.Scanner;

// 【责任链模式】-- 请假审批
interface LeaveHandler{
    void handleLeave(LeaveRequest request);
}
class LeaveRequest{
    private String name;
    private int days;
    public LeaveRequest(String name, int days) {
        this.name = name;
        this.days = days;
    }
    public String getName() {
        return name;
    }
    public int getDays() {
        return days;
    }
}
class Supervisor implements LeaveHandler{
    private static final int MAX_DAYS_SUPERVISOR_CAN_APPROVE = 3;
    private LeaveHandler nextHandler;

    public Supervisor(LeaveHandler nextHandler) {
        this.nextHandler = nextHandler;
    }
    @Override
    public void handleLeave(LeaveRequest request) {
        if(request.getDays()<=MAX_DAYS_SUPERVISOR_CAN_APPROVE){
            System.out.println(request.getName() + " Approved by Supervisor.");
        }else if (nextHandler != null) {
            nextHandler.handleLeave(request);
        }else {
            System.out.println(request.getName() + " Denied by Supervisor.");
        }
    }
}
class Manager implements LeaveHandler {
    private static final int MAX_DAYS_MANAGER_CAN_APPROVE = 7;
    private LeaveHandler nextHandler;

    public Manager(LeaveHandler nextHandler){
        this.nextHandler = nextHandler;
    }
    @Override
    public void handleLeave(LeaveRequest request) {
        if(request.getDays() <= MAX_DAYS_MANAGER_CAN_APPROVE){
            System.out.println(request.getName() + " Approved by Manager.");
        }else if(nextHandler != null){
            nextHandler.handleLeave(request);
        }else{
            System.out.println(request.getName() + " Denied by Manager.");
        }
    }
}
class Director implements LeaveHandler {
    private static final int MAX_DAYS_DIRECTOR_CAN_APPROVE = 10;
    @Override
    public void handleLeave(LeaveRequest request) {
        if(request.getDays() <= MAX_DAYS_DIRECTOR_CAN_APPROVE){
            System.out.println(request.getName() + " Approved by Director.");
        }else{
            System.out.println(request.getName() + " Denied by Director.");
        }
    }
}
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LeaveHandler director = new Director();
        LeaveHandler manager = new Manager(director);
        LeaveHandler supervisor = new Supervisor(manager);
        int n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; i++) {
            String[] input = scanner.nextLine().split(" ");
            if (input.length == 2) {
                String name = input[0];
                int days = Integer.parseInt(input[1]);
                LeaveRequest request = new LeaveRequest(name, days);
                supervisor.handleLeave(request);
            } else {
                System.out.println("Invalid input");
                return;
            }
        }
        scanner.close();
    }
}

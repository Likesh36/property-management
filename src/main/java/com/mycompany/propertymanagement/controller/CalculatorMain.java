package com.mycompany.propertymanagement.controller;

public class CalculatorMain {
    public static void main(String[] args){
        CalculatorController cc = new CalculatorController();
         Double result = cc.add(4.5,3.6,2.7);
         System.out.println(result);
    }
}

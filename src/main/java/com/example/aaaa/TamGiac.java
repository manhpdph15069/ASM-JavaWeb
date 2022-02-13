package com.example.aaaa;

import java.util.Scanner;

public class TamGiac {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập dài:");
        int d = Integer.parseInt(sc.nextLine());
//        System.out.println("Nhập rộng:");
//        int r = Integer.parseInt(sc.nextLine());
//        for (int i = 1; i <= d; i++) {
//            for (int j = 1; j <= r; j++) {
//                if (i == 1 || i == d || j == 1 || j == r) {
//                    System.out.print(" * ");
//                } else {
//                    System.out.print("   ");
//
//                }
//            }
//            System.out.println();
//        }

        for (int i = 0;i<d-1;i++){
            for (int j =0;j<d;j++){
                if (j==0 || j==i){
                System.out.print(" * ");
                }else {
                    System.out.print("   ");
                }
            }
            System.out.println();
        }
        for (int i=0;i<d;i++){
            System.out.print(" * ");
        }
    }
}

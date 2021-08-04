package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        System.out.println("Hello, world");
        var scan = new Scanner(System.in);
        var message = scan.nextLine();
        var array = message.split(" ");

        for(var element : array) {

            System.out.println(element);
        }
        System.out.println(message);


    }
}

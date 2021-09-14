package com.company;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.File;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        //Read in file name as input
        Map<String, String> values = null;
        Scanner s = new Scanner(System.in);
        String filename = s.next();
        //Scan each line of the file one by one
        File file = new File(filename);
        Scanner f = new Scanner(file);
        evaluate(f, values);
        

    }

    public static String evaluate(Scanner s, Map<String, String> vals){
        Stack<String> stack = new Stack<String>();
        String line = s.nextLine();
        //Read each token in the line and add it to the stack
        while(line != null) {
            Scanner l = new Scanner(line);
            String output = l.next();
            while (l.hasNext()) {
                stack.push(output);
                output = l.next();
            }
            stack.push(output);
            System.out.println(stack.size());
            System.out.println(stack.pop());
            try{
                line = s.nextLine();
            } catch (NoSuchElementException n){
                break;
            }
        }
        return "";
    }
}

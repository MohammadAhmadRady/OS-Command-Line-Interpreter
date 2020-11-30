package com.company;
import java.util.Scanner;
public class Main
{
    public static void main(String[] args)  throws Exception
    {
        Scanner input = new Scanner(System.in);
        Parser parser =new Parser();
        String cmd = "";
        while (true)
        {
            System.out.print("\n $ ");
            cmd = input.nextLine();
            parser.parse(cmd);
        }

    }
}
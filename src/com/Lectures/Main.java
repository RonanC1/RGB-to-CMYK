package com.Lectures;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        //create a pattern that will either accept 0-9, 10-49 or 50 as input
        String pattern = "^1?[0-9]?[0-9]|2[0-4][0-9]|25[0-5]$";
        String alphaPattern = "^.*[a-zA-Z].*$";
        String numberPattern = "^[0-9]+$";
        int range = 255;//max number accepted as input
        boolean flag = true;

        //this section is for error checking
        //first check if no input was provided
        if (args.length == 0) {
            flag = false;
            JOptionPane.showConfirmDialog(null, "No input given");
        }
        //then check if incorrect number of input
        else if (args.length != 3) {
            flag = false;
            JOptionPane.showConfirmDialog(null, "Incorrect number of command line arguments. 3 arguments needed.");
        }

        //if true, we check each argument for errors with formatting one by one using a for loop
        if (flag) {
            for (int i = 0; i < args.length; i++) {
                //check for letters using another regex
                if (args[i].matches(alphaPattern)) {
                    flag = false;
                    JOptionPane.showConfirmDialog(null, "Alphabetic character present in argument " + (i + 1) + ". No alphabetic characters allowed");
                }
                //error message if out of range
                else if (args[i].matches(numberPattern) && Integer.parseInt(args[i]) > range) {
                    flag = false;
                    JOptionPane.showConfirmDialog(null, "Arguement " + (i + 1) + " is out of range");
                }
                //finally, compare to regex to check if anything else is wrong
                else if (!args[i].matches(pattern)) {
                    flag = false;
                    JOptionPane.showConfirmDialog(null, "Incorrect format for arguement " + (i + 1) + ".");
                }
            }
        }


        //take input of 3 arguments from the command line
        if (flag) {
            float red = Float.parseFloat(args[0]) / 255f;
            float green = Float.parseFloat(args[1]) / 255f;
            float blue = Float.parseFloat(args[2]) / 255f;
            //System.out.println("r " + red + "g " + green  + "b " + blue);
            //first get black by subtracting 1 by the max RGB value
            float black = 1 - (Math.max(Math.max(red, green), blue));

            //then get the cyan, magenta and yellow values using the appropriate formulae
            float cyan = (1 - red - black) / (1 - black);
            float magenta = (1 - green - black) / (1 - black);
            float yellow = (1 - blue - black) / (1 - black);

            //if the user passes 0 0 0 , output will be NaN NaN NaN
            //if all variables are Not a Number, we assign them each the value of 0.0
            if (Float.isNaN(cyan) && Float.isNaN(magenta) && Float.isNaN(yellow)) {
                cyan = 0.0f;
                magenta = 0.0f;
                yellow = 0.0f;
            }
            //output data to two decimal places. .2f is used to round to two decimal places
            System.out.printf("Cyan\tMagenta\tYellow\tBlack\n%.2f\t%.2f\t%.2f\t%.2f\n", cyan, magenta, yellow, black);
            //System.out.println("c " + cyan + " m " + magenta + " y "  + yellow + " k " + black);

        }


    }

}

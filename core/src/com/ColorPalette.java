package com;

public class ColorPalette {
    int[] color = new int[3];
    public void colorRed(int values){
        switch (values) {
            case 100:   color[0] = 255;
                        color[1] = 0;
                        color[2] = 0;
                break;
            case 200:  monthString = "February";
                break;
            case 300:  monthString = "March";
                break;
            case 400:  monthString = "April";
                break;
            case 500:  monthString = "May";
                break;
            case 600:  monthString = "June";
                break;
            case 700:  monthString = "July";
                break;
            case 800:  monthString = "August";
                break;
            case 900:  monthString = "September";
                break;
            case -100: monthString = "October";
                break;
            case -200: monthString = "November";
                break;
            case -300: monthString = "December";
                break;
            default: monthString = "Invalid month";
                break;
        }
    }
}

package com.team3.techniko;

import com.team3.techniko.ui.WelcomeScreen;
import com.team3.techniko.util.Data;

public class Techniko extends Exception {

    public static void main(String[] args) throws Exception {
        /**
         * 4.1 Data population
         */
        //new Data().insertDummyData();
        
        // start of the program...
        new WelcomeScreen().login();
    }
}

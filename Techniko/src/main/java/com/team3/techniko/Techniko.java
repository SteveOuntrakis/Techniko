package com.team3.techniko;

import com.team3.techniko.ui.WelcomeScreen;
import com.team3.techniko.utils.Data;

public class Techniko extends Exception {

    public static void main(String[] args) throws Exception {
        //new Data().insertDummyData();
        new WelcomeScreen().login();
    }
}

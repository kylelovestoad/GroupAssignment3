package com.jkn;

import com.jkn.view.CLI;


public class Main {

    public static void main(String[] args) {
        try {
            new CLI().run();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

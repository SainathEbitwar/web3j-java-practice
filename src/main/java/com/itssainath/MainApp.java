package com.itssainath;


import com.itssainath.utils.Utility;

import java.io.IOException;

public class MainApp {

    public static void main( String[] args ) throws IOException {

        Utility utility = new Utility();

        String response = utility.sendingSynchronousRequest();

        System.out.println(response);

    }
}

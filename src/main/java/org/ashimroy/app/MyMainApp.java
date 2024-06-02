package org.ashimroy.app;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain
public class MyMainApp {

    public static void main(String[] args) {
        System.out.println("Starting My Quarkus Application...");
        Quarkus.run(args);
    }
}
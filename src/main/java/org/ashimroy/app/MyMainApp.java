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

/*
 * The main class is annotated with @QuarkusMain. This annotation tells Quarkus that this is the main class of the application.
 * The main method calls Quarkus.run(args) to start the Quarkus application.
 * The main method also prints a message to the console when the application starts.
 * This class bootstraps the Quarkus application and starts it. It's a minimalistic and appropriate setup for starting the application.
 * 
 */
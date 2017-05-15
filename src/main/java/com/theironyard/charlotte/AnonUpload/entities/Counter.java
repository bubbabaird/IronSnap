package com.theironyard.charlotte.AnonUpload.entities;
import java.lang.*;

public class Counter implements Runnable {
    // Here the abstract run() method is defined in the Runnable interface
    // and is being implemented. Note that we have an instance of the Runnable
    // class as a variable of the Counter class.
    Thread T;

    public void run() {
        // An interface only provides a design upon which classes should be implemented.
        // In the case of the Runnable interface, it forces the definition of only the
        // run() method. Therefore, most of the work is done in the Runnable class.
    }
}
package com.theironyard.charlotte.AnonUpload;

import com.theironyard.charlotte.AnonUpload.entities.AnonFile;
import com.theironyard.charlotte.AnonUpload.services.AnonFileRepository;

import java.io.File;

/**
 * Created by BUBBABAIRD on 5/15/17.
 */
public class DeleteThread implements Runnable {
    private AnonFile file;
    private AnonFileRepository repo;

    // DeleteThread accepts two parameters:
    // the first file of type "AnonFile" (first parameter)
    // and where the datasource of that file is stored: "AnonFileRepository" (second parameter)
    public DeleteThread(AnonFile file, AnonFileRepository repo) {
        this.file = file;
        this.repo = repo;
    }

    @Override
    public void run() {
        // make this thread wait for the number of seconds
        // as defined in "file"

        try {
            Thread.sleep(file.getExpirationTime() * 1000);
        }  catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        // use the data in "file" to delete the file from our disk
        File f = new File("public/files/" + file.getFilename());

        // now delete the file
        f.delete();

        // use the repo to delete the file from the database
        repo.delete(file);

        System.out.println("Stuff");
    }
}

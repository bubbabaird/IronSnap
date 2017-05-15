package com.theironyard.charlotte.AnonUpload;

import com.theironyard.charlotte.AnonUpload.entities.AnonFile;
import com.theironyard.charlotte.AnonUpload.services.AnonFileRepository;

/**
 * Created by BUBBABAIRD on 5/15/17.
 */
public class DeleteThread implements Runnable {
    private AnonFile file;
    private AnonFileRepository repo;

    public DeleteThread(AnonFile file, AnonFileRepository repo) {
        this.file = file;
        this.repo = repo;
    }

    @Override
    public void run() {
        // make this thread wait (zzzz) for the number
        // of seconds as defined in "file"

        // use the data in "file" to delete the file from disk
        // use the repo to delete the file from the database
        System.out.println("Stuff");
    }
}

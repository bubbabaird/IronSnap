package com.theironyard.charlotte.AnonUpload.controllers;

import com.theironyard.charlotte.AnonUpload.DeleteThread;
import com.theironyard.charlotte.AnonUpload.entities.AnonFile;
import com.theironyard.charlotte.AnonUpload.services.AnonFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

//  Turn the AnonUpload project we made in class into a SnapChat-story-like service by making it delete the photos briefly after they are first viewed.

//  Requirements:
//  Delete photos from the database and the disk if they were viewed by the /photos route.
//  You will need to figure out how to accomplish this task. We have not explicitly covered this in class.
//  Hint: Threads are a useful way to run tasks
//  Create an input box in the upload form to let the user specify how many seconds they want the photo to exist.
//  Store this number in each "Photo" entity
//  If no number is specified, use 10 seconds as a default.

@RestController
public class AnonFileController {
    @Autowired
    AnonFileRepository files;

    @RequestMapping(path = "/upload", method = RequestMethod.POST)
    // a "MultipartFile" represents the file that we will be uploading
    // and "HttpServletResponse" is how we can modify the response before it is sent back, so in this case, we will be redirecting.
    public void upload(MultipartFile file, Integer expirationTime, HttpServletResponse response) throws IOException {
        if (expirationTime == null) {
            expirationTime = 10;
        }
        // even though the class name is "File",
        // what we are doing is referencing a directory.
        // the directory is /public/files (relative to the project root)
        File dir = new File("public/files");

        // this call will make a directory if it's missing
        // from the file path we specified above in "dir"
        dir.mkdirs();

        // now create a NEW file that starts with "file" (first parameter)
        // and ends with whatever filename you specified (second parameter)
        // the new file is going to be in "dir" (third parameter)
        // file-abc123-picture.png
        File fileA = File.createTempFile("file", file.getOriginalFilename(), dir);

        // this creates an output stream (which is just a way to read the raw contents of the file)
        FileOutputStream fos = new FileOutputStream(fileA);

        // now write all of the bytes to the output stream in the file upload
        fos.write(file.getBytes());

        // now that we have the file on our filesystem, now we need a reference to it in our database
        // so the first parameter is: the name of the temporary file (which is "file.." as specified above)
        // the second parameter is: the name of the file that you uploaded
        // and the third parameter is: the expirationTime
        AnonFile anonFile = new AnonFile(fileA.getName(), file.getOriginalFilename(), expirationTime);
        // now take the new File object that we've called "anonFile" and save it to the AnonFile "files" repository.
        files.save(anonFile);

        // for regular "non rest" controllers, we would do this:
        // return "redirect:/";

        // but since this IS a "restcontroller", we're doing this instead:
        response.sendRedirect("/");
    }

    @RequestMapping(path = "/files", method = RequestMethod.GET)
    public List<AnonFile> getFiles() throws InterruptedException {

        // get all the photos that currently exist (known)
        for (AnonFile file : files.findAll()) {

        // for each photo, make a new thread (???)
            Thread t = new Thread(new DeleteThread(file, files));
            // MAKE A NEW THREAD
            // DeleteThread -
            // files        -
            // file         -
            // takes in a runnable "DeleteThread"
            // for each photo, create a new thread


            // BEGIN EXECUTION OF THE THREAD
            t.start();
            //start will call
        }


//        public static void sleep {
//            try {
//                Thread.sleep();
//            }
//        }
//
//        public final native void notify(); wait(10);
//        notify(t wait(10) DeleteThread);
//        files.remove();
//        // HAVE THAT THREAD DELETE THE PHOTO (DELETE FROM DB.. THEN.. ??)
//            // files.remove(the thing you want to remove); (known)
//            // delete a file. (???)
        return (List<AnonFile>) files.findAll();
//    }



//     an example of how to use Thread.sleep:
//    public void testMethod() throws InterruptedException {
//        int count = 1;
//
//        while (true) {
//            count++;
//            System.out.println(count);
//            Thread.sleep(1000);
//        }
    }
}

package org.example;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileUtils {

    // Read objects
    public static Object loadObject(String fileName) {
        Object returnObj = null;

        try {
            ObjectInputStream objInStream = new ObjectInputStream(new FileInputStream(fileName));

            returnObj = objInStream.readObject();

            objInStream.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return returnObj;
    }


    // Save objects
    public static void saveObject(Object objectToSave, String fileName) {
        try {
            FileOutputStream outStream = new FileOutputStream(fileName);
            ObjectOutputStream objStream = new ObjectOutputStream(outStream);

            objStream.writeObject(objectToSave);

            objStream.close();
            outStream.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}

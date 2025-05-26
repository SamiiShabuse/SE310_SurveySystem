package utils;

import java.io.*;

public class Serializer {

    public static void serialize(Object obj, String path) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path))) {
            out.writeObject(obj);
            System.out.println("Saved to: " + path);
        } catch (IOException e) {
            System.out.println("Failed to save: " + e.getMessage());
        }
    }

    public static Object deserialize(String path) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(path))) {
            return in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Failed to load: " + e.getMessage());
            return null;
        }
    }
}

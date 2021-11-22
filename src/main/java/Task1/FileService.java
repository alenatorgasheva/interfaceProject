package Task1;

import java.io.*;
import java.util.ArrayList;

public class FileService {
    private static FileService instance;

    private FileService() {}

    public static synchronized FileService getInstance() {
        if (instance == null) {
            instance = new FileService();
        }
        return instance;
    }

    public ArrayList<Student> readFile(String fileName) throws IOException {
        // чтение файла
        FileReader fileIn = null;
        String data = "";

        try {
            fileIn = new FileReader(fileName);
            int a;
            while ((a = fileIn.read()) != -1) {
                data = data + (char) a;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            if (fileIn != null) {
                fileIn.close();
            }
        }
        return AnotherService.getInstance().decodeData(data);
    }


}

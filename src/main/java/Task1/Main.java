package Task1;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String command = args[0];
        String path = args[1];

        if (command.equals("updateDB")) {
            try {
                ArrayList<Student> students = FileService.getInstance().readFile(path);
                DatabaseService.getInstance().updateDatabase(students, AnotherService.getInstance().getGroupName(path));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

// java -jar student-manager.jar updateDB C:\Users\1292354\IdeaProjects\interfaceProject\src\main\2570

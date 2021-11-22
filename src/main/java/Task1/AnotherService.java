package Task1;

import java.util.ArrayList;

public class AnotherService {
    private static AnotherService instance;

    private AnotherService() {}

    public static synchronized AnotherService getInstance() {
        if (instance == null) {
            instance = new AnotherService();
        }
        return instance;
    }

    public ArrayList<Student> decodeData(String data) {
        // конвертирование строки в коллекцию студентов группы
        ArrayList<Student> accounts = new ArrayList<>();
        String[] rawStudents;
        String[] infoStudent;

        rawStudents = data.split("\n");
        for (String s : rawStudents) {
            infoStudent = s.split(",");
            Student newStudent = new Student(infoStudent[0], infoStudent[1], infoStudent[2], infoStudent[3]);
            accounts.add(newStudent);
        }
        return accounts;
    }

    public String getGroupName(String path) {
        int i = path.lastIndexOf("\\");
        String groupName = path.substring(i + 1);
        return groupName;
    }
}

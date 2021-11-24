package Task1;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseService {
    private String url = "jdbc:mysql://localhost:3306/mydb2";
    private String user = "root";
    private String password = "password";
    private static DatabaseService instance;

    private DatabaseService() {}

    public static synchronized DatabaseService getInstance() {
        if (instance == null) {
            instance = new DatabaseService();
        }
        return instance;
    }

    public void updateDatabase(ArrayList<Student> students, String groupName) {

        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement()) {
            for (Student student : students) {
                // поиск id текущей группы
                int groupId = -1;
                String query = "SELECT * FROM `group` WHERE (`group_name` = '" + groupName + "');";
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    groupId = resultSet.getInt("id");
                }
                // если группы нет в бд
                if (groupId == -1) {
                    query = "INSERT INTO `group` (`group_name`) " + "VALUES ('" + groupName + "');";
                    statement.executeUpdate(query);
                    // находим id новой группы
                    query = "SELECT * FROM `group` WHERE (`group_name` = '" + groupName + "');";
                    resultSet = statement.executeQuery(query);
                    while (resultSet.next()) {
                        groupId = resultSet.getInt("id");
                    }
                }

                // проверяем, есть ли студент в бд
                boolean isStudentExist = false;
                query = "SELECT * FROM student WHERE (`last_name` = '" + student.getLastName() + "') AND (`first_name` = '" + student.getFirstName() + "') AND (`second_name` = '" + student.getSecondName() + "') AND (`birthday_date` = '" + student.getBirthdayDate() + "');";
                resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    isStudentExist = true;
                }
                if (!isStudentExist) {
                    // добавляем студента в бд
                    query = "INSERT INTO student (`group_id`, `last_name`, `first_name`, `second_name`, `birthday_date`) VALUES ('" + groupId + "', '" + student.getLastName() + "', '" + student.getFirstName() + "', '" + student.getSecondName() + "', '" + student.getBirthdayDate() + "');";
                    statement.executeUpdate(query);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package Task1;

import lombok.Data;

@Data
public class Student {
    private String lastName;
    private String firstName;
    private String secondName;
    private String birthdayDate;

    public Student(String lastName, String firstName, String secondName, String birthdayDate) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.secondName = secondName;
        this.birthdayDate = birthdayDate;
    }
}

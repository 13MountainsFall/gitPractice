package org.hugh;

/**
 * @author adward
 * @date 2023/11/16 15:52
 */
public class Student {
    private String name ;
    private String classroom;

    public Student(String name, String classroom) {
        this.name = name;
        this.classroom = classroom;
    }

    public String getName() {
        return name;
    }

    public String getClassroom() {
        return classroom;
    }

    public Student() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", classroom='" + classroom + '\'' +
                '}';
    }
}

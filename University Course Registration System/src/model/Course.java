package model;

import java.util.*;

import model.Student;

public class Course {
    public String courseId, courseName, instructorName;
    public int capacity, credits;
    public TreeSet<Student> enrolledStudents;
    public Queue<Student> waitlist;

    public Course(String courseId, String courseName, String instructorName, int capacity, int credits) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.instructorName = instructorName;
        this.capacity = capacity;
        this.credits = credits;
        this.enrolledStudents = new TreeSet<>(new Comparator<Student>() {
            public int compare(Student a, Student b) {
                int cmp = a.name.compareTo(b.name);
                if (cmp == 0) return a.id.compareTo(b.id);
                return cmp;
            }
        });
        this.waitlist = new LinkedList<>();
    }

    public boolean isFull() {
        return enrolledStudents.size() >= capacity;
    }

    public void addStudent(Student s) {
        if (!enrolledStudents.contains(s) && !waitlist.contains(s)) {
            if (!isFull()) enrolledStudents.add(s);
            else waitlist.offer(s);
        }
    }

    public void dropStudent(Student s) {
        if (enrolledStudents.remove(s)) {
            if (!waitlist.isEmpty()) {
                Student next = waitlist.poll();
                enrolledStudents.add(next);
            }
        } else {
            waitlist.remove(s);
        }
    }

    public boolean hasStudent(Student s) {
        return enrolledStudents.contains(s) || waitlist.contains(s);
    }
}
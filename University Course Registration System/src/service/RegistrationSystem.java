package service;

import model.Course;
import model.Student;
import util.EmailValidator;

import java.util.*;

public class RegistrationSystem {
    private HashMap<String, Student> studentMap = new HashMap<>();
    private HashMap<String, Course> courseMap = new HashMap<>();

    public void addStudent(Student s) {
        if (!studentMap.containsKey(s.id)) studentMap.put(s.id, s);
    }

    public void addCourse(Course c) {
        if (!courseMap.containsKey(c.courseId)) courseMap.put(c.courseId, c);
    }

    public void register(String studentId, String courseId) {
        Student s = studentMap.get(studentId);
        Course c = courseMap.get(courseId);

        if (s == null || c == null || !EmailValidator.isValid(s.email)) {
            System.out.println("Invalid student or course ID/email.");
            return;
        }

        if (c.hasStudent(s)) {
            System.out.println("Student already enrolled or waitlisted.");
            return;
        }

        c.addStudent(s);
        System.out.println("Registration successful.");
    }

    public void drop(String studentId, String courseId) {
        Student s = studentMap.get(studentId);
        Course c = courseMap.get(courseId);

        if (s == null || c == null) {
            System.out.println("Invalid student or course ID");
            return;
        }

        c.dropStudent(s);
        System.out.println("Dropped successfully.");
    }

    public void listStudents(String courseId) {
        Course c = courseMap.get(courseId);
        if (c != null) {
            System.out.println("Enrolled students in " + courseId + ":");
            for (Student s : c.enrolledStudents) System.out.println(s);
        } else {
            System.out.println("Course not found");
        }
    }

    public void listByBranch(String branch) {
        System.out.println("Students from branch: " + branch);
        for (Course c : courseMap.values()) {
            for (Student s : c.enrolledStudents) {
                if (s.branch.equals(branch)) {
                    System.out.println(s + " enrolled in: " + c.courseId);
                }
            }
        }
    }

    public void listCoursesSortedByEnrolledCount() {
        List<Course> courseList = new ArrayList<>(courseMap.values());
        Collections.sort(courseList, new Comparator<Course>() {
            public int compare(Course a, Course b) {
                return b.enrolledStudents.size() - a.enrolledStudents.size();
            }
        });
        System.out.println("Courses sorted by enrollment:");
        for (Course c : courseList) {
            System.out.println(c.courseId + " - " + c.enrolledStudents.size() + " students");
        }
    }

    public void filterCoursesByCredits(int credits) {
        System.out.println("Courses with " + credits + " credits:");
        for (Course c : courseMap.values()) {
            if (c.credits == credits) {
                System.out.println(c.courseId + " - " + c.courseName);
            }
        }
    }

    public void filterCoursesByInstructor(String instructor) {
        System.out.println("Courses by instructor: " + instructor);
        for (Course c : courseMap.values()) {
            if (c.instructorName.equals(instructor)) {
                System.out.println(c.courseId + " - " + c.courseName);
            }
        }
    }

    public void groupCoursesByInstructor() {
        HashMap<String, List<Course>> map = new HashMap<>();
        for (Course c : courseMap.values()) {
            if (!map.containsKey(c.instructorName)) {
                map.put(c.instructorName, new ArrayList<Course>());
            }
            map.get(c.instructorName).add(c);
        }
        for (String instructor : map.keySet()) {
            System.out.println("Instructor: " + instructor);
            for (Course c : map.get(instructor)) {
                System.out.println("  -> " + c.courseId + ": " + c.courseName);
            }
        }
    }
}
package driver;

import util.*;

import java.util.*;
import java.io.*;

public class Main {
    private static boolean verify(String position, String name, String studentID, String teacherID, String phone) {
        if (name.matches("^[a-zA-Z]+,[a-zA-Z]+$") && phone.matches("^[0-9]{10}$")) {
            return switch (position) {

                case "student" -> teacherID.equals("0") && studentID.matches("^[0-9]{5}$");

                case "teacher" -> studentID.equals("0") && teacherID.matches("^[0-9]{5}$");

                case "ta" -> studentID.matches("^[0-9]{5}$") && teacherID.matches("^[0-9]{5}$");

                default -> false;
            };
        }
        return false;
    }

    private static CSVPrintable createPerson(String position, String name, String studentID, String teacherID, String phone) {
        return switch (position) {
            case "student" -> new Student(name, studentID, phone);
            case "teacher" -> new Teacher(name, teacherID, phone);
            default -> new TA(name, studentID, teacherID, phone);
        };
    }

    public static final String OUTPUT_PATH = System.getProperty("user.dir") + "/output/";

    public static void main(String[] args) throws IOException {

        PrintWriter p = new PrintWriter(OUTPUT_PATH + "out.csv");
        CSVPrintable person;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter number of people: ");
        String input = scanner.nextLine();
        while (!input.matches("^[0-9]+$") || input.equals("0")) {
            System.out.println("Incorrect input entered, Please try again: ");
            input = scanner.nextLine();
        }
        int n = Integer.parseInt(input);

        System.out.println("Please enter the info for all " + n + " people, one person per line:");
        for (int i = 0; i < n; i++) {
            String[] tokens = scanner.nextLine().split(" ");
            if (tokens.length != 5) {
                i--;
                System.out.println("Try Again.");
                continue;
            }
            String position = tokens[0].toLowerCase();
            String name = tokens[1];
            String studentID = tokens[2];
            String teacherID = tokens[3];
            String phone = tokens[4];
            if (!verify(position, name, studentID, teacherID, phone)) {
                i--;
                System.out.println("Try again.");
                continue;
            }
            person = createPerson(position, name, studentID, teacherID, phone);
            person.csvPrintln(p);
        }
        p.close();
        System.out.println("Output located at: " + OUTPUT_PATH + "out.csv");

    }
}
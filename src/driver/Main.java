package driver;

import util.*;

import java.util.*;
import java.io.*;

public class Main {
    /**
     * The verify method is a method that verifies all the input parameters match the required format.
     * If it does then it returns true and if not returns false.
     *
     * @param position  is the position of the person. It must be equal to student, teacher, or TA.
     * @param name      is the name of the person. Must be a string made of two words separated by a single comma.
     * @param studentID this is a 5-digit number when position is student or TA, and 0 when position is teacher.
     * @param teacherID this is a 5-digit number when position is teacher or TA, and 0 when position is student.
     * @param phone     this is the phone number of the person, it must be a 10-digit string.
     * @return this method returns true only if all conditions are met, otherwise returns false.
     */
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

    /**
     * This method is called after all parameters are verified, this is what creates the person based on the position
     * and returns the object of the corresponding class.
     *
     * @param position  is the position of the person and determines the class of the returned object.
     * @param name      is the name of the person.
     * @param studentID is the students ID and is only used if position is student or TA.
     * @param teacherID is the teachers ID and is only used if position is teacher or TA.
     * @param phone     is the 10-digit phone number of the person.
     * @return returns the person object designated by the switch case.
     */
    private static CSVPrintable createPerson(String position, String name, String studentID, String teacherID, String phone) {
        return switch (position) {
            case "student" -> new Student(name, studentID, phone);
            case "teacher" -> new Teacher(name, teacherID, phone);
            default -> new TA(name, studentID, teacherID, phone);
        };
    }

    //This is the output path of the csv file. It outputs to a folder named output located in the same directory as this program.
    public static final String OUTPUT_PATH = System.getProperty("user.dir") + "/output/";

    public static void main(String[] args) throws IOException {

        new File(OUTPUT_PATH).mkdirs(); // creates the output folder if not already created.
        PrintWriter p = new PrintWriter(OUTPUT_PATH + "out.csv"); // initializes the output file.
        Scanner scanner = new Scanner(System.in);   // initializes the input scanner.
        CSVPrintable person; // creates an empty person object.

        p.println("Name,ID,Phone");

        System.out.println("Please enter number of people: ");
        String input = scanner.nextLine();                                // stores the input
        while (!input.matches("^[0-9]+$") || input.equals("0")) {   // checks if the input is numeric and >0 and loops if not.
            System.out.println("Incorrect input entered, Please try again: ");
            input = scanner.nextLine();
        }
        int n = Integer.parseInt(input);                                // converts the validated input into an integer.

        System.out.println("Please enter the info for all " + n + " people, one person per line:");
        for (int i = 0; i < n; i++) {

            String[] tokens = scanner.nextLine().split(" ");    // splits input
            if (tokens[0].equals("quit"))
                break;
            if (tokens.length != 5) {                                 // checks the right amount of fields were entered.
                i--;
                System.out.println("Try Again.");
                continue;
            }

            String position = tokens[0].toLowerCase();                // stores position value all in lowercase.
            String name = tokens[1];                                  // stores name.
            String studentID = tokens[2];                             // stores student ID.
            String teacherID = tokens[3];                             // stores teacher ID.
            String phone = tokens[4];                                 // stores phone number.
            if (!verify(position, name, studentID, teacherID, phone)) {     // verifies all values match needed format.
                i--;
                System.out.println("Try again.");
                continue;
            }

            person = createPerson(position, name, studentID, teacherID, phone); // creates the new person object
            person.csvPrintln(p);                                               // prints the persons information to csv file.
        }

        p.close();                                                              // closes the csv file
        System.out.println("Output located at: " + OUTPUT_PATH + "out.csv");

    }
}
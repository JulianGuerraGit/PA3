package driver;
import util.*;
import java.util.*;
import java.io.*;

public class Main {
    private static boolean verify(String position, String name, String studentID, String teacherID, String phone){
        switch(position){
            case "student":
                if(!teacherID.equals("0")){
                    return false;
                }
                break;
            case "teacher":
                if(!studentID.equals("0")){
                    return false;
                }
                break;
            case "TA":
                break;
            default:
                return false;
        }
        return true;
    }
    private static CSVPrintable createPerson(String position, String name, String studentID, String teacherID, String phone){
        CSVPrintable person;
        switch(position){
            case "teacher":
                person = new Teacher();
                break;
            case "TA":
                person = new TA();
                break;
            default:
                person = new Student();

        }
        return person;
    }
    public static final String outputPath = System.getProperty("user.dir") + "/output/";
    public static void main(String[] args) throws IOException{
        // write your code here
        PrintWriter p = new PrintWriter(outputPath + "out.csv");
        CSVPrintable person;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter number of people: ");
        int n = scanner.nextInt();
        while(n < 0){
            System.out.println("Negative number entered, Please try again: ");
            n = scanner.nextInt();
        }
        for(int i = 0; i < n;i++){
            String[] tokens = scanner.nextLine().split(" ");
            if(tokens.length != 5){
                i--;
                System.out.println("Try Again.");
                continue;
            }
            String position = tokens[0];
            String name = tokens[1];
            String studentID = tokens[2];
            String teacherID = tokens[3];
            String phone = tokens[4];
            if(!verify(position, name, studentID, teacherID, phone)){
                i--;
                System.out.println("Try again");
                continue;
            }
            person = createPerson(position, name, studentID, teacherID, phone);
            person.csvPrintln(p);
        }
        p.close();

    }
}
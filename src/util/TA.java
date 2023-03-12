package util;

import java.io.PrintWriter;

public class TA extends Student {
    private int teacherID;

    public TA(String name, String studentID, String teacherID, String phone) {
        super(name, studentID, phone);
        this.teacherID = Integer.parseInt(teacherID);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public int getID() {
        return Math.max(super.getID(), teacherID);
    }

    @Override
    public void csvPrintln(PrintWriter out) {
        String[] names = super.getName().split(",");
        out.printf("%s %s,%d,%d\n", names[0], names[1], getID(), super.getPhone());
    }
}

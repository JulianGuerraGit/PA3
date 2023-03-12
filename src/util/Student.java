package util;

import java.io.PrintWriter;

public class Student implements CSVPrintable {
    private String name;
    private int id;
    private long phone;

    public Student(String name, String id, String phone) {
        this.name = name;
        this.id = Integer.parseInt(id);
        this.phone = Long.parseLong(phone);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getID() {
        return id;
    }

    public long getPhone() {
        return phone;
    }

    @Override
    public void csvPrintln(PrintWriter out) {
        String[] names = name.split(",");
        out.printf("%s %s,%d,%d\n", names[0], names[1], id, phone);
    }
}

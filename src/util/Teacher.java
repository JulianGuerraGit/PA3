package util;

import java.io.PrintWriter;

public class Teacher implements CSVPrintable {
    private String name;
    private int id;
    private int phone;

    public Teacher(String name, String id, String phone) {
        this.name = name;
        this.id = Integer.parseInt(id);
        this.phone = Integer.parseInt(phone.substring(6));
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public void csvPrintln(PrintWriter out) {
        String[] names = name.split(",");
        out.printf("%s %s,%d,%d\n", names[0], names[1], id, phone);
    }
}

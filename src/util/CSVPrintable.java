package util;

import java.io.PrintWriter;

public interface CSVPrintable {
    String getName();

    int getID();

    void csvPrintln(PrintWriter out);
}

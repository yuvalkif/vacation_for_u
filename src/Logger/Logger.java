package Logger;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Logger class for logging successful transactions and errors
 */

public class Logger extends PrintStream {

    private static final String PATH = "log.txt";
    private DateTimeFormatter dtf ;
    private static Logger ourInstance;

    static {
        try {
            ourInstance = new Logger(new FileOutputStream(PATH));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Logger getInstance() {
        return ourInstance;
    }

    private Logger(FileOutputStream out) {
        super(out);
        dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    }

    public void log(String line){
        LocalDateTime now = LocalDateTime.now();
        this.println(line + " " + dtf.format(now));
    }
}

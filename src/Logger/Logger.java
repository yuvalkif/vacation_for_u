package Logger;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Logger class for logging successful transactions and errors
 */

public class Logger {

    private String path = "log.txt";

    private static Logger ourInstance = new Logger();

    public static Logger getInstance() {
        return ourInstance;
    }

    private Logger() {

    }

    public void log(String line){
        try{
            FileWriter writer = new FileWriter(this.path);
            writer.write(line);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}

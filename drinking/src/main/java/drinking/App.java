package drinking;


import org.apache.log4j.Logger;

/**
 * Hello world!
 */
public class App {
    public static final Logger log = Logger.getLogger(App.class);

    public static void main(String[] args) {
        log.debug("debug");
        log.error("error");
        System.out.println("Hello World!");
    }
}

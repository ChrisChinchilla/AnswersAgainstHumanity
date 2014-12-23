package gregariousmammal.com.answersagainsthumanity;

/**
 * Created by chrisward on 23/12/14.
 */

public class Globals {
    private static Globals instance;

    // Global variable
    private String[] answers;

    // Restrict the constructor from being instantiated
    private Globals() {
    }

    public static synchronized Globals getInstance() {
        if (instance == null) {
            instance = new Globals();
        }
        return instance;
    }

    public String[] getData() {
        return this.answers;
    }

    public void setData(String[] d) {
        this.answers = d;
    }
}
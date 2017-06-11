import java.util.ArrayList;

/**
 * Created by jetma on 6/10/2017.
 */
public class Console {
    ArrayList<Message> messages;
    public Console(){
        messages = new ArrayList<Message>();
    }

    public ArrayList<Message> getMessages(){
        return messages;
    }
}

/**
 * Created by jetma on 6/11/2017.
 */
public class Message {
    public static int SYSTEM = 0, LOCAL = 1, STRANGER = 2;
    private String message;
    private int type;

    public Message(String message, int type){
        this.message = message;
        this.type = type;
    }

    public String getMessage(){
        return message;
    }

    public int getType(){
        return type;
    }
}

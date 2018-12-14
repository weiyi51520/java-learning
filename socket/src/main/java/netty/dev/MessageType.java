package netty.dev;

/**
 * @author Yale.Wei
 * @date 2018/12/13 13:40
 */
public enum MessageType {

    SERVICE_REQ((byte) 0),
    SERVICE_RESP((byte) 1),
    ONE_WAY((byte) 2),
    LOGIN_REQ((byte) 3),
    LOGIN_RESP((byte) 4),
    HEARTBEAT_REQ((byte) 5),
    HEARTBEAT_RESP((byte) 6)
    ;

    private final byte value;

    MessageType(byte value) {
        this.value = value;
    }
    public byte value(){
        return value;
    }
}

package netty.dev;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Yale.Wei
 * @date 2018/12/12 15:50
 */
public class Header {
    private int crcCode = 0xabef0101;
    /*消息长度*/
    private int length;
    private long sessionID;
    private byte type;
    private byte priority;
    private Map<String,Object> attachment = new HashMap<>();

    public int getCrcCode() {
        return crcCode;
    }

    public void setCrcCode(int crcCode) {
        this.crcCode = crcCode;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public long getSessionID() {
        return sessionID;
    }

    public void setSessionID(long sessionID) {
        this.sessionID = sessionID;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public byte getPriority() {
        return priority;
    }

    public void setPriority(byte priority) {
        this.priority = priority;
    }

    public Map<String, Object> getAttachment() {
        return attachment;
    }

    public void setAttachment(Map<String, Object> attachment) {
        this.attachment = attachment;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("crcCode", crcCode)
                .append("length", length)
                .append("sessionID", sessionID)
                .append("type", type)
                .append("priority", priority)
                .append("attachment", attachment)
                .toString();
    }
}

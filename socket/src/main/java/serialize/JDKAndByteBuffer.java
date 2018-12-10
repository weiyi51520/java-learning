package serialize;

import java.io.*;
import java.nio.ByteBuffer;

/**
 * @author Yale.Wei
 * @date 2018/12/7 14:27
 */
public class JDKAndByteBuffer {
    public static void main(String[] args) throws IOException {
        UserInfo info = new UserInfo("Netty","112233");
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(bos);
        os.writeObject(info);
        os.flush();
        os.close();
        byte[] bytes = bos.toByteArray();
        System.out.println("The jdk serializable length is : " + bytes.length);
        bos.close();

        System.out.println("=================================");

        System.out.println("The byte array serializable length is : " + info.codeC().length);
    }
}

class UserInfo implements Serializable {

    private String username;

    private String userId;

    public UserInfo(String username, String userId) {
        this.username = username;
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    public byte[] codeC(){
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        byte[] value = username.getBytes();
        buffer.putInt(value.length);
        buffer.put(value);
        buffer.put(userId.getBytes());
        buffer.flip();
        value = null;
        byte[] result = new byte[buffer.remaining()];
        buffer.get(result);
        return result;
    }
}

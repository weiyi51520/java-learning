package zk_2;

import org.I0Itec.zkclient.exception.ZkMarshallingError;
import org.I0Itec.zkclient.serialize.ZkSerializer;

import java.io.UnsupportedEncodingException;

/**
 * @author Yale.Wei
 * @date 2018/12/25 14:42
 */
public class MyZkSerialzer implements ZkSerializer{
    public Object deserialize(byte[] bytes) throws ZkMarshallingError
    {
        try {
            return new String(bytes, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return  null;
    }

    public byte[] serialize(Object obj) throws ZkMarshallingError
    {
        try {
            return String.valueOf(obj).getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return  null;
    }
}

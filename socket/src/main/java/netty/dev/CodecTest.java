package netty.dev;

import java.io.IOException;

/**
 * @author Yale.Wei
 * @date 2018/12/19 19:27
 */
public class CodecTest {
    final static NettyMessage MESSAGE ;


    static {
        MESSAGE = new NettyMessage();
        Header header = new Header();
        header.setType(MessageType.LOGIN_REQ.value());
        MESSAGE.setHeader(header);
    }

    public CodecTest() throws IOException {
    }

    public static void main(String[] args) throws IOException {
        NettyMessageEncoder encoder = new NettyMessageEncoder();
        NettyMessageDecoder decoder = new NettyMessageDecoder(1024 * 1024, 4, 4);

    }
}

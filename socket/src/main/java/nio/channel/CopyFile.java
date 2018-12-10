package nio.channel;


import java.io.*;
import java.nio.channels.FileChannel;

/**
 * @author Yale.Wei
 * @date 2018/12/2 10:34
 */
public class CopyFile {


    public static void main(String[] args) throws IOException {
        String infile = "nio/src/main/resources/CopyFile.java";
        String outfile = "nio/src/main/resources/CopyFile.java.copy";
        File file = new File(infile);

        FileInputStream fin = new FileInputStream(file);
        FileOutputStream fout = new FileOutputStream(outfile);

        FileChannel finChannel = fin.getChannel();
        FileChannel foutChannel = fout.getChannel();

        long position = 0;
        long size = finChannel.size();

        finChannel.transferTo(1,size,foutChannel);
//        ByteBuffer buffer = ByteBuffer.allocate(1024);
//
//        while (true) {
//            buffer.clear();
//
//            int read = finChannel.read(buffer);
//            if (read == -1) break;
//
//            buffer.flip();
//            foutChannel.write(buffer);
//        }
    }
}

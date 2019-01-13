package zk_4;



import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author Yale.Wei
 * @date 2018/12/26 14:44
 */
public class MointorController {

    public static void main(String[] args) throws IOException {
        Socket socket=new Socket("192.168.82.131",2181);
        Scanner scan = new Scanner(System.in);
        System.out.println("请输入命令:");
        while (true){
            String command = scan.nextLine();
            socket.getOutputStream().write(command.getBytes());
            System.out.println("服务器返回:\n"+IOUtils.toString(socket.getInputStream()));
            System.out.println("======================================================");
        }
    }
}

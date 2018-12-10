package nio;

import java.io.IOException;
import java.nio.Buffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author Yale.Wei
 * @date 2018/12/4 11:51
 */
public class SelectorDemo {
    public static void main(String[] args) throws IOException {
        SocketChannel channel = SocketChannel.open();
        //创建Selector
        Selector selector = Selector.open();

        //向Selector注册通道
        //Channel必须处于非阻塞模式下才能与Selector使用，这意味着FileChannel不能使用Selector
        channel.configureBlocking(false);

        //将Channel注册到Selector上 并表示是可读的 返回key
        SelectionKey key = channel.register(selector, (SelectionKey.OP_READ | SelectionKey.OP_WRITE));

//        通道四种interest状态 如果不止一种状态可以用 | 传多个
//        SelectionKey.OP_READ;
//        SelectionKey.OP_ACCEPT;
//        SelectionKey.OP_CONNECT;
//        SelectionKey.OP_WRITE;

        //SelectionKey interestSet
        int interestOps = key.interestOps(); //interest集合 返回了之前注册的 OP_READ | OP_WRITE == 5
        int readyOps = key.readyOps(); //ready集合 是已经准备就绪的操作集合 一次Selection后会首先访问这个ready set

        //可通过key获取 channel 和 selector
        SelectableChannel selectableChannel = key.channel();
        Selector selector1 = key.selector();

        //可以给key添加附加对象 标识区分key
        Object attach = key.attach(new Buffer[]{});
        Object attachment = key.attachment();

        //通过Selector选择通道
        //向Selector注册了通道后，可以调用select()方法,返回的int值表示多少通道已经就绪，这是个同步阻塞方法，若无就绪通道会等待直到有就绪通道.
        //selector.wakeup(); 可以唤醒阻塞select()方法 立马返回
        int ready = selector.select();
        //在确认ready channel > 0 后 获取就绪通道注册的keys
        Set<SelectionKey> selectionKeys = selector.selectedKeys();

        //当像Selector注册Channel时，Channel.register()方法会返回一个SelectionKey 对象。
        //这个对象代表了注册到该Selector的通道。可以通过SelectionKey的selectedKeySet()方法访问这些对象。
        Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
        while (keyIterator.hasNext()){
            SelectionKey next = keyIterator.next();
            if (next.isAcceptable()){
                SelectableChannel readyAcceptChannel = next.channel(); //获取通道
            }
            keyIterator.remove();
        }

        selector.close();//关闭selector 注册的keys全部失效 channel不会失效
    }
}

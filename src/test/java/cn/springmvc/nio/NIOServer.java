package cn.springmvc.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;

/**
 * Created by Vincent on 2017/2/28.
 * Description This is a self-designed Server.
 */
public class NIOServer {
    public static void main(String[] args) throws Exception {
        //  创建ServerSocketChannel并监听8080号端口
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.socket().bind(new InetSocketAddress(8080));
        //  设置非阻塞模式
        ssc.configureBlocking(false);
        //  为ssc注册选择器selector
        Selector selector = Selector.open();
        ssc.register(selector, SelectionKey.OP_ACCEPT);
        //  创建处理器
        Handler handler = new Handler();
        //  使用自定义的Handler
        while (true) {
            int i = 0;
            if (selector.select(3000) == 0) {
                System.out.println("当前无请求 或 等待请求超时");
                continue;
            }
            System.out.println("请求处理中...");
            Iterator<SelectionKey> keyIter = selector.selectedKeys().iterator();
            while (keyIter.hasNext()) {
                SelectionKey key =  keyIter.next();
                try {
                    if (key.isAcceptable()) {
                        handler.handleAccept(key);
                    } else {
                        handler.handleRead(key);
                    }
                } catch (IOException ex) {
                    keyIter.remove();
                    continue;
                }
                keyIter.remove();
            }
            System.out.println("请求处理结束");
            i++;
            if (i == 10) {
                System.out.println("本次服务器已处理十次请求，不再接收新的请求");
                break;
            }
        }
    }
    private static class Handler {
        private int bufferSize = 1024;
        private String localCharset = "UTF-8";

        public Handler() {
        }

        public Handler(int bufferSize) {
            this(bufferSize, null);
        }

        public Handler(String localCharset) {
            this(-1, localCharset);
        }

        public Handler(int bufferSize, String localCharset) {
            if (bufferSize > 0)
                this.bufferSize = bufferSize;
            if(localCharset != null)
                this.localCharset = localCharset;
        }

        public void handleAccept(SelectionKey key) throws IOException{
            SocketChannel sc = ((ServerSocketChannel) key.channel()).accept();
            sc.configureBlocking(false);
            sc.register(key.selector(), SelectionKey.OP_READ, ByteBuffer.allocate(bufferSize));
        }

        public void handleRead(SelectionKey key) throws IOException{
            //  获取channel
            SocketChannel sc = (SocketChannel) key.channel();
            //  获取Buffer并重置
            ByteBuffer buffer = (ByteBuffer) key.attachment();
            buffer.clear();
            //  无内容时关闭
            if (sc.read(buffer) == -1){
                sc.close();
            }else {
                //  改buffer 为读状态
                buffer.flip();
                //  将读到的值，按照handler 设定的localCharset的值编码后，保存到receivedString 对象中
                String receivedString = Charset.forName(localCharset).newDecoder().decode(buffer).toString();
                //  准备返回数据
                String sendString = "received String :" + receivedString;
                //  覆写到缓冲区
                buffer = ByteBuffer.wrap(sendString.getBytes(localCharset));
                //  把缓冲区数据返回给客户端
                sc.write(buffer);
                //  关闭
                sc.close();
            }
        }
    }
}

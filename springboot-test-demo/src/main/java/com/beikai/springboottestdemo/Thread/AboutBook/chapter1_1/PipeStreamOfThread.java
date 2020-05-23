package com.beikai.springboottestdemo.Thread.AboutBook.chapter1_1;

import lombok.SneakyThrows;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/5/23
 * Time: 7:18 下午
 * Description: 使用管道字节流 进行通信
 */
public class PipeStreamOfThread {

    public static void main(String[] args) throws IOException, InterruptedException {
        WriteData writeData = new WriteData();
        ReadData readData = new ReadData();

        PipedOutputStream pipedOutputStream = new PipedOutputStream();
        PipedInputStream pipedInputStream = new PipedInputStream();

        WriteThread writeThread = new WriteThread(writeData, pipedOutputStream);
        ReadThread readThread = new ReadThread(readData, pipedInputStream);

        // pipedOutputStream.connect(pipedInputStream);
        // 使两个流之间建立通信
        pipedInputStream.connect(pipedOutputStream);
        readThread.start();
        Thread.sleep(1000);
        writeThread.start();

        /**{
         * read :
         * write :
         * 123456789101112131415161718192021222324252627282930313233343536373839404142434445464748495051525354555657585960616263646566676869707172737475767778798081828384858687888990919293949596979899100101102103104105106107108109110111112113114115116117118119120121122123124125126127128129130131132133134135136137138139140141142143144145146147148149150151152153154155156157158159160161162163164165166167168169170171172173174175176177178179180181182183184185186187188189190191192193194195196197198199200201202203204205206207208209210211212213214215216217218219220221222223224225226227228229230231232233234235236237238239240241242243244245246247248249250251252253254255256257258259260261262263264265266267268269270271272273274275276277278279280281282283284285286287288289290291292293294295296297298299300
         * 123456789101112131415161718192021222324252627282930313233343536373839404142434445464748495051525354555657585960616263646566676869707172737475767778798081828384858687888990919293949596979899100101102103104105106107108109110111112113114115116117118119120121122123124125126127128129130131132133134135136137138139140141142143144145146147148149150151152153154155156157158159160161162163164165166167168169170171172173174175176177178179180181182183184185186187188189190191192193194195196197198199200201202203204205206207208209210211212213214215216217218219220221222223224225226227228229230231232233234235236237238239240241242243244245246247248249250251252253254255256257258259260261262263264265266267268269270271272273274275276277278279280281282283284285286287288289290291292293294295296297298299300
         *
         * 实现了两个线程之间的通信
         */

    }
}

// 创建读取线程
class WriteThread extends Thread {
    private WriteData writeData;
    private PipedOutputStream pipedOutputStream;

    public WriteThread(WriteData writeData, PipedOutputStream pipedOutputStream) {
        this.writeData = writeData;
        this.pipedOutputStream = pipedOutputStream;
    }

    @SneakyThrows
    @Override
    public void run() {
        writeData.writeService(pipedOutputStream);
    }
}


// 创建写入线程
class ReadThread extends Thread {
    private ReadData readData;
    private PipedInputStream pipedInputStream;

    public ReadThread(ReadData readData, PipedInputStream pipedInputStream) {
        this.readData = readData;
        this.pipedInputStream = pipedInputStream;
    }

    @SneakyThrows
    @Override
    public void run() {
        readData.readService(pipedInputStream);
    }
}


class WriteData {
    public void writeService(PipedOutputStream pipedOutputStream) throws IOException {
        System.out.println("write : ");
        for (int i = 0; i < 300; i++) {
            String date = "" + (i + 1);
            pipedOutputStream.write(date.getBytes());
            System.out.print(date);
        }
        System.out.println();
        pipedOutputStream.close();
    }
}

class ReadData {
    public void readService(PipedInputStream pipedInputStream) throws IOException {
        System.out.println("read : ");
        // 设置用来存储临时数据的对象
        byte[] bytes = new byte[20];
        // 获取读取的数据的长度
        int length = pipedInputStream.read(bytes);
        while (length != -1) {
            // 获取数据
            String s = new String(bytes, 0, length);
            System.out.print(s);
            // 重新读取数据 并重置长度
            length = pipedInputStream.read(bytes);
        }
        System.out.println();
        // 关闭流
        pipedInputStream.close();
    }
}
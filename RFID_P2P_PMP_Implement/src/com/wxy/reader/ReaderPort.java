package com.wxy.reader;

import com.wxy.file.GenerateTagData;
import com.wxy.pojo.Record;
import com.wxy.pojo.Tag;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * @author wxy
 * @date: 2022/3/4 8:57 上午
 * @ClassName: ReaderPort
 */
@SuppressWarnings("all")
public class ReaderPort implements Runnable {
    // 1. 获取算法处理过的数据
    private static ArrayList<Record> records = new ArrayList<>();
    private static ArrayList<Tag> missingTags = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        // 1. 生成标签数据
        // 删除已存内容
        new File("src/com/wxy/file/tags.txt").delete();
        new File("src/com/wxy/file/vistags.txt").delete();
        GenerateTagData generateTagData = new GenerateTagData();
        generateTagData.generateTag();
        Thread thread = new Thread(new ReaderPort());
        thread.start();
    }

    public void getData() throws IOException, ClassNotFoundException {
        ArrayList<Record> data = new ReadFile().readFile();
        for (Record record : data) {
            records.add(record);
        }
    }


    @Override
    public void run() {
        // 1. 获取算法后的数据
        try {
            new ReaderPort().sendMess();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void sendMess() throws IOException, ClassNotFoundException {
        // 1. 获取算法数据
        new ReaderPort().getData();
        // 2. 建立连接
        System.out.println("ReaderPort 等待建立连接");
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(39999);
            Socket socket = serverSocket.accept();
            ObjectOutputStream oos = null;
            ObjectInputStream ois = null;
            System.out.println("ReaderPort连接建立成功，准备发送信息");
            int i = 0;
            while (true && i < 20) {
                // 3. 发送Select指令
                System.out.println("发送 Select 指令 " + (i + 1));
                Record record = records.get(i);
                int p = record.getP();
                int l = record.getL();
                String s = record.getTag().getEPC().substring(p, p + l);
                oos = new ObjectOutputStream(socket.getOutputStream());
                oos.writeInt(p);
                oos.writeInt(l);
                oos.writeUTF(s);
                oos.flush();
                // 当收到标签的回复后，发送下一个
                ois = new ObjectInputStream(socket.getInputStream());
                String epc = "";
                if ((epc = ois.readUTF()) != null) {
                    if (!epc.equals("null")) {
                        System.out.println("收到标签EPC：" + epc);
                    } else {
                        System.out.println("标签：" + record.getTag().getName() + " 丢失");
                        missingTags.add(record.getTag());
                    }
                } else {
                    break;
                }
                // 等待一秒之后继续发送下一个
                Thread.sleep(1000);
                i++;
            }
            socket.shutdownOutput();
            oos.close();
            ois.close();
            socket.close();
            serverSocket.close();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        // 3. 显示丢失标签
        displayMisingTags();
    }

    public void displayMisingTags() {
        System.out.println("=========显示所有丢失的标签==========");
        if (missingTags.size() != 0) {
            for (Tag missingTag : missingTags) {
                System.out.println(missingTag.getName() + "\t--\t" + missingTag.getEPC());
            }
        } else {
            System.out.println("没有标签丢失");
        }

    }

}

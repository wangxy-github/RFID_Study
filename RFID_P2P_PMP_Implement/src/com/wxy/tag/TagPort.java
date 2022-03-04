package com.wxy.tag;

import com.wxy.file.GenerateTagData;
import com.wxy.pojo.Tag;
import sun.jvm.hotspot.utilities.ObjectReader;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

/**
 * @author wxy
 * @date: 2022/3/4 8:57 上午
 * @ClassName: TagPort
 */
@SuppressWarnings("all")
public class TagPort implements Runnable {

    private static ArrayList<Tag> list = new ArrayList<>();

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        new TagPort().getAllTags();
        Thread thread = new Thread(new TagPort());
        thread.start();
    }

    public void filter() throws IOException, InterruptedException, ClassNotFoundException {
        // 1. 建立连接
        Socket socket = new Socket(InetAddress.getLocalHost(), 39999);
        System.out.println("TagPort建立连接成功，准备接收和回复信息");
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;
        // 接收数据
        int i = 0;
        while (true && i < 20) {
            ois = new ObjectInputStream(socket.getInputStream());
            int p;
            if ((p = ois.readInt()) == -1) break;
            int l = ois.readInt();
            String s = ois.readUTF();
            System.out.print("开始位置为： " + p + " 长度为： " + l + "   ");
            // 进行过滤效果找到唯一的标签
            Tag oneTag = findOnlyOneTag(p, l, s);
            oos = new ObjectOutputStream(socket.getOutputStream());
            if (oneTag != null && oneTag.isExist()) {
                System.out.println(oneTag.getName() + "被成功识别");
                // 给reader发送epc
                oos.writeUTF(oneTag.getEPC());
                oos.flush();
            } else {
                System.out.println(" - 没有回应 - ");
                oos.writeUTF("null");
                oos.flush();
            }
            Thread.sleep(100);
            i++;
        }
        socket.shutdownOutput();
        ois.close();
        oos.close();
        socket.close();
    }

    public void getAllTags() throws IOException, ClassNotFoundException {
        // 1. 得到所有标签
        ObjectInputStream ois =
                new ObjectInputStream(new FileInputStream("src/com/wxy/file/tags.txt"));
        list = (ArrayList<Tag>) ois.readObject();
        // 2. 假设3个标签丢失
        int x1 = (int) (Math.random() * 20);
        int x2 = (int) (Math.random() * 20);
        int x3 = (int) (Math.random() * 20);
        list.get(x1).setExist(false);
        list.get(x2).setExist(false);
        list.get(x3).setExist(false);
        System.out.println(x1 + " " + x2 + " " + x3 + " ");
    }

    public Tag findOnlyOneTag(int p, int l, String s) throws IOException, ClassNotFoundException {
        // 进行筛选
        for (Tag tag : list) {
            if (tag.getEPC().substring(p, p+l).equals(s)) {
                // 3. 返回唯一的标签
                return tag;
            }
        }
        return null;
    }

    @Override
    public void run() {
        try {
            new TagPort().filter();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

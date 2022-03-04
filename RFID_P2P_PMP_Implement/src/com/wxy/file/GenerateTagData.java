package com.wxy.file;

import com.wxy.pojo.Tag;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;

/**
 * @author wxy
 * @date: 2022/3/3 5:47 下午
 * @ClassName: GenerateTagData
 */
@SuppressWarnings("all")
public class GenerateTagData {
    private static ArrayList<Tag> list = new ArrayList<>();
    // 生成EPC
    public static String generateTagEPC() {
        // 随机生成96位是字符串
        StringBuffer EPC = new StringBuffer();
        for (int i = 0; i < 96; i++) {
            int x = (int) (Math.random() * 2);
            EPC.append(x + "");
        }
        return EPC.toString();
    }

    @Test
    // 生成20个Tag信息
    public void generateTag() throws IOException {
        String filePath = "src/com/wxy/file/tags.txt";
        String visFilePath = "src/com/wxy/file/vistags.txt";
        ObjectOutputStream oos =
                new ObjectOutputStream(new FileOutputStream(filePath, true));
        FileWriter fw = new FileWriter(visFilePath, true);
        for (int i = 0; i < 20; i++) {
            // 生成Tag信息
            String name = "标签" + (i + 1);
            String EPC = generateTagEPC();
            list.add(new Tag(name, EPC));
            fw.write(name + "\t--\t" + EPC + "\n");
            fw.flush();
        }
        // 将Tag保存到文件中
        oos.writeObject(list);
        oos.close();
        fw.close();
        System.out.println(list.size());
    }

    public ArrayList<Tag> getList() {
        return list;
    }
}

package com.wxy.reader;

import com.wxy.idea.P2P;
import com.wxy.idea.P2PFirst;
import com.wxy.idea.P2PTwo;
import com.wxy.pojo.Record;
import com.wxy.pojo.Tag;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

/**
 * @author wxy
 * @date: 2022/3/3 6:19 下午
 * @ClassName: ReadFile
 */
@SuppressWarnings("all")
public class ReadFile {
    // 读取tags.txt中Tag的数据
    @Test
    public ArrayList<Record> readFile() throws IOException, ClassNotFoundException {
        String filePath = "src/com/wxy/file/tags.txt";
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath));
        ArrayList<Tag> list = (ArrayList<Tag>) ois.readObject();
        // 运用算法，获取所有记录
        ArrayList<Record> records = AlogthismOneGetRecode(list);
        return records;
    }

    // 经过算法过后生成的数据
    public ArrayList<Record> AlogthismOneGetRecode(ArrayList<Tag> tags) {
        P2P p2p = new P2PFirst();
        P2P p2pp = new P2PTwo();
        // 算法1
//        P2PFirst p2p = new P2PFirst();
//        ArrayList<Record> records = p2p.findOneTag(tags);
        // 算法2
        ArrayList<Record> records = p2pp.findOneTag(tags);
        // 显示 records
        System.out.println("==============经过算法处理后的数据显示" + records.size() + "条===============");
        System.out.println("标签名字 \t\t\t\t\t\t\t\t\tEPC\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  -- p l");
        for (Record record : records) {
            Tag tag = record.getTag();
            int p = record.getP();
            int l = record.getL();
            System.out.println(tag.getName() + " -- " + tag.getEPC() + " -- " + p + " " + l);
        }
        return records;
    }

}

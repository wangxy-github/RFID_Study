package com.wxy.idea;

import com.wxy.pojo.Record;
import com.wxy.pojo.Tag;

import java.util.ArrayList;

/**
 * @author wxy
 * @date: 2022/3/4 2:15 下午
 * @ClassName: P2PTwo
 */
@SuppressWarnings("all")
public class P2PTwo implements P2P {
    ArrayList<Record> records = new ArrayList<>();
    ArrayList<Tag> S = new ArrayList<>();
    int l = 0; // 长度
    int z = 0; // 轮次

    // 设置参数 长度l 和 轮次z
    public void setLAndZ(int l, int z) {
        this.l = l;
        this.z = z;
    }

    public ArrayList<Record> findOneTag(ArrayList<Tag> list) {
        for (int i = 0; i < list.size(); i++) {
            setLAndZ(4, 20);// 暂时确保 100% 能够找到
            ArrayList<Tag> N = new ArrayList<>();
            for (Tag tag : list) {
                N.add(tag);
            }
            int k = 1;
            int p = 0;
            // 选择任意一个标签
            Tag chooseTag = chooseTag(N);
            while (k <= z) {
                int indicator = 1;
                for (int j = 0; j < N.size(); j++) {
                    if (j == N.indexOf(chooseTag))
                        continue;
                    if (chooseTag.getEPC().substring(p, p + l).equals
                            (N.get(j).getEPC().substring(p, p + l))) {
                        indicator = 0;
                    }
                }
                if (indicator == 1) {
                    recordMess(chooseTag, p, l);
                    S.add(chooseTag);
                    break;
                } else {
                    p += l;
                    k++;
                }
            }
        }
        return records;
    }

    // 随机从 N - S 中选择一个标签
    public Tag chooseTag(ArrayList<Tag> list) {
        ArrayList<Tag> tags = new ArrayList<>();
        for (Tag tag : list) {
            tags.add(tag);
        }
        tags.removeAll(S);
        // 随机选择一个Tag返回
        int random = (int) (Math.random() * tags.size());
        return tags.get(random);
    }
    // 记录信息
    public void recordMess(Tag tag, int p, int l) {
        records.add(new Record(tag, p, l));
    }

}

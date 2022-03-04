package com.wxy.idea;

import com.wxy.pojo.Record;
import com.wxy.pojo.Tag;

import java.util.ArrayList;

/**
 * @author wxy
 * @date: 2022/3/3 7:56 下午
 * @ClassName: P2PFirst
 */
@SuppressWarnings("all")
public class P2PFirst implements P2P {
    ArrayList<Record> records = new ArrayList<>();
//    public ArrayList<Record> findOneTag1(ArrayList<Tag> list) {
//        // 1. 初始定义
//        ArrayList<Tag> SS = new ArrayList<>();
//        ArrayList<Tag> N = new ArrayList<>();
//        for (Tag tag : list) {
//            N.add(tag);
//        }
//        int l = 1;
//        int j = 0;
//        while (j < list.size()) {
//            while (l <= 96) {
//                int p = 0;
//                while (p <= 96 - l) {
//                    // 选择一个标签，与其他标签进行比较
//                    int indicator = 1;
//                    Tag tagx = chooseTag1(N, SS);
//                    if (tagx == null)
//                        return records;
//                    for (Tag tag : N) {
//                        if (tag.equals(tagx)) {
//                            continue;
//                        }
//                        if (tag.getEPC().substring(p, p + l).equals(tagx.getEPC().substring(p, p + l))) {
//                            indicator = 0;
//                            p ++;
//                            break;
//                        }
//                    }
//                    // 此位置确实可以唯一确定tagx，记录保存
//                    if (indicator == 1) {
//                        recordMess(tagx, p, l);
//                        SS.add(tagx);
//                        j ++;
//                        p ++;
//                    }
//                }
//                l ++;
//            }
//        }
//        return records;
//    }

    public ArrayList<Record> findOneTag(ArrayList<Tag> list) {
        // 1. 初始定义
        ArrayList<Tag> SS = new ArrayList<>();
        ArrayList<Tag> N = new ArrayList<>();
        int l = 1;
        int j = 0;
        while (j < list.size()) {
            while (l <= 96) {
                int p = 0;
                while (p <= 96 - l) {
                    for (Tag tag : list) {
                        N.add(tag);
                    }
                    ArrayList<Tag> S = new ArrayList<>();
                    int indicator = 1;
                    // 从 list - S - SS 中 随机选择一个tagx
                    Tag tagx = chooseTag(list, SS, S);
                    if (tagx == null) return records;
                    // 比较
                    for (Tag tag : N) {
                        if (tag.equals(tagx)) {
                            continue;
                        }
                        if (tag.getEPC().substring(p, p + l).equals
                                (tagx.getEPC().substring(p, p + l))) {
                            S.add(tag);
                            indicator = 0;
                            p++;
                            break;
                        }
                    }
                    if (indicator == 1) {
                        recordMess(tagx, p, l);
                        SS.add(tagx);
                        j++;
                        break;
                    } else {
                        p++;
                    }
                }
                l++;
            }
            j++;
        }
        return records;
    }


    public Tag chooseTag(ArrayList<Tag> list, ArrayList<Tag> SS, ArrayList<Tag> S) {
        // 获取一个非公共集合
        ArrayList<Tag> tags = new ArrayList<>();
        for (Tag tag : list) {
            tags.add(tag);
        }
        tags.removeAll(SS);
        tags.removeAll(S);
        // 随机选择一个Tag返回
        int size = tags.size();
        if (size == 0) return null;
        int random = (int) (Math.random() * size);
        return tags.get(random);
    }

//    public Tag chooseTag1(ArrayList<Tag> list, ArrayList<Tag> SS) {
//        // 获取一个非公共集合
//        ArrayList<Tag> tags = new ArrayList<>();
//        for (Tag tag : list) {
//            tags.add(tag);
//        }
//        tags.removeAll(SS);
//        // 随机选择一个Tag返回
//        int size = tags.size();
//        if (size == 0) {
//            return null;
//        }
//        int random = (int) (Math.random() * size);
//        return tags.get(random);
//
//    }


    public void recordMess(Tag tag, int p, int l) {
        records.add(new Record(tag, p, l));
    }
}

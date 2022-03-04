package com.wxy.pojo;

/**
 * @author wxy
 * @date: 2022/3/3 8:28 下午
 * @ClassName: Record
 */
public class Record {
    private Tag tag;
    private int p;
    private int l;

    public Record() {
    }

    public Record(Tag tag, int p, int l) {
        this.tag = tag;
        this.p = p;
        this.l = l;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public int getP() {
        return p;
    }

    public void setP(int p) {
        this.p = p;
    }

    public int getL() {
        return l;
    }

    public void setL(int l) {
        this.l = l;
    }
}

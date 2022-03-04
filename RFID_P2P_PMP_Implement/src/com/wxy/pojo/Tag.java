package com.wxy.pojo;

import java.io.Serializable;

/**
 * @author wxy
 * @date: 2022/3/3 5:18 下午
 * @ClassName: Tag
 */
public class Tag implements Serializable {
    private String name; // 标签的名字
    private String EPC; // 标签的EPC码，96位组成
    private boolean isExist = true; // 标签是否存在
    private boolean SL = false; // 标签的开关，true表示打开，false表示关闭，默认为关闭状态

    public Tag(String name, String EPC) {
        this.name = name;
        this.EPC = EPC;
    }

    public boolean isExist() {
        return isExist;
    }

    public void setExist(boolean exist) {
        isExist = exist;
    }

    public Tag(String name, String EPC, boolean isExist) {
        this.name = name;
        this.EPC = EPC;
        this.isExist = isExist;
    }

    public Tag() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEPC() {
        return EPC;
    }

    public void setEPC(String EPC) {
        this.EPC = EPC;
    }

    public boolean isSL() {
        return SL;
    }

    public void setSL(boolean SL) {
        this.SL = SL;
    }

}

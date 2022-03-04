package com.wxy.idea;

import com.wxy.pojo.Record;
import com.wxy.pojo.Tag;

import java.util.ArrayList;

/**
 * @author wxy
 * @date: 2022/3/4 2:17 下午
 * @ClassName: P2P
 */
public interface P2P {
    public ArrayList<Record> findOneTag(ArrayList<Tag> list);
}

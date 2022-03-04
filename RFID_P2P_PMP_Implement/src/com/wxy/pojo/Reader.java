package com.wxy.pojo;

/**
 * @author wxy
 * @date: 2022/3/3 5:25 下午
 * @ClassName: Reader
 */
public class Reader {
    private String Select;
    private String Query;
    private String QueryRep;
    private String ACK;
    private String ACKandRN16;

    public String getSelect() {
        return Select;
    }

    public void setSelect(String select) {
        Select = select;
    }

    public String getQuery() {
        return Query;
    }

    public void setQuery(String query) {
        Query = query;
    }

    public String getQueryRep() {
        return QueryRep;
    }

    public void setQueryRep(String queryRep) {
        QueryRep = queryRep;
    }

    public String getACK() {
        return ACK;
    }

    public void setACK(String ACK) {
        this.ACK = ACK;
    }

    public String getACKandRN16() {
        return ACKandRN16;
    }

    public void setACKandRN16(String ACKandRN16) {
        this.ACKandRN16 = ACKandRN16;
    }

    public Reader() {
    }

    public Reader(String select, String query, String queryRep, String ACK, String ACKandRN16) {
        Select = select;
        Query = query;
        QueryRep = queryRep;
        this.ACK = ACK;
        this.ACKandRN16 = ACKandRN16;
    }
}

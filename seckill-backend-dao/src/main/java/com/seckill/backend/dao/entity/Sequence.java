package com.seckill.backend.dao.entity;

/**
 * @Author: Bojun Ji
 * @Date: Created in 2018-09-17 15:35
 * @Description:
 */
public class Sequence {
    private String tabeleName;
    private int id;

    public String getTabeleName() {
        return tabeleName;
    }

    public void setTabeleName(String tabeleName) {
        this.tabeleName = tabeleName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

package com.seckill.backend.dao.entity;

/**
 * @Author: Bojun Ji
 * @Date: Created in 2018-09-17 15:35
 * @Description:
 */
public class Sequence {
    private String tabeleName;
    private int sequenceId;

    public String getTabeleName() {
        return tabeleName;
    }

    public void setTabeleName(String tabeleName) {
        this.tabeleName = tabeleName;
    }


    public int getSequenceId() {
        return sequenceId;
    }

    public void setSequenceId(int sequenceId) {
        this.sequenceId = sequenceId;
    }
}

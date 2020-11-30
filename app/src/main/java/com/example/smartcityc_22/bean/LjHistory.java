package com.example.smartcityc_22.bean;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/29 at 16:04
 */
public class LjHistory  {
    private int inidex;
    private String rq,lx;
     private int je;

    public LjHistory(int inidex, String rq, String lx, int je) {
        this.inidex = inidex;
        this.rq = rq;
        this.lx = lx;
        this.je = je;
    }

    public int getInidex() {
        return inidex;
    }

    public void setInidex(int inidex) {
        this.inidex = inidex;
    }

    public String getRq() {
        return rq;
    }

    public void setRq(String rq) {
        this.rq = rq;
    }

    public String getLx() {
        return lx;
    }

    public void setLx(String lx) {
        this.lx = lx;
    }

    public int getJe() {
        return je;
    }

    public void setJe(int je) {
        this.je = je;
    }
}

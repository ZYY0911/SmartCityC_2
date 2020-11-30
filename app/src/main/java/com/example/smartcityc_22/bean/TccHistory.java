package com.example.smartcityc_22.bean;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/29 at 9:46
 */
public class TccHistory {

    /**
     * id : 5
     * carNum : 琼S20001
     * charge : 6
     * inTime : 2020-10-03 13:03:46
     * outTime : 2020-10-03 16:03:50
     * parkingid : 2
     */

    private int id;
    private String carNum;
    private String charge;
    private String inTime;
    private String outTime;
    private String parkingid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCarNum() {
        return carNum;
    }

    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public String getInTime() {
        return inTime;
    }

    public void setInTime(String inTime) {
        this.inTime = inTime;
    }

    public String getOutTime() {
        return outTime;
    }

    public void setOutTime(String outTime) {
        this.outTime = outTime;
    }

    public String getParkingid() {
        return parkingid;
    }

    public void setParkingid(String parkingid) {
        this.parkingid = parkingid;
    }
}

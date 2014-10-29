package com.bzh.gt.vo;

/**
 * 数据导入的Excel的头部信息
 * Created by biezhihua on 14-9-28.
 */
public enum ExcelHeader {
    SERIAL(0),  // 序号
    APARTMENT(1), // 公寓
    DORMITORY(2), // 宿舍
    SEX(3), // 公寓类型(男//女)
    RANK(4), // 公寓等级(高等/普通)
    CLASS(5), // 班级
    BED_NO1(6, 1),// 床位1
    BED_NO2(7, 2),// 床位2
    BED_NO3(8, 3),// 床位3
    BED_NO4(9, 4),// 床位4
    BED_NO5(10, 5),// 床位5
    BED_NO6(11, 6),// 床位6
    BED_NO7(12, 7),// 床位7
    BED_NO8(13, 8),// 床位8
    DORMITORY_LEADER_NAME(14), // 寝室长
    DORMITORY_LEADER_PHONE(15),// 寝室长电话
    NOTE(16); // 备注

    private ExcelHeader(int index, int bedNo) {
        this.bedNo = bedNo;
        this.index = index;
    }

    private ExcelHeader(int index) {
        this.index = index;
    }

    private int bedNo;
    private int index;


    public int getBedNo() {
        return bedNo;
    }

    public void setBedNo(int bedNo) {
        this.bedNo = bedNo;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public static void main(String[] args) {
        for (ExcelHeader header : ExcelHeader.values()) {
            System.out.println(header + " ---");
        }
    }
}

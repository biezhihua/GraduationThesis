package com.bzh.gt.utils;

/**
 * Created by Administrator on 2014/10/7.
 */
public class RegExpUtil {

    private static final String REG_EXP_CLASS = "[a-bA-B]\\d{4}";
    private static final String REG_EXP_CLASS_NAME = "[a-bA-B]\\d{4}[\\u4e00-\\u9fa5]{2,}";
    private static final String REG_EXP_APARTMENT_DOR_BED = "\\d{2}#\\d{3}-\\d";
    /**
     * 是否匹配班级信息
     *
     * @param className
     * @return
     */
    public static boolean isMatchesClass(String className) {
        return className.matches(REG_EXP_CLASS);
    }

    /**
     * 是否匹配特殊的学生信息，例如“A1121别志华”
     *
     * @param studentName
     * @return
     */
    public static boolean isMatchesSpecialStudentName(String studentName) {
        return studentName.matches(REG_EXP_CLASS_NAME);
    }

    /**
     * 是否匹配 "31#504-1"这样的数字
     * @param apartDorBed
     * @return
     */
    public static boolean isMatchesSpecialApartDorBed(String apartDorBed) {
        return apartDorBed.matches(REG_EXP_APARTMENT_DOR_BED);
    }

}

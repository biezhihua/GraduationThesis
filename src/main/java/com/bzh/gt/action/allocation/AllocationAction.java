package com.bzh.gt.action.allocation;

import com.bzh.gt.action.base.BaseAction;
import com.bzh.gt.bean.*;
import com.bzh.gt.utils.QueryHelper;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.*;

/**
 * Created by Administrator on 2014/10/22.
 */
@Controller
@Scope("prototype")
public class AllocationAction extends BaseAction<Clasz> {

    private Long apartmentId;   //
    private Long dormitoryId;
    private Long bedId;
    private Long claszId;
    private String stuSno;
    private String stuName;
    private Object data; // json数据

    /**
     * 获取待分配班级列表
     */
    public String infoEntering() {
        List<Clasz> claszList = claszService.getAllByIsReservoir();
        ActionContext.getContext().put("claszList", claszList);
        return "infoEntering";
    }

    /**
     * 添加待分配班级页面
     */
    public String addUI() {
        return "saveUI";
    }

    /**
     * 为新班级设置待分配标记
     * @return
     */
    public String add() {
        model.setIsReservoir(true);
        claszService.save(model);
        return "toInfoEntering";
    }

    /**
     * 删除待分配标记
     * @return
     */
    public String delete() {
        //claszService.delete(model.getId());
        Clasz clasz = claszService.getById(model.getId());
        clasz.setIsReservoir(false);
        claszService.update(clasz);
        return "toInfoEntering";
    }

    /**
     * 编辑页面
     */
    public String editUI() {
        ActionContext.getContext().getValueStack().push(claszService.getById(model.getId()));
        return "saveUI";
    }

    public String edit() {
        Clasz clasz = claszService.getById(model.getId());
        clasz.setManReservoir(model.getManReservoir());
        clasz.setWomanReservoir(model.getWomanReservoir());
        claszService.update(clasz);
        return "toInfoEntering";
    }


    /**
     * 宿舍预分配
     */
    public String preDistribution() {
        // 计算分配男生
        cacluatePreDistribution(true);
        // 计算分配女生
        cacluatePreDistribution(false);
        return "toInfoEntering";
    }

    /**
     * 手动分配
     */
    public String manualUI() {
        // 准备待分配寝室公寓数据
        QueryHelper queryHelper = new QueryHelper(Dormitory.class, "d")
                .addWhereCondition("d.isAllocation=?", true);
        List<Dormitory> dormitoryList = dormitoryService.getPageBean(pageNum, Integer.MAX_VALUE, queryHelper).getRecords();
        Set<Apartment> apartmentSet = new HashSet<Apartment>();
        for (Dormitory dormitory : dormitoryList) {
            apartmentSet.add(dormitory.getApartment());
        }
        List<Apartment> apartmentList = new ArrayList<Apartment>();
        for (Apartment apartment : apartmentSet) {
            Apartment copy = new Apartment();
            copy.setId(apartment.getId());
            copy.setName(apartment.getName() + "-" + apartment.getRank());
            apartmentList.add(copy);
        }

        ActionContext.getContext().put("apartmentList", apartmentList);

        // 拿到待分配班级集合，按照班级名称排序
        queryHelper = new QueryHelper(Clasz.class, "c");
        queryHelper.addWhereCondition("c.isReservoir=?", true);
        queryHelper.addOrderByProperty("c.name", true);
        List<Clasz> claszList = claszService.getPageBean(pageNum, Integer.MAX_VALUE, queryHelper).getRecords();
        ActionContext.getContext().put("claszList", claszList);
        // 准备待分配班级数据
        return "manualUI";
    }

    /**
     * 手动分配
     * @return
     */
    public String manual() {
        Map<String, String> result = new HashMap<String, String>();
        // 创建学生
        Student student = new Student();
        student.setName(stuName);
        student.setSno(stuSno);
        studentService.save(student);
        Bed bed = bedService.getById(bedId);
        bed.setStudent(student);
        bedService.update(bed);
        student.setBed(bed);
        studentService.update(student);
        // 设置完关联关系代表分配成功
        result.put("status", "success");
        result.put("result", "恭喜你！" + student.getName() + "你已经被分配到" + bed.getDormitory().getApartment().getName() + "公寓" + bed.getDormitory().getName() + "寝室" + bed.getBedNO() + "号床铺");
        data = result;
        return "json";
    }

    /**
     * 自动分配界面
     * @return
     */
    public String checkInUI() {
        // 准备待分配寝室公寓数据
        QueryHelper queryHelper = new QueryHelper(Dormitory.class, "d")
                .addWhereCondition("d.isAllocation=?", true);
        List<Dormitory> dormitoryList = dormitoryService.getPageBean(pageNum, Integer.MAX_VALUE, queryHelper).getRecords();
        Set<Apartment> apartmentSet = new HashSet<Apartment>();
        for (Dormitory dormitory : dormitoryList) {
            apartmentSet.add(dormitory.getApartment());
        }

        List<Apartment> apartmentList = new ArrayList<Apartment>();
        for (Apartment apartment : apartmentSet) {
            Apartment copy = new Apartment();
            copy.setId(apartment.getId());
            copy.setName(apartment.getName() + "-" + apartment.getRank());
            apartmentList.add(copy);
        }

        ActionContext.getContext().put("apartmentList", apartmentList);

        // 拿到待分配班级集合，按照班级名称排序
        queryHelper = new QueryHelper(Clasz.class, "c");
        queryHelper.addWhereCondition("c.isReservoir=?", true);
        queryHelper.addOrderByProperty("c.name", true);
        List<Clasz> claszList = claszService.getPageBean(pageNum, Integer.MAX_VALUE, queryHelper).getRecords();
        ActionContext.getContext().put("claszList", claszList);
        // 准备待分配班级数据
        return "checkInUI";
    }

    /**
     * 自动分配
     * @return
     */
    public String checkIn() {
        Map<String, String> result = new HashMap<String, String>();

        // 创建学生
        Student student = new Student();
        student.setName(stuName);
        student.setSno(stuSno);
        studentService.save(student);

        // 根据所选公寓和所选班级，按照寝室号排序，拿到该班级预分配的寝室
        Clasz clasz = claszService.getById(claszId);
        student.setClasz(clasz);
        studentService.update(student);

        QueryHelper queryHelper = new QueryHelper(Dormitory.class, "d")
                .addWhereCondition("d.apartment.id=?", apartmentId)//
                .addWhereCondition("d.isAllocation=?", true)
                .addOrderByProperty("d.level", false)//
                .addOrderByProperty("d.name", true);

        Apartment apartment = apartmentService.getById(apartmentId);
        if ("高等公寓".equals(apartment.getRank())) {
            // 获取寝室
            List<Dormitory> dormitoryList = dormitoryService.getPageBean(pageNum, Integer.MAX_VALUE, queryHelper).getRecords();

            // 遍历寝室，看是否有已经属于此班级的，有则在该寝室中安排同学，没有查看公寓是否充裕，建立新关系
            if (isExistDormitory(dormitoryList, clasz)) {
                Dormitory dormitory = existDormitory(dormitoryList, clasz);
                for (Bed bed : dormitory.getBeds()) {
                    if (bed.getStudent() == null) {
                        bed.setStudent(student);
                        bedService.update(bed);
                        student.setBed(bed);
                        studentService.update(student);
                        if ("男生公寓".equals(apartment.getSex())) {
                            clasz.setManReservoir(clasz.getManReservoir() - 1);
                        } else {
                            clasz.setWomanReservoir(clasz.getWomanReservoir() - 1);
                        }
                        claszService.update(clasz);
                        // 计算分配男生
                        cacluatePreDistribution(true);
                        // 计算分配女生
                        cacluatePreDistribution(false);

                        // 设置完关联关系代表分配成功
                        result.put("status", "success");
                        result.put("result", "恭喜你！" + student.getName() + "你已经被分配到" + bed.getDormitory().getApartment().getName() + "公寓" + bed.getDormitory().getName() + "寝室" + bed.getBedNO() + "号床铺");
                        data = result;
                        return "json";
                    }
                }
            } else {
                for (Dormitory dormitory : dormitoryList) {
                    if (dormitory.getClasz() == null) {
                        dormitory.setClasz(clasz);
                        dormitoryService.update(dormitory);
                        for (Bed bed : dormitory.getBeds()) {
                            if (bed.getStudent() == null) {
                                bed.setStudent(student);
                                bedService.update(bed);
                                student.setBed(bed);
                                studentService.update(student);
                                if ("男生公寓".equals(apartment.getSex())) {
                                    clasz.setManReservoir(clasz.getManReservoir() - 1);
                                } else {
                                    clasz.setWomanReservoir(clasz.getWomanReservoir() - 1);
                                }
                                claszService.update(clasz);
                                // 计算分配男生
                                cacluatePreDistribution(true);
                                // 计算分配女生
                                cacluatePreDistribution(false);

                                // 设置完关联关系代表分配成功
                                result.put("status", "success");
                                result.put("result", "恭喜你！" + student.getName() + "你已经被分配到" + bed.getDormitory().getApartment().getName() + "公寓" + bed.getDormitory().getName() + "寝室" + bed.getBedNO() + "号床铺");
                                data = result;
                                return "json";
                            }
                        }
                    }
                }
            }

        } else {
            queryHelper.addWhereCondition("d.clasz.id=?", claszId);
            List<Dormitory> dormitoryList = dormitoryService.getPageBean(pageNum, Integer.MAX_VALUE, queryHelper).getRecords();
            // 遍历寝室集合，按床铺依次分配，没有则下一个寝室
            for (Dormitory dormitory : dormitoryList) {
                Set<Bed> bedSet = dormitory.getBeds();
                for (Bed bed : bedSet) {
                    if (bed.getStudent() == null) {
                        bed.setStudent(student);
                        bedService.update(bed);
                        student.setBed(bed);
                        studentService.update(student);
                        // 设置完关联关系代表分配成功
                        result.put("status", "success");
                        result.put("result", "恭喜你！" + student.getName() + "你已经被分配到" + bed.getDormitory().getApartment().getName() + "公寓" + bed.getDormitory().getName() + "寝室" + bed.getBedNO() + "号床铺");
                        data = result;
                        return "json";
                    }
                }
            }
        }
        result.put("status", "error");
        return "json";
    }

    private Dormitory existDormitory(List<Dormitory> dormitoryList, Clasz clasz) {
        for (Dormitory dormitory : dormitoryList) {
            if (dormitory.getClasz() != null) {
                if (dormitory.getClasz().getName().equals(clasz.getName())) {
                    return dormitory;
                }
            }
        }
        return null;
    }

    /**
     * 寝室列表中是否有寝室属于该班级
     *
     * @param dormitoryList
     * @param clasz
     * @return
     */
    private boolean isExistDormitory(List<Dormitory> dormitoryList, Clasz clasz) {
        for (Dormitory dormitory : dormitoryList) {
            if (dormitory.getClasz() != null) {
                if (dormitory.getClasz().getName().equals(clasz.getName())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 通过班级待分配的人数，计算得到预分配的房间，进行预分配
     * @param sex true男生、false女生
     */
    public boolean cacluatePreDistribution(boolean sex) {
        List<Clasz> claszList = null;
        List<Dormitory> reservoirDormitorys = null;

        // 拿到待分配宿舍的集合，按照普通公寓，男女，楼栋、楼层、房间号排序
        QueryHelper queryHelper = new QueryHelper(Dormitory.class, "d");
        queryHelper.addWhereCondition("d.apartment.rank=?", "普通公寓")//
                .addWhereCondition("d.apartment.sex=?", (sex == true ? "男生公寓" : "女生公寓"))//
                .addWhereCondition("d.isAllocation=?", true)
                .addOrderByProperty("d.apartment.name", true)//
                .addOrderByProperty("d.level", false)//
                .addOrderByProperty("d.name", true);//
        reservoirDormitorys = dormitoryService.getPageBean(pageNum, Integer.MAX_VALUE, queryHelper).getRecords();

        // 拿到待分配班级集合，按照班级名称排序
        queryHelper = new QueryHelper(Clasz.class, "c");
        queryHelper.addWhereCondition("c.isReservoir=?", true);
        queryHelper.addOrderByProperty("c.name", true);
        claszList = claszService.getPageBean(pageNum, Integer.MAX_VALUE, queryHelper).getRecords();


        if (claszList == null || claszList.size() == 0 || reservoirDormitorys == null || reservoirDormitorys.size() == 0) {
            return false;
        }
        // 循环班级集合，以男生为例。男生/寝室床铺 = 所需房间，进行关联关系的设置
        // 对余数进行记忆，下一个班级待分配男生减去余数/ 床铺书
        // 都按6铺
        int mod = 0;
        for (int i = 0; i < claszList.size(); i++) {
            Clasz clasz = claszList.get(i);
            // 如果mod为0，代表在为该班级分配之前，没有需要已经分配到上个寝室的人(mod == 0 ? 0 : 6 - mod)
            if (sex) {
                if (clasz.getManReservoir() == null) {
                    break;
                }
            } else {
                if (clasz.getWomanReservoir() == null) {
                    break;
                }
            }
            int reservoir = (sex == true ? clasz.getManReservoir() : clasz.getWomanReservoir()) - (mod == 0 ? 0 : 6 - mod); // 待分配人数

            int divide = reservoir / 6; // 待分配人数减去，之前房间剩余的人
            // 如果遗留的余数大于3，那么代表这个寝室归本班级
            if (mod != 0) {
                if (6 - mod > 3) {
                    divide += 1; // 分配的房间+1
                }
            }

            mod = reservoir % 6; // mod代表，整除床铺后，多出来的人

            // 如果余数大于等于3，则房间数+1
            if (mod >= 3) {
                divide += 1;
            } else {
                // 如果后面没有班级了
                if (i + 1 == claszList.size()) {
                    divide += 1;
                }
            }
            // 根据divide所代表的房间数，给Clasz分配寝室
            for (int j = 0; j < divide; j++) {
                // 待分配房间没有了
                if (reservoirDormitorys.size() == 0) {
                    break;
                }
                Dormitory dormitory = reservoirDormitorys.get(0);
                dormitory.setClasz(clasz); //设置关联关系
                dormitoryService.update(dormitory);
                reservoirDormitorys.remove(0);
            }
        }
        return true;
    }


    /**
     * 根据公寓ID获取待分配寝室
     */
    public String getReservoirDormitory() {
        QueryHelper queryHelper = new QueryHelper(Dormitory.class, "d")
                .addWhereCondition("d.apartment.id=?", apartmentId)//
                .addWhereCondition("d.isAllocation=?", true)
                .addOrderByProperty("d.apartment.name", true)//
                .addOrderByProperty("d.level", false)//
                .addOrderByProperty("d.name", true);//
        List<Dormitory> dormitoryList = dormitoryService.getPageBean(pageNum, Integer.MAX_VALUE, queryHelper).getRecords();
        List<Dormitory> copyList = new ArrayList<Dormitory>();
        for (Dormitory dormitory : dormitoryList) {
            Dormitory copy = new Dormitory();
            copy.setId(dormitory.getId());
            copy.setName(dormitory.getName());
            copyList.add(copy);
        }
        data = copyList;
        return "json";
    }

    /**
     * 根据寝室ID获取空床铺
     */
    public String getEmptyBed() {
        List<Bed> bedList = bedService.getByDormitory(dormitoryId);
        List<Bed> copyList = new ArrayList<Bed>();
        for (Bed bed : bedList) {
            if (bed.getStudent() == null) {
                Bed copy = new Bed();
                copy.setId(bed.getId());
                copy.setBedNO(bed.getBedNO());
                copyList.add(copy);
            }
        }
        data = copyList;
        return "json";
    }

    public Long getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(Long apartmentId) {
        this.apartmentId = apartmentId;
    }

    public Long getClaszId() {
        return claszId;
    }

    public void setClaszId(Long claszId) {
        this.claszId = claszId;
    }

    public String getStuSno() {
        return stuSno;
    }

    public void setStuSno(String stuSno) {
        this.stuSno = stuSno;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Long getDormitoryId() {
        return dormitoryId;
    }

    public void setDormitoryId(Long dormitoryId) {
        this.dormitoryId = dormitoryId;
    }

    public Long getBedId() {
        return bedId;
    }

    public void setBedId(Long bedId) {
        this.bedId = bedId;
    }
}

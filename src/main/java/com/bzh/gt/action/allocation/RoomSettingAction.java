package com.bzh.gt.action.allocation;

import com.bzh.gt.action.base.BaseAction;
import com.bzh.gt.bean.Apartment;
import com.bzh.gt.bean.Bed;
import com.bzh.gt.bean.Dormitory;
import com.bzh.gt.utils.QueryHelper;
import com.bzh.gt.vo.PageBean;
import com.opensymphony.xwork2.ActionContext;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 待分配房间设置
 * Created by Administrator on 2014/10/22.
 */
@Controller
@Scope("prototype")
public class RoomSettingAction extends BaseAction<Dormitory> {

    static Logger logger = Logger.getLogger(RoomSettingAction.class);
    /**
     * 前台传递过来的公寓的ID，用于对公寓进行过滤，进而筛选出
     */
    private Long apartmentId;
    private Long[] dormitoryIds;
    /**
     * 选择公寓的楼层
     */
    private String dormitoryLevel;
    private Long dormitoryId;   //
    private Map<String,String> data;  // json数据

    /**
     * C(Create) R(Read) U(Update) D(Delete)
     * <p/>
     * 概要: 转向到列表页面
     */
    public String list() throws Exception {

        /**
         * 获取所有公寓的信息，存到上下文中
         * 在JSP的页面显示出一个Menu
         */
        List<Apartment> apartments = apartmentService.getAll();
        ActionContext.getContext().put("apartments", apartments);

        /**
         * 获取公寓的最高楼层
         * 在Menu中生成每栋公寓的每一层的链接
         */
        Integer topLevel = apartmentService.getTopLevel();
        ActionContext.getContext().put("topLevel", topLevel);

        /**
         * 对寝室信息进行过滤选择，由于公寓ID和楼层Level信息是同时传递过来的
         * 在此就只判断一个
         */
        if (apartmentId != null) {
            // 添加过滤条件
            QueryHelper queryHelper = new QueryHelper(Dormitory.class, "d");
            queryHelper.addWhereCondition("d.apartment.id=?", apartmentId);
            queryHelper.addWhereCondition("d.level=?", dormitoryLevel);
            queryHelper.addOrderByProperty("d.name", true);
            //
            PageBean pageBean = dormitoryService.getPageBean(pageNum, 100, queryHelper);

            //
            List<List<Dormitory>> records = new ArrayList<List<Dormitory>>();
            List<Dormitory> dormitories = new ArrayList<Dormitory>(6);

            // 获取结果中待分配的房间数量
            int index = 0;
            for (Dormitory dormitory : (List<Dormitory>)pageBean.getRecords()) {
                if (dormitory.getIsAllocation()) {
                    index++;
                }
            }
            // 初始化数组
            dormitoryIds = new Long[index];
            index = 0;

            for (int i = 1, len = pageBean.getRecords().size(); i <= len; i++) {
                Dormitory dormitory = (Dormitory) pageBean.getRecords().get(i-1);
                // 如果是被待分配的
                if (dormitory.getIsAllocation()) {
                    dormitoryIds[index++] = dormitory.getId();
                }
                // 每6个加入到一个集合中
                if (i % 6 == 0) {
                    dormitories.add(dormitory);
                    records.add(dormitories);
                    dormitories = new ArrayList<Dormitory>(6);
                } else {
                    dormitories.add(dormitory);
                }
            }
            if (dormitories.size() < 6) {
                records.add(dormitories);
            }

            // 压入到Struts的Map区域中
            ActionContext.getContext().put("records",records);

            // 把过滤条件(公寓ID，楼层Level)保存到Session中
            ActionContext.getContext().put("apartmentId", apartmentId);
            ActionContext.getContext().put("dormitoryLevel", dormitoryLevel);
        }
        return "list";
    }


    /**
     * 待分配房间设置，更新标记
     * @return
     * @throws Exception
     */
    public String setDormitorys() throws  Exception {

        if (dormitoryIds == null) {
            dormitoryIds = new Long[0];
        }

        List<Dormitory> dormitoryList = dormitoryService.getByApartmentIdAndFloor(apartmentId,Integer.valueOf(dormitoryLevel));
        // 设置待分配标记
        for (Dormitory dormitory : dormitoryList) {
            //
            if (isContain(dormitory, dormitoryIds)) {
               dormitory.setIsAllocation(true);
                // 清除关联关系
               dormitory.setClasz(null);
               for (Bed bed : dormitory.getBeds()) {
                   bed.setStudent(null);
                   bedService.update(bed);
               }
            } else {
                dormitory.setIsAllocation(false);
            }
            dormitoryService.update(dormitory);
        }
        data = new HashMap<String, String>();
        data.put("status","success");
        return "json";
    }

    private boolean isContain(Dormitory dormitory, Long[] dormitoryIds) {
        for (int i = 0; i < dormitoryIds.length; i++) {
            // 使用==号时，要警惕
            if (dormitory.getId().equals(dormitoryIds[i])) {
                return true;
            }
        }
        return false;
    }


    public Long getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(Long apartmentId) {
        this.apartmentId = apartmentId;
    }

    public String getDormitoryLevel() {
        return dormitoryLevel;
    }

    public void setDormitoryLevel(String dormitoryLevel) {
        this.dormitoryLevel = dormitoryLevel;
    }

    public Long getDormitoryId() {
        return dormitoryId;
    }

    public void setDormitoryId(Long dormitoryId) {
        this.dormitoryId = dormitoryId;
    }

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }

    public Long[] getDormitoryIds() {
        return dormitoryIds;
    }

    public void setDormitoryIds(Long[] dormitoryIds) {
        this.dormitoryIds = dormitoryIds;
    }
}

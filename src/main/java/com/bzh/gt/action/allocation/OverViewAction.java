package com.bzh.gt.action.allocation;

import com.bzh.gt.action.base.BaseAction;
import com.bzh.gt.bean.Dormitory;
import com.bzh.gt.utils.QueryHelper;
import com.bzh.gt.vo.PageBean;
import com.opensymphony.xwork2.ActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2014/10/23.
 */

@Controller
@Scope("prototype")
public class OverViewAction extends BaseAction<Dormitory> {

    public String list() {
        // 添加过滤条件
        QueryHelper queryHelper = new QueryHelper(Dormitory.class, "d");
        queryHelper.addWhereCondition("d.isAllocation=?", true)
                .addOrderByProperty("d.apartment.name",true)
                .addOrderByProperty("d.level", false)//
                .addOrderByProperty("d.name", true);//
        //
        PageBean pageBean = dormitoryService.getPageBean(pageNum, 1000, queryHelper);

        //
        List<List<Dormitory>> records = new ArrayList<List<Dormitory>>();
        List<Dormitory> dormitories = new ArrayList<Dormitory>(6);

        // 初始化数组

        for (int i = 1, len = pageBean.getRecords().size(); i <= len; i++) {
            Dormitory dormitory = (Dormitory) pageBean.getRecords().get(i - 1);
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
        ActionContext.getContext().put("records", records);
        return "list";
    }
}

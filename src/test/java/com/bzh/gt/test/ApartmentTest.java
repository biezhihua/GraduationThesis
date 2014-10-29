package com.bzh.gt.test;

import com.bzh.gt.bean.Apartment;
import com.bzh.gt.service.ApartmentService;
import com.bzh.gt.service.DormitoryService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Test;

import javax.swing.*;
import java.util.List;

/**
 * Created by Administrator on 2014/8/19.
 */
public class ApartmentTest extends SpringUtils {

    // 添加公寓
    @Test
    public void addApartment() {
          SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
          Session session =   sessionFactory.openSession();
//
//           for(int i = 0; i < 144; i++) {
//               Apartment apartment = new Apartment();
//               apartment.setName(i+1+"栋");
//               if (i % 2 == 0) {
//                   apartment.setRank("普通公寓");
//                   apartment.setSex("男生公寓");
//               } else {
//                   apartment.setRank("高等公寓");
//                   apartment.setSex("女生公寓");
//               }
//               session.save(apartment);
//           }
        Apartment apartment =  new Apartment();
        apartment.setName("31栋");
        apartment.setSex("男生公寓");
        apartment.setRank("普通公寓");
        session.save(apartment);


        Apartment apartment1 =  new Apartment();
        apartment1.setName("29栋");
        apartment1.setSex("女生公寓");
        apartment1.setRank("普通公寓");
        session.save(apartment1);

        Apartment apartment2 =  new Apartment();
        apartment2.setName("10栋");
        apartment2.setSex("女生公寓");
        apartment2.setRank("高等公寓");
        session.save(apartment2);

        Apartment apartment3 =  new Apartment();
        apartment3.setName("13栋");
        apartment3.setSex("男生公寓");
        apartment3.setRank("高等公寓");
        session.save(apartment3);
    }

    @Test
    public void Test_getPartFieldInfo() {

        ApartmentService apartmentService = (ApartmentService) context.getBean("apartmentServiceImpl");

        List<Object[]> partInfo = apartmentService.getPartFieldInfo("id", "name");
        for (Object[] part : partInfo) {
            for (Object o : part) {
                System.out.println(o.toString());
            }
        }
        System.out.println(partInfo.toString());
        Assert.assertNotNull(partInfo);
    }
}

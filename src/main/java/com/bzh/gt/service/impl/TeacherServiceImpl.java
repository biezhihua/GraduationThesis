package com.bzh.gt.service.impl;

import com.bzh.gt.base.impl.DaoSupportImpl;
import com.bzh.gt.bean.Bed;
import com.bzh.gt.bean.Teacher;
import com.bzh.gt.service.BedService;
import com.bzh.gt.service.TeacherService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by biezhihua on 14-9-13.
 */

@Service
@Transactional
public class TeacherServiceImpl extends DaoSupportImpl<Teacher> implements TeacherService {
}

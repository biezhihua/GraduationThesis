package com.bzh.gt.service.impl;

import com.bzh.gt.base.impl.DaoSupportImpl;
import com.bzh.gt.bean.ExcelFile;
import com.bzh.gt.service.ExcelFileService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by biezhihua on 14-9-24.
 */

@Service
@Transactional
public class ExcelFileServiceImpl extends DaoSupportImpl<ExcelFile> implements ExcelFileService {
}

package com.gsau.portal.portal.service.impl;

import com.gsau.portal.feign_service.PortalSubjectTreeService;
import com.gsau.portal.portal.service.SubjectTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * @Description:
 * @Date: 2019/5/16 17:01
 * @author: WangGuoQing
 * @version: 1.0
 */
@Service
public class SubjectTreeServiceImpl implements SubjectTreeService {
    @Autowired
    PortalSubjectTreeService portalSubjectTreeService;


    @Override
    public Object getTrees_json() {
        return null;
    }

    @Override
    public void init() {

    }
}

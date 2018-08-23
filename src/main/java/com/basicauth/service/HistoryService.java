package com.basicauth.service;

import com.basicauth.mapper.HistoryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by Jabito on 10/19/2016.
 */
@Service("historyService")
public class HistoryService {

    private static Logger logger = LoggerFactory.getLogger(HistoryService.class);

    @Autowired
    private HistoryMapper claimsMapper;

}

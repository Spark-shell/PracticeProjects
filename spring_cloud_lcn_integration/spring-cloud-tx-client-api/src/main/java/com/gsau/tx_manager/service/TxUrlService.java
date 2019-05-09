package com.gsau.tx_manager.service;

import com.codingapi.tx.config.service.TxManagerTxUrlService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TxUrlService implements TxManagerTxUrlService {
    @Value("${tm.manager.url}")
    private String url;

    /**
     * 返回访问事务协调器的URL
     * @return
     */
    @Override
    public String getTxUrl() {
        return "http://192.168.199.56:8888/tx/manager/";
    }
}

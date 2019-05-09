package com.gsau.tx_manager.service;

import org.springframework.beans.factory.annotation.Value;

public class TxManagerTxUrlService implements com.codingapi.tx.config.service.TxManagerTxUrlService {
    @Value("${tm.manager.url}")
    private String url;

    /**
     * 返回访问事务协调器的URL
     * @return
     */
    @Override
    public String getTxUrl() {
        return "http://localhost:8888/tx/manager/";
    }
}

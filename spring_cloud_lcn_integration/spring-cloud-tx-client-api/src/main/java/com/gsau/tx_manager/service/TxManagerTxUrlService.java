package com.gsau.tx_manager.service;

import org.springframework.beans.factory.annotation.Value;

public class TxManagerTxUrlService implements com.codingapi.tx.config.service.TxManagerTxUrlService {
    @Value("${tm.manager.url}")
    private String url;
    @Override
    public String getTxUrl() {
        return "http://192.168.199.56:8899/tx/manager/";
    }
}

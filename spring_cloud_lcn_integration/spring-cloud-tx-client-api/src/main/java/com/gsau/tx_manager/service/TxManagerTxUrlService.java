package com.gsau.tx_manager.service;

import org.springframework.beans.factory.annotation.Value;

public class TxManagerTxUrlService implements com.codingapi.tx.config.service.TxManagerTxUrlService {
    @Value("${tx.manager.url}")
    private String url;
    @Override
    public String getTxUrl() {
        return "http://localhost:8888/tx/manager/";
    }
}

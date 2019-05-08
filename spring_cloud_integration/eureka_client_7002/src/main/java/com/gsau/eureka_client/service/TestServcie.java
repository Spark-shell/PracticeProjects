package com.gsau.eureka_client.service;

import com.codingapi.tx.annotation.TxTransaction;

public class TestServcie {
    /**
     * @          @TxTransaction(isStart = true) 为lcn 事务控制注解，r其中isStart = true 表示该方法是事务的发起方例如
     * @return
     */
    @TxTransaction(isStart = true)
    // @Transactional
    public String save(){
        return "SUCCESS";
    }
}

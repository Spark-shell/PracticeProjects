package com.gsau.springboot.exception;

/**
 * @author WangGuoQing
 * @date 2019/4/18 15:22
 * @Desc 自定义用户不存在异常
 */
public class UserNotExistException extends RuntimeException {
    public UserNotExistException() {
        super("用户不存在");
    }
}

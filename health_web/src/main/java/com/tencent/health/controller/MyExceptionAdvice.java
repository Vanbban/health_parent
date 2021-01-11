package com.tencent.health.controller;



import com.tencent.health.entity.Result;
import com.tencent.health.exception.HealthException;
import org.slf4j.Logger;
import org.csource.common.MyException;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * @author: Vanbban
 * @create 2021-01-08 20:36
 */
@RestControllerAdvice
public class MyExceptionAdvice {

    /**
     *  info:  打印日志，记录流程性的内容
     *  debug: 记录一些重要的数据 id, orderId, userId
     *  error: 记录异常的堆栈信息，代替e.printStackTrace();
     *  工作中不能有System.out.println(), e.printStackTrace();
     */
    private static final Logger log = LoggerFactory.getLogger(MyExceptionAdvice.class);

    /**
     * 业务异常的处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MyException.class)
    public Result handleMyException(MyException e) {
        return new Result(false, e.getMessage());
    }

    /**
     * 未知异常处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        // 记录异常信息
        log.error("发生未知异常", e);
        return new Result(false, "发生未知异常，请联系管理员");
    }
}

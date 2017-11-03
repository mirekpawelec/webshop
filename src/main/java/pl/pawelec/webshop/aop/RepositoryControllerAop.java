/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 *
 * @author mirek
 */
@Component
@Aspect
public class RepositoryControllerAop {
    private static final Logger log = Logger.getLogger(RepositoryControllerAop.class);
    
    @Around("execution(* pl.pawelec.webshop.controller.RepositoryController.*(..))")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable{
        Object result = null;
        log.info("[AROUND-BEFORE] Method called: " + joinPoint.getSignature());
        if(joinPoint.getArgs().length==0){
            log.info("[AROUND-BEFORE] No arguments passed.");
        }
        for(Object arg:joinPoint.getArgs()){
            log.info("[AROUND-BEFORE] Argument passed:" + arg);
        }
        try{
            log.info("[AROUND] Processing...");
            result = joinPoint.proceed();
            log.info("[AROUND-AFTER] Result: " + result);
            return result;
        }catch(IllegalArgumentException iae){
            log.error("[AROUND] Throws an exception: " + iae.getMessage());
            throw iae;
        }
    }
}

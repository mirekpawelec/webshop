/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.interceptor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.util.StopWatch;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author mirek
 */
public class PerformanceMonitorInterceptor implements HandlerInterceptor{

    private Logger logger = Logger.getLogger(PerformanceMonitorInterceptor.class.getName());
    ThreadLocal<StopWatch> localStopWatch = new ThreadLocal<StopWatch>();

    public boolean preHandle(HttpServletRequest request, HttpServletResponse hsr1, Object handler) throws Exception {
        StopWatch stopWatch = new StopWatch(handler.toString());
        stopWatch.start(handler.toString());
        localStopWatch.set(stopWatch);
        logger.info(" ==================================================== START ===================== ");
        logger.info("the current request path: " + getUrlPath(request));
        logger.info("start time: " + getCurrentTime());
        return true;
    }


    public void postHandle(HttpServletRequest hsr, HttpServletResponse hsr1, Object o, ModelAndView mav) throws Exception {
        logger.info("end time: " + getCurrentTime());
    }


    public void afterCompletion(HttpServletRequest hsr, HttpServletResponse hsr1, Object o, Exception excptn) throws Exception {
        StopWatch stopWatch = localStopWatch.get();
        stopWatch.stop();
        logger.info("the total time duration of process: " + stopWatch.getTotalTimeMillis() + " ms");
        localStopWatch.set(null);
        logger.info(" ==================================================== END ======================= ");
        
    }
    
    private String getUrlPath(HttpServletRequest request){
        String currentPath = request.getRequestURI();
        String requestParameters = request.getQueryString();
        requestParameters = requestParameters == null ? "" : "?" + requestParameters;
        return currentPath + requestParameters;
    }
    
    private String getCurrentTime(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime curDateTime = LocalDateTime.now();
        return dateTimeFormatter.format(curDateTime);
    }
    
}

package by.it.academy.takeanddrive.controllers;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Aspect
@Component
public class AspectController {

    @Pointcut("execution(* by.it.academy.takeanddrive.controllers..*(..))")
    public void logBefore() {
    }

    @Before("logBefore()")
    public void logRequest(JoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String REQUEST_LOG_PATTERN = "HttpMethod: {}, Controller Method: {}, URI: {}";
        log.warn(REQUEST_LOG_PATTERN,
                request.getMethod(),
                joinPoint.getSignature().toShortString(),
                request.getRequestURI());
    }
}

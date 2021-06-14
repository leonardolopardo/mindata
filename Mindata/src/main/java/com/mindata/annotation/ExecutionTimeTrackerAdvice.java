package com.mindata.annotation;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExecutionTimeTrackerAdvice {

	Logger logger = Logger.getLogger(ExecutionTimeTrackerAdvice.class);

	@Around("@annotation(com.mindata.annotation.TrackExecutionTime)")
	public Object trackTime(ProceedingJoinPoint pjp) throws Throwable {
		long startTime = System.currentTimeMillis();
		Object obj = pjp.proceed();
		long endTime = System.currentTimeMillis();
		logger.info("Method name: " + pjp.getSignature() + " time taken to execute : " + (endTime - startTime));
		return obj;
	}
}

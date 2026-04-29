package tn.esprit.ds.championat.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.logging.Logger;

@Aspect
@Component
public class LoggingAspect {

    private final Logger logger = Logger.getLogger(LoggingAspect.class.getName());

    // Pointcut commun pour toutes les méthodes du service
    @Pointcut("execution(* tn.esprit.ds.championat.services.ChampionatService.*(..))")
    public void serviceMethods() {}

    @Before("serviceMethods()")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("[@Before] " + joinPoint.getSignature().getName()
                + " | args = " + Arrays.toString(joinPoint.getArgs()));
    }

    @After("serviceMethods()")
    public void logAfter(JoinPoint joinPoint) {
        logger.info("[@After] Fin de " + joinPoint.getSignature().getName());
    }

    @AfterReturning(pointcut = "serviceMethods()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        logger.info("[@AfterReturning] " + joinPoint.getSignature().getName()
                + " | retour = " + result);
    }

    @AfterThrowing(pointcut = "serviceMethods()", throwing = "error")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
        logger.severe("[@AfterThrowing] " + joinPoint.getSignature().getName()
                + " | exception = " + error.getMessage());
    }

    //
    @Around("execution(* tn.esprit.ds.championat.services.ChampionatService.planifierMatch(..))")
    public Object logAroundPlanification(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        logger.info("[@Around] Avant planification");
        try {
            Object result = joinPoint.proceed();
            long elapsed = System.currentTimeMillis() - start;
            logger.info("[@Around] Planification terminée en " + elapsed + " ms");
            return result;
        } catch (Exception e) {
            logger.severe("[@Around] Erreur pendant la planification : " + e.getMessage());
            throw e;
        }
    }
}
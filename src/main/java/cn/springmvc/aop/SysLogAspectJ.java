package cn.springmvc.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class SysLogAspectJ {
//    @Pointcut("execution(* cn.springmvc..*.*(..))")
    @Pointcut("execution(* cn.springmvc.controller..*.*(..))")
    public void init(){

    }
//
//    @Before(value="init()")
//    public void before(JoinPoint point){
//		System.out.println("方法执行前执行.....");
//		// 拦截的实体类
//		Object target = point.getTarget();
//		// 拦截的方法名称
//		String methodName = point.getSignature().getName();
//		// 拦截的方法参数
//		Object[] args = point.getArgs();
//		if (target.getClass().getSimpleName().equals("InsuranceController") && methodName.startsWith("test")) {
//			for (Object obj : args) {
//				System.out.println("调用："+ methodName + "方法，参数：" + obj);
//			}
//			System.out.println("调用："+ methodName + "方法，参数：" + true );
//		}
//    }
//
//    @AfterReturning(value="init()")
//    public void afterReturning(){
//        System.out.println("方法执行完执行.....");
//    }
//
//    @AfterThrowing(value="init()")
//    public void throwss(){
//        System.out.println("方法异常时执行.....");
//    }

    @After(value="init()")
    public void after(){
        System.out.println("方法最后执行.....");
    }

//    @Around(value="init()")
//    public Object around(ProceedingJoinPoint pjp){
//        System.out.println("方法环绕start.....");
//        Object o = null;
//        try {
//            o = pjp.proceed();
//        } catch (Throwable e) {
//            e.printStackTrace();
//        }
//
//        System.out.println("方法环绕end.....");
//        System.out.println("方法结束");
//        return o;
//    }

}
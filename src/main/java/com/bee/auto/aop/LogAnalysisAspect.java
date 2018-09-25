package com.bee.auto.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LogAnalysisAspect {

    @Pointcut("execution(public * com.bee.auto.controller..*.*(..) )")
    public void point() {
    }

//    @Around("point()")
//    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
//        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
//
//        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
//        //获取完整的url路径：http://localhost:8083/sdk/wii/swbu
//        StringBuffer url = request.getRequestURL();
//
//        //获取请求的资源名部分：/sdk/wii/swbu
//        String uri = request.getRequestURI();
//
//        //获取请求类型：POST
//        String method = request.getMethod();
//
//        //获取请求ip:12.45.36.23
//        String ip = request.getRemoteAddr();
//        //获取请求表单参数
//        Map<String, String[]> paramsMap = request.getParameterMap();
//        String queryString = "";
//        for (String key : paramsMap.keySet()) {
//            String[] values = paramsMap.get(key);
//            for (int i = 0; i < values.length; i++) {
//                String value = values[i];
//                queryString += key + "=" + value + "&";
//            }
//        }
//
//        long startTime = System.currentTimeMillis();
//        long endTime = 0l;
//        float duration;
//        Object obj = null;
//        String packagePath = joinPoint.getTarget().getClass().getPackage().getName();
//        String pathResource = packagePath.split("\\.")[2];
//        String methodName = joinPoint.getSignature().getName();
//        obj = joinPoint.proceed();
//        List list = new ArrayList();
//        list.add(obj);
//        endTime = System.currentTimeMillis();
//        duration = (endTime - startTime) / 1000f;
//        log.info("当前访问路径：" + packagePath + ",包名：" + pathResource + "，访问方法：" + methodName + "请求地址：" + ip + "，请求参数：" + queryString + ",执行时间：" + duration);
//
//        return obj;
//    }

}

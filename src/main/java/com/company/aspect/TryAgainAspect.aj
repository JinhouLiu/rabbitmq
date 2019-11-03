package  com.company.aspect;

import com.company.exception.ApiException;
import com.company.exception.ApiResultEnum;
import com.company.exception.TryAgainException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;
import java.util.Arrays;

@Aspect
@Configuration
public class TryAgainAspect  implements Ordered {

    /**
     * 默认重试几次
     */
    private static final int    DEFAULT_MAX_RETRIES = 3;

    private int                 maxRetries          = DEFAULT_MAX_RETRIES;
    private int                 order               = 1;

    public void setMaxRetries(int maxRetries) {
        this.maxRetries = maxRetries;
    }

    public int getOrder() {
        return this.order;
    }

    @Pointcut("@annotation(com.company.annotation.IsTryAgain)")
    public void retryOnOptFailure() {
        // point
    }

    @Around("retryOnOptFailure()")
    @Transactional(rollbackFor = Exception.class)
    public Object doConcurrentOperation(ProceedingJoinPoint pjp) throws Throwable {
        int numAttempts = 0;
        Class clazz = pjp.getTarget().getClass();
        Method method = ((MethodSignature) pjp.getSignature()).getMethod();
        String codeName = clazz.getSimpleName()+"_"+method.getName();
        System.err.println("query param---->"+codeName);
        Object result = null;
        Object args = Arrays.asList(pjp.getArgs());
        do {
            numAttempts++;
            try {
                //再次执行业务代码
                return pjp.proceed();
            } catch (TryAgainException ex) {
                if (numAttempts > maxRetries) {
                    //log failure information, and throw exception
//					如果大于 默认的重试机制 次数，我们这回就真正的抛出去了
                    throw new ApiException(ApiResultEnum.ERROR_TRY_AGAIN_FAILED);
                }else{
                    //如果 没达到最大的重试次数，将再次执行
                    System.out.println("=====正在重试====="+numAttempts+"次");
                }
            }
        } while (numAttempts <= this.maxRetries);

        return null;
    }
}


package hello.aop;

import hello.aop.order.OrderRepository;
import hello.aop.order.OrderService;
import hello.aop.order.aop.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.*;

@Slf4j
@SpringBootTest
//@Import(AspectV1.class) //스프링 빈으로 등록
//@Import(AspectV2.class) //스프링 빈으로 등록
//@Import(AspectV3.class) //스프링 빈으로 등록
//@Import(AspectV4Pointcut.class) //스프링 빈으로 등록
//@Import({AspectV5Order.LogAspect.class, AspectV5Order.TransactionAspect.class}) //스프링 빈으로 등록
@Import(AspectV6Advice.class) //스프링 빈으로 등록
public class AopTest {

    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    @Test
    void aopInfo(){
        log.info("isAopProxy, orderService={}", AopUtils.isAopProxy(orderService));
        log.info("isAopProxy, orderRepository={}", AopUtils.isAopProxy(orderRepository));
    }

    @Test
    void success(){
        orderService.orderItem("itemA");
    }

    @Test
    void exception(){
        assertThatThrownBy(() -> orderService.orderItem("ex")).isInstanceOf(IllegalStateException.class);
    }

}

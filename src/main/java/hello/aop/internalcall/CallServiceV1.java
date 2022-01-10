package hello.aop.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CallServiceV1 {

    private CallServiceV1 callServiceV1;

    /**
     * 생성자 주입은 순환 사이클을 만들기 때문에 실패한다.
     * 수정자(setter) 주입도 스프링 부트 2.6부터 막힘 --> 순환참조 금지
     * 순환참조를 해결하기 위해 application.properties에 아래 파일 추가
     * spring.main.allow-circular-references=true
     */
    @Autowired
    public void setCallServiceV1(CallServiceV1 callServiceV1) {
        this.callServiceV1 = callServiceV1;
    }

    public void external(){
        log.info("call external");
        callServiceV1.internal(); //외부 메서드 호출
    }

    public void internal(){
        log.info("call internal");
    }
}

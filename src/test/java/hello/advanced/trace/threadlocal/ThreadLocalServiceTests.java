package hello.advanced.trace.threadlocal;

import hello.advanced.trace.threadlocal.code.FiledService;
import hello.advanced.trace.threadlocal.code.ThreadLocalService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ThreadLocalServiceTests {

    private ThreadLocalService threadLocalService = new ThreadLocalService();

    @Test
    void field(){
        log.info("main start");
        Runnable userA = () -> {
            threadLocalService.logic("userA");
        };
        Runnable userB = () -> {
            threadLocalService.logic("userB");
        };

        Thread threadA = new Thread(userA);
        threadA.setName("thread-A");
        Thread threadB = new Thread(userB);
        threadB.setName("thread-B");

        threadA.start();
        //sleep(2000); // 동시성 문제 발생X
        sleep(100); // 동시성 문제 발생X
        threadB.start();
        
        sleep(2000); // 메인 쓰레드 종료 대기
        log.info("main exit");
    }

    private void sleep(int milliis) {
        try {
            Thread.sleep(milliis);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}

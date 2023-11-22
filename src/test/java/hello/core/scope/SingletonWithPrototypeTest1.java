package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.*;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(Prototypebean.class);
        Prototypebean prototypebean1 = ac.getBean(Prototypebean.class);
        prototypebean1.addCount();
        assertThat(prototypebean1.getCount()).isEqualTo(1);

        Prototypebean prototypebean2 = ac.getBean(Prototypebean.class);
        prototypebean2.addCount();
        assertThat(prototypebean2.getCount()).isEqualTo(1);

    }

    @Test
    void singletonClientUsePrototype(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, Prototypebean.class);


        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);


        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(2);
    }

    @Scope("singleton")
    static class ClientBean {
        private final Prototypebean prototypebean; // 생성시점에 주입



        @Autowired
        public ClientBean(Prototypebean prototypebean) {
            this.prototypebean = prototypebean;
        }

        public int logic(){
            prototypebean.addCount();
            return prototypebean.getCount();
        }

    }



    @Scope("prototype")
    static class Prototypebean{
        private int count = 0;

        public void addCount(){
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init(){
            System.out.println("PrototypeBean.init" + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }



    }
}

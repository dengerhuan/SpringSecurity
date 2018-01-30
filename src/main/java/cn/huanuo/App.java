package cn.huanuo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.PostConstruct;
import java.util.Map;

@SpringBootApplication
public class App {

    @Autowired
    RequestMappingHandlerConfig requestMappingHandlerConfig;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
    @PostConstruct
    public void detectHandlerMethods() {
        final RequestMappingHandlerMapping requestMappingHandlerMapping = requestMappingHandlerConfig.requestMappingHandlerMapping();


        Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping.getHandlerMethods();


        for (RequestMappingInfo info : map.keySet()) {
            HandlerMethod method = map.get(info);
            System.out.println(info.getPatternsCondition().toString() + method.toString());
        }

    }
}

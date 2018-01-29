package cn.huanuo.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @GetMapping("/product/{id}")
    public String getProduct(@PathVariable String id) {
        //for debug
       // Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "product id : " + id;
    }

    @GetMapping("/order/{id}")
    public String getOrder(@PathVariable String id) {
        //for debug
        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "order id : " + id;
    }

}

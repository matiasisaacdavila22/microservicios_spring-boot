package curso.ms.cursos1;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping(value = "greeting")
public class GreetingController {
    private final AtomicLong counter = new AtomicLong();
    private static final String template = "hello %s";

    @GetMapping
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "world") String name){
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

}

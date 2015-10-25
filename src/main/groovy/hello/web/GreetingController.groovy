package hello.web

import java.util.concurrent.atomic.AtomicLong
import hello.domain.Greeting
import org.springframework.web.bind.annotation.*
import groovy.util.logging.Slf4j


@Slf4j
@RestController
public class GreetingController {
    private static final String template = "Hello, %s!"
    private final AtomicLong counter = new AtomicLong()


    @RequestMapping(value = "/greeting", method = RequestMethod.GET)
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        log.debug("inside greeting method with "+name)
        return new Greeting(counter.incrementAndGet(), String.format(template, name))
    }
}

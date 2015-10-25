package hello

import spock.lang.Specification
import org.springframework.test.context.ContextConfiguration
import org.springframework.boot.test.SpringApplicationContextLoader


@ContextConfiguration(loader = SpringApplicationContextLoader.class, classes = Application)
class ApplicationSpec extends Specification {
    void contextLoads() {
    }
}
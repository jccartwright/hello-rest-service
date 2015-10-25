package hello.web

import hello.domain.Greeting
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification
import org.springframework.test.context.ContextConfiguration
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.context.WebApplicationContext
import org.springframework.test.context.web.WebAppConfiguration
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.http.HttpStatus.*

import hello.Application
import groovy.json.JsonSlurper



@ContextConfiguration(loader = SpringApplicationContextLoader.class, classes = Application)
@WebAppConfiguration
class GreetingControllerSpec extends Specification {
    //alternative way to construct MockMvc
    //private controller = new GreetingController()
    //private MockMvc mockMvc = standaloneSetup(controller).build()

    private MockMvc mockMvc

    @Autowired
    private WebApplicationContext webApplicationContext

    def setup() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build()
    }

    def "greeting with default user"() {
        def response = mockMvc.perform(get('/greeting')).andReturn().response
        def payload = new JsonSlurper().parseText(response.contentAsString)
        println payload

        expect:
        response.status == OK.value()
        payload.content == 'Hello, World!'

    }


    def "greeting without MVC framework"() {
        GreetingController controller = new GreetingController()

        when:
        //default name not available w/o MVC
        def response = controller.greeting('World')

        then:
        response instanceof Greeting
        response.content == "Hello, World!"
    }
}



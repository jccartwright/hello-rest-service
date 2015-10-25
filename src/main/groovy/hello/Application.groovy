package hello

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.SpringApplication
import org.apache.catalina.connector.Connector
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory
import org.springframework.context.annotation.*
import org.springframework.beans.factory.annotation.*
import groovy.util.logging.Slf4j


@Slf4j
@SpringBootApplication // same as @Configuration @EnableAutoConfiguration @ComponentScan
class Application {
    @Value('${ajpPort}')
    private int ajpPort


    static void main(String[] args) {
        SpringApplication.run(Application.class, args)
    }


    @Bean
    EmbeddedServletContainerFactory servletContainer() {
        TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory()
        tomcat.addAdditionalTomcatConnectors(createAjpConnector())
        return tomcat
    }


    private Connector createAjpConnector() {
        Connector connector = new Connector("org.apache.coyote.ajp.AjpProtocol")
        connector.setScheme("ajp")
        connector.setProtocol("AJP/1.3")
        connector.setPort(ajpPort)
        return connector
    }
}

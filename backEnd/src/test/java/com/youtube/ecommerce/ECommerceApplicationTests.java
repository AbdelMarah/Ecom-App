package com.youtube.ecommerce;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest // une annotation fournie par Spring Boot. Elle permet lors de l’exécution des tests d’initialiser le contexte Spring. Les beans de notre application peuvent alors être utilisés.
class ECommerceApplicationTests {

    @Test
    //@DirtiesContext
    void contextLoads() {
        // Your test logic here
    }

}
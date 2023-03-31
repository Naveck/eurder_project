package com.switchfully.eurder.item.api;

import com.switchfully.eurder.item.domain.repositories.ItemRepository;
import com.switchfully.eurder.item.service.dtos.ItemDTO;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ItemControllerIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    ItemRepository itemRepository;
    @Autowired
    ItemController itemController;

    @Test
    void whenIPostAnItem_thenTheRepositoryContainsThisItem() {
        ItemDTO newItemDTO = new ItemDTO("itemName","someDescription",100.0,1);
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(newItemDTO)
                .when()
                .port(port)
                .post("/items") // http://localhost:???/contacts
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value());

        assertTrue(itemController.getAllItems().contains(newItemDTO));
    }

}
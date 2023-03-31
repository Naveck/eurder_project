package com.switchfully.eurder.item.api;

import com.switchfully.eurder.item.domain.repositories.ItemRepository;
import com.switchfully.eurder.item.service.dtos.ItemDTO;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

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
        ItemDTO newItemDTO = new ItemDTO("itemName", "someDescription", 100.0, 1);
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

    @Test
    void whenTheRepositoryContains2Customers_thenICanRetrieveThemViaTheAPI() {
        ItemDTO newItemDTO1 = new ItemDTO("itemName", "someDescription", 100.0, 1);
        ItemDTO newItemDTO2 = new ItemDTO("anotherItemName", "someDescriptionAgain", 35.35, 35);
        itemController.addItem(newItemDTO1);
        itemController.addItem(newItemDTO2);

        List<ItemDTO> list = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .when()
                .port(port)
                .get("/items") // http://localhost:???/contacts
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .body()
                .jsonPath()
                .getList(".", ItemDTO.class);

        Assertions.assertThat(list).containsExactlyInAnyOrder(newItemDTO1, newItemDTO2);
    }
}
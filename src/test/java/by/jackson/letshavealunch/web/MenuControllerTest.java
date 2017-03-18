package by.jackson.letshavealunch.web;

import by.jackson.letshavealunch.DishTestData;
import by.jackson.letshavealunch.MenuTestData;
import by.jackson.letshavealunch.RestaurantTestData;
import by.jackson.letshavealunch.model.Dish;
import by.jackson.letshavealunch.model.Menu;
import by.jackson.letshavealunch.model.MenuItem;
import by.jackson.letshavealunch.service.MenuService;
import by.jackson.letshavealunch.web.json.JsonUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;

import static by.jackson.letshavealunch.MenuTestData.*;
import static by.jackson.letshavealunch.RestaurantTestData.RESTAURANT1_ID;
import static by.jackson.letshavealunch.TestUtil.userHttpBasic;
import static by.jackson.letshavealunch.UserTestData.ADMIN;
import static by.jackson.letshavealunch.UserTestData.USER;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MenuControllerTest extends AbstractControllerTest {
    private static final String REST_URL = MenuController.REST_URL + '/';

    @Autowired
    private MenuService service;

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + MENU1_ID)
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentMatcher(MenuTestData.MENU1));
    }

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentListMatcher(Arrays.asList(MENU3, MENU4, MENU1, MENU2)));
    }

    @Test
    public void testGetByRestaurant() throws Exception {
        mockMvc.perform(get(REST_URL + "?restaurant=" + RESTAURANT1_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentListMatcher(Arrays.asList(MENU3, MENU1)));
    }


    @Test
    public void testGetByDate() throws Exception {
        mockMvc.perform(get(REST_URL + "?date=" + MENU1.getDate())
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentListMatcher(Arrays.asList(MENU1, MENU2)));
    }

    @Test
    public void testGetByDateAndRestaurant() throws Exception {
        mockMvc.perform(get(REST_URL + "?date=" + MENU1.getDate()+ "&restaurant=" + RESTAURANT1_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentListMatcher(Collections.singletonList(MENU1)));
    }

    @Test
    public void testGetUnauth() throws Exception {
        mockMvc.perform(get(REST_URL + MENU1_ID))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void testGetNotFound() throws Exception {
        mockMvc.perform(get(REST_URL + 1)
                .with(userHttpBasic(USER)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    @Transactional
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + MENU1_ID)
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isOk());
        MATCHER.assertCollectionEquals(Arrays.asList(MENU3, MENU4, MENU2), service.getAll());
    }

    @Test
    public void testDeleteNotFound() throws Exception {
        mockMvc.perform(delete(REST_URL + 1)
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }


    @Test
    @Transactional
    public void testDeleteDenied() throws Exception {
        mockMvc.perform(delete(REST_URL + MENU1_ID)
                .with(userHttpBasic(USER)))
                .andExpect(status().isForbidden());
    }

    @Test
    @Transactional
    public void testUpdate() throws Exception {
        Menu updated = new Menu(MENU1_ID,
                LocalDate.parse("2017-02-01"),
                RestaurantTestData.RESTAURANT1,
                Arrays.asList(
                        new MenuItem(DishTestData.DISH1, 9.77F),
                        new MenuItem(DishTestData.DISH2, 8.85F),
                        new MenuItem(DishTestData.DISH8, 10.55F)
                ));

        mockMvc.perform(put(REST_URL + MENU1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated))
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk());

        assertEquals(updated, service.get(MENU1_ID));
    }

    @Test
    public void testUpdateDenied() throws Exception {
        Menu updated = new Menu(MENU1_ID,
        LocalDate.parse("2017-02-01"),
        RestaurantTestData.RESTAURANT1,
        Arrays.asList(
                new MenuItem(DishTestData.DISH1, 9.77F),
                new MenuItem(DishTestData.DISH2, 8.85F),
                new MenuItem(DishTestData.DISH8, 10.55F)
        ));

        mockMvc.perform(put(REST_URL + MENU1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated))
                .with(userHttpBasic(USER)))
                .andExpect(status().isForbidden());
    }

    @Test
    public void testUpdateInvalid() throws Exception {
        Menu invalid = new Menu(MENU1_ID,
                null,
                RestaurantTestData.RESTAURANT1,
                Arrays.asList(
                        new MenuItem(DishTestData.DISH1, 9.77F),
                        new MenuItem(DishTestData.DISH2, 8.85F),
                        new MenuItem(DishTestData.DISH8, 10.55F)
                ));

        mockMvc.perform(put(REST_URL + MENU1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid))
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void testUpdateHtmlUnsafe() throws Exception {
        Menu invalid = new Menu(MENU1_ID,
                null,
                RestaurantTestData.RESTAURANT1,
                Arrays.asList(
                        new MenuItem(new Dish("<script>alert(123)</script>"), 9.77F),
                        new MenuItem(DishTestData.DISH2, 8.85F),
                        new MenuItem(DishTestData.DISH8, 10.55F)
                ));

        mockMvc.perform(put(REST_URL + MENU1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid))
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    @Transactional
    public void testCreate() throws Exception {
        Menu newMenu = new Menu(
                LocalDate.parse("2017-02-03"),
                RestaurantTestData.RESTAURANT1,
                Arrays.asList(
                        new MenuItem(DishTestData.DISH1, 9.77F),
                        new MenuItem(DishTestData.DISH2, 8.85F),
                        new MenuItem(DishTestData.DISH8, 10.55F)
                ));

        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newMenu))
                .with(userHttpBasic(ADMIN)));

        Menu returned = MATCHER.fromJsonAction(action);
        newMenu.setId(returned.getId());

        MATCHER.assertEquals(newMenu, returned);
        MATCHER.assertCollectionEquals(
                Arrays.asList(newMenu, MENU3, MENU4, MENU1, MENU2),
                service.getAll());
    }

    @Test
    public void testCreateInvalid() throws Exception {
        Menu invalid = new Menu(
                null,
                RestaurantTestData.RESTAURANT1,
                Arrays.asList(
                        new MenuItem(DishTestData.DISH1, 9.77F),
                        new MenuItem(DishTestData.DISH2, 8.85F),
                        new MenuItem(DishTestData.DISH8, 10.55F)
                ));

        mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid))
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void testCreateDenied() throws Exception {
        Menu invalid = new Menu(
                LocalDate.parse("2017-02-03"),
                RestaurantTestData.RESTAURANT1,
                Arrays.asList(
                        new MenuItem(DishTestData.DISH1, 9.77F),
                        new MenuItem(DishTestData.DISH2, 8.85F),
                        new MenuItem(DishTestData.DISH8, 10.55F)
                ));
        mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid))
                .with(userHttpBasic(USER)))
                .andExpect(status().isForbidden());
    }

    @Test
    public void testUpdateDuplicate() throws Exception {
        Menu duplicate = new Menu(MENU2_ID,
                LocalDate.parse("2017-02-01"),
                RestaurantTestData.RESTAURANT1,
                Arrays.asList(
                        new MenuItem(DishTestData.DISH1, 9.77F),
                        new MenuItem(DishTestData.DISH2, 8.85F)
                ));
        mockMvc.perform(put(REST_URL + MENU1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(duplicate))
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isConflict());
    }

    @Test
    public void testCreateDuplicate() throws Exception {
        Menu duplicate = new Menu(
                LocalDate.parse("2017-02-01"),
                RestaurantTestData.RESTAURANT1,
                Arrays.asList(
                        new MenuItem(DishTestData.DISH1, 9.77F),
                        new MenuItem(DishTestData.DISH2, 8.85F)
                ));
        mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(duplicate))
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isConflict());
    }
}
package com.seonjuleee.msa.itemservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seonjuleee.msa.itemservice.controller.ItemController;
import com.seonjuleee.msa.itemservice.dto.ItemDTO;
import com.seonjuleee.msa.itemservice.service.ItemService;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class ItemServiceApplicationTests {

    @InjectMocks
    private ItemController itemController;

    @Mock
    private ItemService itemService;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(itemController)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))
                .build();
    }

    @Test
    @DisplayName("물품 등록 테스트")
    void add_item() throws Exception {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            mockMvc.perform(post("/v1/item/add/C")
                    .contentType(MediaType.APPLICATION_JSON)
                    .header("accountId", "admin")
                    .accept(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(getTestItem()))
                    )
                    .andDo(print())
                    .andExpect(status().isOk());
        } catch (Exception e) {
            fail(ExceptionUtils.getStackTrace(e));
        }
    }

    private ItemDTO getTestItem() {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId("test1");
        itemDTO.setName("상품명");
        itemDTO.setDescription("상품 설명");
        itemDTO.setCount(50);
        return itemDTO;
    }
}

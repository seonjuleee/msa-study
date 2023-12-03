package com.seonjuleee.msa.itemservice.controller;

import com.seonjuleee.msa.itemservice.constant.ItemType;
import com.seonjuleee.msa.itemservice.dto.ItemDTO;
import com.seonjuleee.msa.itemservice.dto.ResponseDTO;
import com.seonjuleee.msa.itemservice.service.ItemService;
import com.seonjuleee.msa.itemservice.valid.ItemTypeValid;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "v1/item")
@Slf4j
@RequiredArgsConstructor
@Validated
public class ItemController {

    private final ItemService itemService;

    @RequestMapping(value = "/add/{itemType}", method = RequestMethod.POST)
    public ResponseEntity<ResponseDTO> add(@Valid @RequestBody ItemDTO itemDTO,
                                           @ItemTypeValid @PathVariable String itemType) {
        ResponseDTO.ResponseDTOBuilder responseBuilder = ResponseDTO.builder();

        itemDTO.setItemType(itemType);

        itemService.insertItem(itemDTO);
        log.debug("item id = {}", itemDTO.getId());

        responseBuilder.code("200").message("success");
        return ResponseEntity.ok(responseBuilder.build());
    }
}

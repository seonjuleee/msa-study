package com.seonjuleee.msa.itemservice.controller;

import com.seonjuleee.msa.itemservice.constant.ItemType;
import com.seonjuleee.msa.itemservice.dto.ItemDTO;
import com.seonjuleee.msa.itemservice.dto.ResponseDTO;
import com.seonjuleee.msa.itemservice.service.ItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "v1/item")
@Slf4j
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @RequestMapping(value = "/add/{itemType}", method = RequestMethod.POST)
    public ResponseEntity<ResponseDTO> add(@Valid @RequestBody ItemDTO itemDTO,
                                           @PathVariable String itemType) {
        ResponseDTO.ResponseDTOBuilder responseBuilder = ResponseDTO.builder();

        log.debug("itemType : {}", itemType);

        boolean hasItemType = false;
        ItemType[] itemTypes = ItemType.values();
        for (ItemType it : itemTypes) {
            hasItemType = it.hasItemCode(itemType);
            if (hasItemType) break;
        }

        if (!hasItemType) {
            responseBuilder.code("500").message("Invalid itemType ["+itemType+"]");
            return ResponseEntity.ok(responseBuilder.build());
        } else {
            itemDTO.setItemType(itemType);
        }

        itemService.insertItem(itemDTO);
        log.debug("item id = {}", itemDTO.getId());

        responseBuilder.code("200").message("success");
        return ResponseEntity.ok(responseBuilder.build());
    }
}

package com.seonjuleee.msa.itemservice.service;

import com.seonjuleee.msa.itemservice.domain.Item;
import com.seonjuleee.msa.itemservice.dto.ItemDTO;
import com.seonjuleee.msa.itemservice.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    public void insertItem(ItemDTO itemDTO) {
        SimpleDateFormat form = new SimpleDateFormat("yyyyMMddHHmmss");
        String date = form.format(new Date()); // todo 나중에 LocalDateTime으로 변경하기

        Item item = Item.builder()
                .id(itemDTO.getId())
                .name(itemDTO.getName())
                .description(itemDTO.getName())
                .count(itemDTO.getCount())
                .regDts(date)
                .updDts(date)
                .build();

        itemRepository.save(item);
    }
}

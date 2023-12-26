package com.peakosoft.giftlistj7.service;

import com.peakosoft.giftlistj7.mapper.WishListMapper;
import com.peakosoft.giftlistj7.model.dto.WishListRequest;
import com.peakosoft.giftlistj7.model.dto.WishListResponse;
import com.peakosoft.giftlistj7.model.entities.Gift;
import com.peakosoft.giftlistj7.repository.WishListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WishListService {
    private final WishListRepository wishListRepository;
    private final WishListMapper wishListMapper;
    public WishListResponse save(WishListRequest wishListRequest) {
        Gift gift = wishListMapper.mapToEntity(wishListRequest);
        wishListRepository.save(gift);
        return wishListMapper.mapToResponse(gift);
    }
    public List<WishListResponse> findAll(Long userId) {
        List<Gift> myGifts = wishListRepository
                .findAllByUserId(userId)
                .orElseThrow(()-> new RuntimeException("Not found user by id: " + userId));
        return myGifts.stream().map(wishListMapper::mapToResponse).toList();

    }
    public WishListResponse update(Long giftId, WishListRequest wishListRequest) {
        Gift oldGift = wishListRepository.findById(giftId).orElseThrow(()-> new RuntimeException("Not found gift by id: " + giftId));
        wishListRepository.save(oldGift);
        return wishListMapper.mapToResponse(oldGift);
    }

    public void delete(Long giftId){
        Gift gift = wishListRepository.findById(giftId).orElseThrow(()-> new RuntimeException("Not found gift by id: " + giftId));
        wishListRepository.delete(gift);
    }
}

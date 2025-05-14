package com.example.taggo.domain.wishlist.service;

import com.example.taggo.domain.place.model.Place;
import com.example.taggo.domain.place.service.PlaceService;
import com.example.taggo.domain.user.model.User;
import com.example.taggo.domain.user.service.UserService;
import com.example.taggo.domain.wishlist.api.request.CreateWishListRequest;
import com.example.taggo.domain.wishlist.api.response.GetWishListResponse;
import com.example.taggo.domain.wishlist.model.WishList;
import com.example.taggo.domain.wishlist.repository.WishListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WishListService {
    private final WishListRepository wishListRepository;
    private final PlaceService placeService;
    private final UserService userService;

    public void addWishList(CreateWishListRequest request) {
        // To-do 입력받은 JWT 에서 유저 아이디 추출, 해당 유저 아이디로 유저 조회
        User user = userService.getById(1L);
        Place place = placeService.findOrCreate(request.kakaoId(), request.name());
        wishListRepository.save(WishList.create(user, place));
    }

    public void removeWishList(Long id) {
        wishListRepository.deleteById(id);
    }

    public GetWishListResponse getByUserId(){
        List<WishList> result = wishListRepository.findByUserId(1L);
        return GetWishListResponse.from(result);
    }

}

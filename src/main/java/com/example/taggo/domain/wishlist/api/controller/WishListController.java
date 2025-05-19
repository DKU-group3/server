package com.example.taggo.domain.wishlist.api.controller;

import com.example.taggo.domain.wishlist.api.request.CreateWishListRequest;
import com.example.taggo.domain.wishlist.api.response.GetWishListResponse;
import com.example.taggo.domain.wishlist.model.WishList;
import com.example.taggo.domain.wishlist.service.WishListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/v1/wishlist")
@RequiredArgsConstructor
public class WishListController {
    private final WishListService wishListService;

    @GetMapping
    public ResponseEntity<GetWishListResponse> getWishList(){
        return ResponseEntity.ok(wishListService.getByUserId());
    }

    @PostMapping
    public ResponseEntity<Void> createWishList(@RequestBody CreateWishListRequest request){
        wishListService.addWishList(request);
        return ResponseEntity.status(CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWishList(@PathVariable Long id){
        wishListService.removeWishList(id);
        return ResponseEntity.noContent().build();
    }
}

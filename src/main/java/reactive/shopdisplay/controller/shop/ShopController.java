package reactive.shopdisplay.controller.shop;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactive.shopdisplay.service.shop.ShopService;
import reactive.shopdisplay.service.shop.dto.ShopDetailResponse;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
public class ShopController {

    private final ShopService shopService;

    @GetMapping("/api/v1/shops/detail")
    public Mono<ShopDetailResponse> fetchShopDetails(Long shopNumber) {
        return shopService.fetchShopDetailsBy(shopNumber);
    }

}

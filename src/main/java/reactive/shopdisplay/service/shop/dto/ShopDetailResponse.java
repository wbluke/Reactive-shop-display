package reactive.shopdisplay.service.shop.dto;

import lombok.RequiredArgsConstructor;
import reactive.shopdisplay.domain.ad.ShopAd;
import reactive.shopdisplay.domain.shop.Shop;
import reactive.shopdisplay.domain.statistics.ShopStatistics;

@RequiredArgsConstructor
public class ShopDetailResponse {

    private final Shop shop;
    private final ShopAd shopAd;
    private final ShopStatistics shopStatistics;

}

package reactive.shopdisplay.service.shop;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactive.shopdisplay.domain.ad.ShopAd;
import reactive.shopdisplay.domain.ad.ShopAdReactiveRepository;
import reactive.shopdisplay.domain.shop.Shop;
import reactive.shopdisplay.domain.shop.ShopReactiveRepository;
import reactive.shopdisplay.domain.statistics.ShopStatistics;
import reactive.shopdisplay.domain.statistics.ShopStatisticsReactiveRepository;
import reactive.shopdisplay.service.shop.dto.ShopDetailResponse;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class ShopService {

    private final ShopReactiveRepository shopReactiveRepository;
    private final ShopAdReactiveRepository shopAdReactiveRepository;
    private final ShopStatisticsReactiveRepository shopStatisticsReactiveRepository;

    public Mono<ShopDetailResponse> fetchShopDetailsBy(Long shopNumber) {
        Mono<Shop> shop = shopReactiveRepository.findOpenShopById(shopNumber);
        Mono<ShopAd> shopAdMono = shopAdReactiveRepository.findByShopNumber(shopNumber);
        Mono<ShopStatistics> statisticsMono = shopStatisticsReactiveRepository.findByShopNumber(shopNumber);

        return Mono.zip(shop, shopAdMono, statisticsMono)
                .map(tuple -> new ShopDetailResponse(tuple.getT1(), tuple.getT2(), tuple.getT3()));
    }

}

package reactive.shopdisplay.domain.ad;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ShopAdReactiveRepository {

    Mono<ShopAd> findByShopNumber(Long shopNumber);

    Mono<ShopAd> save(ShopAd shopAd);

    Flux<ShopAd> saveAll(List<ShopAd> shopAd);

}

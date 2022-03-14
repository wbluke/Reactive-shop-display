package reactive.shopdisplay.repository.ad;

import org.springframework.stereotype.Repository;
import reactive.shopdisplay.domain.ad.ShopAd;
import reactive.shopdisplay.domain.ad.ShopAdReactiveRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ShopAdInMemoryReactiveRepository implements ShopAdReactiveRepository {

    private final AtomicLong shopAdId = new AtomicLong(1);
    private final Map<Long, ShopAd> shopAds = new HashMap<>();

    @Override
    public Mono<ShopAd> findByShopNumber(Long shopNumber) {
        ShopAd shopAd = shopAds.values().stream()
                .filter(ad -> ad.isSameShopNumber(shopNumber))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);

        return Mono.just(shopAd);
    }

    @Override
    public Mono<ShopAd> save(ShopAd shopAd) {
        ShopAd savedShopAd = saveOne(shopAd);
        return Mono.just(savedShopAd);
    }

    @Override
    public Flux<ShopAd> saveAll(List<ShopAd> shopAds) {
        return Flux.fromStream(
                shopAds.stream()
                        .map(this::saveOne)
        );
    }

    private ShopAd saveOne(ShopAd shopAd) {
        long shopAdId = this.shopAdId.getAndIncrement();
        shopAd.setId(shopAdId);

        this.shopAds.put(shopAdId, shopAd);
        return shopAd;
    }

}

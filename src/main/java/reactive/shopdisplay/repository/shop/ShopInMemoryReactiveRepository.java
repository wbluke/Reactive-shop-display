package reactive.shopdisplay.repository.shop;

import org.springframework.stereotype.Repository;
import reactive.shopdisplay.domain.shop.Shop;
import reactive.shopdisplay.domain.shop.ShopReactiveRepository;
import reactive.shopdisplay.domain.shop.ShopStatus;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ShopInMemoryReactiveRepository implements ShopReactiveRepository {

    private final AtomicLong shopId = new AtomicLong(1);
    private final Map<Long, Shop> shops = new HashMap<>();

    @Override
    public Mono<Shop> findById(Long shopNumber) {
        return Mono.just(shops.get(shopNumber));
    }

    @Override
    public Flux<Shop> findAllByIdsAndShopStatus(List<Long> shopNumbers, ShopStatus shopStatus) {
        return null;
    }

    @Override
    public Mono<Shop> save(Shop shop) {
        long shopId = this.shopId.getAndIncrement();
        shop.setShopNumber(shopId);

        this.shops.put(shopId, shop);
        return Mono.just(shop);
    }

    @Override
    public Flux<Shop> saveAll(List<Shop> shop) {
        return null;
    }

}

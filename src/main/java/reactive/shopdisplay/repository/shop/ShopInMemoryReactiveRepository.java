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
    public Mono<Shop> findOpenShopById(Long shopNumber) {
        Shop shop = shops.get(shopNumber);
        if (shop.isOpen()) {
            return Mono.just(shop);
        }
        return Mono.error(() -> new IllegalArgumentException("OPEN 상태의 가게가 아닙니다."));
    }

    @Override
    public Mono<Shop> save(Shop shop) {
        Shop savedShop = saveOne(shop);
        return Mono.just(savedShop);
    }

    @Override
    public Flux<Shop> saveAll(List<Shop> shops) {
        return Flux.fromStream(
                shops.stream()
                        .map(this::saveOne)
        );
    }

    private Shop saveOne(Shop shop) {
        long shopId = this.shopId.getAndIncrement();
        shop.setShopNumber(shopId);

        this.shops.put(shopId, shop);
        return shop;
    }

}

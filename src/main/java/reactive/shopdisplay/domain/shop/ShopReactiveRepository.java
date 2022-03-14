package reactive.shopdisplay.domain.shop;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ShopReactiveRepository {

    Mono<Shop> findById(Long shopNumber);

    Flux<Shop> findAllByIdsAndShopStatus(List<Long> shopNumbers, ShopStatus shopStatus);

    Mono<Shop> save(Shop shop);

    Flux<Shop> saveAll(List<Shop> shop);

}

package reactive.shopdisplay.domain.shop;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ShopReactiveRepository {

    Mono<Shop> findById(Long shopNumber);

    Mono<Shop> findOpenShopById(Long shopNumber);

    Mono<Shop> save(Shop shop);

    Flux<Shop> saveAll(List<Shop> shop);

}

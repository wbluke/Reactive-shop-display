package reactive.shopdisplay.domain.statistics;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ShopStatisticsReactiveRepository {

    Mono<ShopStatistics> findByShopNumber(Long shopNumber);

    Mono<ShopStatistics> save(ShopStatistics shopStatistics);

    Flux<ShopStatistics> saveAll(List<ShopStatistics> shopStatistics);

}

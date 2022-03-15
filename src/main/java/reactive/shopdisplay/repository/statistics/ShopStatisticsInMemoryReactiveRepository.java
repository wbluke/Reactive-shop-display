package reactive.shopdisplay.repository.statistics;

import org.springframework.stereotype.Repository;
import reactive.shopdisplay.domain.statistics.ShopStatistics;
import reactive.shopdisplay.domain.statistics.ShopStatisticsReactiveRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ShopStatisticsInMemoryReactiveRepository implements ShopStatisticsReactiveRepository {

    private final AtomicLong shopStatisticsId = new AtomicLong(1);
    private final Map<Long, ShopStatistics> statistics = new HashMap<>();

    @Override
    public Mono<ShopStatistics> findByShopNumber(Long shopNumber) {
        ShopStatistics shopStatistics = statistics.values().stream()
                .filter(ad -> ad.isSameShopNumber(shopNumber))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);

        return Mono.just(shopStatistics);
    }

    @Override
    public Mono<ShopStatistics> save(ShopStatistics shopStatistics) {
        ShopStatistics savedShopStatistics = saveOne(shopStatistics);
        return Mono.just(savedShopStatistics);
    }

    @Override
    public Flux<ShopStatistics> saveAll(List<ShopStatistics> shopStatisticss) {
        return Flux.fromStream(
                shopStatisticss.stream()
                        .map(this::saveOne)
        );
    }

    private ShopStatistics saveOne(ShopStatistics shopStatistics) {
        long shopStatisticsId = this.shopStatisticsId.getAndIncrement();
        shopStatistics.setId(shopStatisticsId);

        this.statistics.put(shopStatisticsId, shopStatistics);
        return shopStatistics;
    }

}

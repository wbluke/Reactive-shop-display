package reactive.shopdisplay.domain.shop;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static reactive.shopdisplay.domain.shop.ShopStatus.CLOSE;
import static reactive.shopdisplay.domain.shop.ShopStatus.PREPARING;

@SpringBootTest
class ShopInMemoryRepositoryTest {

    @Autowired
    private ShopReactiveRepository shopReactiveRepository;

    @DisplayName("Shop 단건 저장 및 shopNumber 발급이 된다.")
    @Test
    void save() {
        // given
        Shop shop = createShop("덕수네공방", PREPARING);

        // when
        Mono<Shop> shopMono = shopReactiveRepository.save(shop);

        // then
        StepVerifier.create(shopMono)
                .assertNext(result -> {
                    assertThat(result.getShopNumber()).isNotNull();
                    assertThat(result)
                            .extracting("shopName", "shopStatus")
                            .containsExactly("덕수네공방", PREPARING);
                })
                .verifyComplete();
    }

    @DisplayName("Shop을 다건 저장한다.")
    @Test
    void saveAll() {
        // given
        Shop shop1 = createShop("덕수네공방", PREPARING);
        Shop shop2 = createShop("우빈이네카페", CLOSE);

        // when
        Flux<Shop> shopFlux = shopReactiveRepository.saveAll(Arrays.asList(shop1, shop2));

        // then
        StepVerifier.create(shopFlux)
                .assertNext(result -> {
                    assertThat(result.getShopNumber()).isNotNull();
                    assertThat(result)
                            .extracting("shopName", "shopStatus")
                            .containsExactly("덕수네공방", PREPARING);
                })
                .assertNext(result -> {
                    assertThat(result.getShopNumber()).isNotNull();
                    assertThat(result)
                            .extracting("shopName", "shopStatus")
                            .containsExactly("우빈이네카페", CLOSE);
                })
                .verifyComplete();
    }

    @DisplayName("shopNumber로 Shop을 단건 조회한다.")
    @Test
    void findById() {
        // given
        Shop shop = createShop("덕수네공방", PREPARING);
        Mono<Shop> shopMono = shopReactiveRepository.save(shop);
        Shop savedShop = shopMono.block();

        Long shopNumber = Objects.requireNonNull(savedShop).getShopNumber();

        // when
        Mono<Shop> foundShop = shopReactiveRepository.findById(shopNumber);

        // then
        StepVerifier.create(foundShop)
                .assertNext(result -> assertThat(result)
                        .extracting("shopNumber", "shopName", "shopStatus")
                        .containsExactly(shopNumber, "덕수네공방", PREPARING)
                )
                .verifyComplete();
    }

    private Shop createShop(String shopName, ShopStatus shopStatus) {
        return Shop.builder()
                .shopName(shopName)
                .shopStatus(shopStatus)
                .build();
    }

}

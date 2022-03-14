package reactive.shopdisplay.domain.shop;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.assertThat;
import static reactive.shopdisplay.domain.shop.ShopStatus.PREPARING;

@SpringBootTest
class ShopInMemoryRepositoryTest {

    @Autowired
    private ShopReactiveRepository shopReactiveRepository;

    @DisplayName("Shop 단건 저장 및 shopNumber 발급이 된다.")
    @Test
    void save() {
        // given
        Shop shop = Shop.builder()
                .shopName("덕수네공방")
                .shopStatus(PREPARING)
                .build();

        // when
        Mono<Shop> shopMono = shopReactiveRepository.save(shop);

        // then
        StepVerifier.create(shopMono)
                .assertNext(savedShop -> assertThat(savedShop)
                        .extracting("shopNumber", "shopName", "shopStatus")
                        .containsExactly(1L, "덕수네공방", PREPARING)
                )
                .verifyComplete();
    }

}

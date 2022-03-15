package reactive.shopdisplay.domain.statistics;

import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

@Getter
public class ShopStatistics {

    private Long id;
    private long reviewCount;
    private Long shopNumber;

    @Builder
    private ShopStatistics(long reviewCount, Long shopNumber) {
        this.reviewCount = reviewCount;
        this.shopNumber = shopNumber;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isSameShopNumber(Long shopNumber) {
        return Objects.equals(this.shopNumber, shopNumber);
    }

}

## 초미니 가게노출시스템

### 목적

Webflux와 Reactor 맛보기

### 요구사항

1. 가게 번호로 가게 상세 데이터를 조회하는 API를 구현한다.
2. 가게 데이터, 통계 데이터, 광고 데이터는 각각 서로 다른 Reactive Repo에 저장되어있다.

- 가게 번호로 조회시 가게 Repo, 통계 Repo, 광고 Repo에 대한 요청을 모두 보내고, 가게 데이터, 통계 데이터, 광고 데이터를 모두 모아서 가게 상세 정보를 되돌려준다.
- 웹플럭스 + in memory Repo (직접 구현한 Reactive Repo) 이용해서
- id로 단건, 다건 조회 API 하나씩

```java
public interface ShopReactiveRepository {
    Mono<Shop> findById(Long shopNumber);
    Flux<Shop> findAllByIdsAndShopStatus(List<Long> shopNumbers, ShopStatus shopStatus);

    Mono<Shop> save(Shop shop);
    Flux<Shop> saveAll(List<Shop> shop);
}
```

- Repository는 unique하게 내부적으로 맵으로 관리되도록 inmemory로 구현

```java
public class Shop {
    private Long shopNumber;
    private String shopName;
    private ShopStatus shopStatus;
}
```

- 가게 단건, 다건 조회시에 ShopStatus가 OPEN인 것만 조회 가능하도록
    - 단건에서 OPEN 아닌 가게 조회하면 에러
- 도메인 로직, Repository 구현체에 대한 CR 테스트 모두 이루어져야 한다.

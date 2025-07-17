# spring-gift-enhancement

| method | url                   | 기능       |
|--------|-----------------------|----------|
| GET    | /api/products         | 상품 전체 조회 |
| POST   | /api/products         | 상품 생성    |
| PATCH  | /api/products/{id}    | 상품 수정    |
| DELETE | /api/products/{id}    | 상품 삭제    |
| GET    | /admin                | 관리자 페이지  |
| POST   | /api/members/register | 회원가입     |
| POST   | /api/members/login    | 로그인      |

step 1. 엔티티 매핑

- 기능요구 사항
  + 지금까지 작성한 JdbcTemplate 기반 코드를 JPA로 리팩터링하고 실제 도메인 모델을 어떻게 구성하고 객체와 테이블을 어떻게 매핑해야 하는지 알아본다.
    - 아래의 DDL(Data Definition Language)을 보고 유추하여 엔티티 클래스와 리포지토리 클래스를 작성해 본다.
    - 객체의 참조와 테이블의 외래 키를 매핑해서 객체에서는 참조를 사용하고 테이블에서는 외래 키를 사용할 수 있도록 한다.
    - @DataJpaTest를 사용하여 학습 테스트를 해 본다.

1. Member entity,repository 클래스를 JPA로 리팩토링
2. Product entity,repository 클래스를 JPA로 리팩토링
3. WishList entity,repository 클래스를 JPA로 리팩토링
4. @DataJpaTest를 사용하여 WishListRepositoryTest

step 2. 페이지네이션

1. 위시리스트를 페이지네이션을 사용하여 조회
2. 상품리스트를 페이지네이션을 사용하여 조회

step 1. 피드백
1. ResponseStatusException의 status code를 항상404으로 반환한걸 수정
2. Optional 타입의 null 타입확인시 중복되는 코드 메소드화

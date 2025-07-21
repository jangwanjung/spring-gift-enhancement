package gift;

import gift.dto.OptionRequestDto;
import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.entity.Option;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductControllerTest {

    @LocalServerPort
    private int port;

    private RestClient client = RestClient.builder().build();


    @Test
    void 상품생성시_정상입력되면_200이_반환된다(){
        String url = "http://localhost:" + port + "/api/products";
        ProductRequestDto productRequestDto = new ProductRequestDto();
        productRequestDto.setName("상품명&+");
        productRequestDto.setPrice(1000L);
        productRequestDto.setImageUrl("image.jpg");

        OptionRequestDto optionRequestDto1 = new OptionRequestDto();
        optionRequestDto1.setName("옵션이름1");
        optionRequestDto1.setQuantity(1000);

        OptionRequestDto optionRequestDto2 = new OptionRequestDto();
        optionRequestDto2.setName("옵션이름2");
        optionRequestDto2.setQuantity(2000);

        List<OptionRequestDto> options = new ArrayList<>();
        options.add(optionRequestDto1);
        options.add(optionRequestDto2);

        productRequestDto.setOptions(options);

        var response = client.post()
                .uri(url)
                .contentType(MediaType.APPLICATION_JSON)
                .body(productRequestDto)
                .retrieve()
                .toEntity(ProductResponseDto.class);
        assert(response.getStatusCode() == HttpStatus.OK);
    }

    @Test
    void 상품생성시_협의된상품에_카카오가들어가면_200이_반환된다(){
        String url = "http://localhost:" + port + "/api/products";
        ProductRequestDto productRequestDto = new ProductRequestDto();
        productRequestDto.setName("상품명은카카오");
        productRequestDto.setPrice(1000L);
        productRequestDto.setImageUrl("image.jpg");
        productRequestDto.setKakaoWordAllow(true);

        OptionRequestDto optionRequestDto1 = new OptionRequestDto();
        optionRequestDto1.setName("옵션이름1");
        optionRequestDto1.setQuantity(1000);

        OptionRequestDto optionRequestDto2 = new OptionRequestDto();
        optionRequestDto2.setName("옵션이름2");
        optionRequestDto2.setQuantity(2000);

        List<OptionRequestDto> options = new ArrayList<>();
        options.add(optionRequestDto1);
        options.add(optionRequestDto2);

        productRequestDto.setOptions(options);

        var response = client.post()
                .uri(url)
                .contentType(MediaType.APPLICATION_JSON)
                .body(productRequestDto)
                .retrieve()
                .toEntity(ProductResponseDto.class);
        assert(response.getStatusCode() == HttpStatus.OK);
    }

    @Test
    void 상품생성시_이름을_입력하지않으면_400이_반환된다(){
        String url = "http://localhost:" + port + "/api/products";
        ProductRequestDto productRequestDto = new ProductRequestDto();
        productRequestDto.setName("");
        productRequestDto.setPrice(1000L);
        productRequestDto.setImageUrl("image.jpg");

        assertThatExceptionOfType(HttpClientErrorException.class)
                .isThrownBy(
                        () -> client.post()
                                .uri(url)
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(productRequestDto)
                                .retrieve()
                                .toEntity(ProductResponseDto.class)

                );
    }

    @Test
    void 상품생성시_협의하지않은상품에_카카오가들어가면_400이_반환된다(){
        String url = "http://localhost:" + port + "/api/products";
        ProductRequestDto productRequestDto = new ProductRequestDto();
        productRequestDto.setName("상품명은카카오");
        productRequestDto.setPrice(1000L);
        productRequestDto.setImageUrl("image.jpg");

        assertThatExceptionOfType(HttpClientErrorException.class)
                .isThrownBy(
                        () -> client.post()
                                .uri(url)
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(productRequestDto)
                                .retrieve()
                                .toEntity(ProductResponseDto.class)

                );
    }

    @Test
    void 상품생성시_허용되지않은_특수문자가들어가면_400이_반환된다(){
        String url = "http://localhost:" + port + "/api/products";
        ProductRequestDto productRequestDto = new ProductRequestDto();
        productRequestDto.setName("상품명은허용되지않은?");
        productRequestDto.setPrice(1000L);
        productRequestDto.setImageUrl("image.jpg");

        assertThatExceptionOfType(HttpClientErrorException.class)
                .isThrownBy(
                        () -> client.post()
                                .uri(url)
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(productRequestDto)
                                .retrieve()
                                .toEntity(ProductResponseDto.class)

                );
    }

    @Test
    void 상품생성시_가격에_음수가들어가면_400이_반환된다(){
        String url = "http://localhost:" + port + "/api/products";
        ProductRequestDto productRequestDto = new ProductRequestDto();
        productRequestDto.setName("상품명은 상품명?");
        productRequestDto.setPrice(-1000L);
        productRequestDto.setImageUrl("image.jpg");

        assertThatExceptionOfType(HttpClientErrorException.class)
                .isThrownBy(
                        () -> client.post()
                                .uri(url)
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(productRequestDto)
                                .retrieve()
                                .toEntity(ProductResponseDto.class)

                );
    }

}

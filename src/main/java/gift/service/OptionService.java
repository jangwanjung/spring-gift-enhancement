package gift.service;

import gift.dto.OptionRequestDto;
import gift.entity.Product;

import java.util.List;

public interface OptionService {

    void saveOptions(Product product, List<OptionRequestDto> optionRequestDtoList);

    void checkDuplicatedOptionName(List<OptionRequestDto> optionRequestDtoList);
}

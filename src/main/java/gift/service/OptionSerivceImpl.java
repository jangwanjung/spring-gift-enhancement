package gift.service;

import gift.dto.OptionRequestDto;
import gift.entity.Option;
import gift.entity.Product;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OptionSerivceImpl implements OptionService{

    @Override
    @Transactional
    public void saveOptions(Product product, List<OptionRequestDto> optionRequestDtoList) {
        checkDuplicatedOptionName(optionRequestDtoList);

        for (OptionRequestDto optionRequestDto : optionRequestDtoList) {
            product.saveOption(optionRequestDto.toEntity());
        }

    }

    @Override
    public void checkDuplicatedOptionName(List<OptionRequestDto> optionRequestDtoList) {
        Set<String> optionNames = optionRequestDtoList.stream()
                .map(OptionRequestDto::getName)
                .collect(Collectors.toSet());

        if (optionNames.size() != optionRequestDtoList.size()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "상품의 옵션이름이 중복이 될 수 없습니다.");
        }
    }
}

package gift.dto;

public class OptionResponseDto {

    private Long id;
    private String name;
    private Integer quantity;

    public OptionResponseDto(Long id, String name, Integer quantity) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
    }
}

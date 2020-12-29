import lombok.Data;

@Data
public class CategoryModel {

    private Long id;
    private Long parentId;

    private  String name;
    private Integer level;
}

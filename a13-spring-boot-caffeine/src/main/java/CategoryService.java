import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    //category是缓存名称,#type是具体的key，可支持el表达式
    @Cacheable(value = "category", key = "#type")
    public CategoryModel getCategory(Integer type) {
        return getCategoryByType(type);
    }

    private CategoryModel getCategoryByType(Integer type) {
        System.out.println("根据不同的type:" + type + "获取不同的分类数据");
        CategoryModel categoryModel = new CategoryModel();
        categoryModel.setId(1L);
        categoryModel.setParentId(0L);
        categoryModel.setName("电器");
        categoryModel.setLevel(3);
        return categoryModel;
    }
}


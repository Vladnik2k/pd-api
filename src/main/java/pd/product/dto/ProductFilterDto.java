package pd.product.dto;

import java.util.List;

public class ProductFilterDto {
    private List<Integer> categoryIds;
    private String searchText;

    public ProductFilterDto(List<Integer> categoryIds, String searchText) {
        this.categoryIds = categoryIds;
        this.searchText = searchText;
    }

    public List<Integer> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(List<Integer> categoryIds) {
        this.categoryIds = categoryIds;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }
}

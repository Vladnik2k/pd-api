package pd.product.dto;

import java.util.List;

public class ProductFilterDto {
    private List<Integer> categoryIds;
    private String searchText;
    private Double maxPrice;
    private Double minPrice;

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

    public Double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }
}

package pd.category.dto;

import pd.category.Category;

public class CategoryDto {
    private int id;
    private String name;

    public CategoryDto() {
    }

    public CategoryDto(Category category) {
        this.id = category.getId();
        this.name = category.getName();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

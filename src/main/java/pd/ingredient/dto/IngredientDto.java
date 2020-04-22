package pd.ingredient.dto;

import pd.ingredient.Ingredient;
import pd.unit.dto.UnitDto;

public class IngredientDto {
    private int id;
    private String name;
    private UnitDto unit;

    public IngredientDto(Ingredient ingredient) {
        this.id = ingredient.getId();
        this.name = ingredient.getName();
        this.unit = new UnitDto(ingredient.getUnit());
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

    public UnitDto getUnit() {
        return unit;
    }

    public void setUnit(UnitDto unit) {
        this.unit = unit;
    }
}

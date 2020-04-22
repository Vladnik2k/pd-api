package pd.unit.dto;

import pd.unit.Unit;

public class UnitDto {
    private int id;
    private String name;
    private String abbreviation;

    public UnitDto(Unit unit) {
        this.id = unit.getId();
        this.name = unit.getName();
        this.abbreviation = unit.getAbbreviation();
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

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }
}

package pd.product;

import org.springframework.jdbc.core.RowMapper;
import pd.product.dto.ProductDto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDtoMapping implements RowMapper<ProductDto> {

    /**
     * Converting got product from database to frontend-view object
     * @param resultSet {@link ResultSet} got information from database
     * @param i {@link int} index of element
     * @return {@link ProductDto} frontend-view object
     * @throws SQLException {@link SQLException} if something went wrong
     */
    @Override
    public ProductDto mapRow(ResultSet resultSet, int i) throws SQLException {
        ProductDto productDto = new ProductDto();

        productDto.setId(resultSet.getInt("id"));
        productDto.setDescription(resultSet.getString("description"));
        productDto.setDiscountPercent(resultSet.getDouble("discount_percent"));
        productDto.setImageUrl(resultSet.getString("image_url"));
        productDto.setName(resultSet.getString("name"));
        productDto.setPrice(resultSet.getInt("price"));
        return productDto;
    }
}

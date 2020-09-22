package pd.product;

import java.util.List;

class ProductFormQueryLine {
    private ProductFormQueryLine() {}

    /**
     * Create query for getting all products by filter using different fields
     * @param categoryIds {@link List<Integer>} categories in which all found products should be
     * @param searchName {@link String} small part (or all) of product's name
     * @param minPrice {@link Double} min price of product
     * @param maxPrice {@link Double} max price of product
     * @return {@link String} generated query
     */
    static String getAllByFilter(List<Integer> categoryIds, String searchName, Double minPrice, Double maxPrice) {
        return "SELECT product.id, product.name, product.description, product.category_id, product.image_url," +
                "       IF(discount.percent IS NULL, NULL, discount.percent) AS discount_percent," +
                "       IF(discount.percent IS NULL, price, price - price / 100 * discount.percent) AS price" +
                "    FROM product" +
                "        LEFT JOIN discount ON discount.product_id = product.id" +
                "            AND discount.activate_by >= CURRENT_TIMESTAMP" +
                "            AND discount.activate_from <= CURRENT_TIMESTAMP" +
                "    WHERE product.category_id IN " + listToString(categoryIds) + " AND product.name LIKE \'%" + searchName + "%\'" +
                "    HAVING price BETWEEN " + minPrice + " AND " + maxPrice +
                "    ORDER BY product.id";
    }

    /**
     * Create query for getting all products by their ids
     * @param ids {@link List<Integer>} list of products ids
     * @return {@link String} generated query
     */
    static String getAllByIds(List<Integer> ids) {
        return "SELECT product.id, product.name, product.description, product.category_id, product.image_url," +
                "       IF(discount.percent IS NULL, NULL, discount.percent) AS discount_percent," +
                "       IF(discount.percent IS NULL, price, price - price / 100 * discount.percent) AS price" +
                "    FROM product" +
                "        LEFT JOIN discount ON discount.product_id = product.id" +
                "            AND discount.activate_by >= CURRENT_TIMESTAMP" +
                "            AND discount.activate_from <= CURRENT_TIMESTAMP" +
                "    WHERE product.category_id IN " + listToString(ids);
    }

    /**
     * Create query for getting all product with discount
     * @return {@link String} generated query
     */
    static String getAllWithDiscount() {
        return "SELECT product.id, product.name, product.description, product.category_id, product.image_url," +
                "       IF(discount.percent IS NULL, NULL, discount.percent) AS discount_percent," +
                "       IF(discount.percent IS NULL, price, price - price / 100 * discount.percent) AS price" +
                "    FROM product" +
                "        INNER JOIN discount ON discount.product_id = product.id" +
                "            AND discount.activate_by >= CURRENT_TIMESTAMP" +
                "            AND discount.activate_from <= CURRENT_TIMESTAMP";
    }

    /**
     * Convert list to string for using in queries
     * @param list {@link List<Integer>} list needed to be converted to string
     * @return {@link String} generated string
     */
    private static String listToString(List<Integer> list) {
        return list.toString().replace("[", "(").replace("]", ")");
    }
}

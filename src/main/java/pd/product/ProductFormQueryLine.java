package pd.product;

import java.util.List;

class ProductFormQueryLine {
    private ProductFormQueryLine() {}

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

    static String getAllWithDiscount() {
        return "SELECT product.id, product.name, product.description, product.category_id, product.image_url," +
                "       IF(discount.percent IS NULL, NULL, discount.percent) AS discount_percent," +
                "       IF(discount.percent IS NULL, price, price - price / 100 * discount.percent) AS price" +
                "    FROM product" +
                "        INNER JOIN discount ON discount.product_id = product.id" +
                "            AND discount.activate_by >= CURRENT_TIMESTAMP" +
                "            AND discount.activate_from <= CURRENT_TIMESTAMP";
    }

    private static String listToString(List<Integer> list) {
        return list.toString().replace("[", "(").replace("]", ")");
    }
}

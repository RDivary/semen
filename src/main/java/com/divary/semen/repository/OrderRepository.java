package com.divary.semen.repository;

import com.divary.semen.entity.Order;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The OrderRepository interface extends JpaRepository and manages database operations related to Order entities.
 */
@Repository
public interface OrderRepository extends JpaRepository <Order, Integer> {

    /**
     * Retrieves sales report data including order ID, date, store name, and total price within a limit.
     *
     * @param limit The limit for the number of records to retrieve.
     * @return A list of objects containing sales report data.
     */
    @Query("SELECT o.id as order_id, o.date, s.name as store, subquery.total_price as total_price " +
            "FROM Order o " +
            "JOIN Store s ON o.store.id = s.id " +
            "JOIN ( " +
            "    SELECT od.order.id as order_id, SUM(od.totalPrice) AS total_price " +
            "    FROM OrderDetail od " +
            "    GROUP BY od.order.id " +
            ") subquery ON o.id = subquery.order_id ")
    List<Object> getReportPenjualan(Limit limit);

    /**
     * Retrieves sales report data greater than a specific order ID within a limit.
     *
     * @param orderId The order ID as a threshold for data retrieval.
     * @param limit   The limit for the number of records to retrieve.
     * @return A list of objects containing sales report data greater than the specified order ID.
     */
    @Query("SELECT o.id as order_id, o.date, s.name as store, subquery.total_price as total_price " +
            "FROM Order o " +
            "JOIN Store s ON o.store.id = s.id " +
            "JOIN ( " +
            "    SELECT od.order.id as order_id, SUM(od.totalPrice) AS total_price " +
            "    FROM OrderDetail od " +
            "    GROUP BY od.order.id " +
            ") subquery ON o.id = subquery.order_id " +
            "WHERE o.id < :orderId ")
    List<Object> getReportPenjualanGreaterThanOrderId(int orderId, Limit limit);
}

package com.crm.marchbatch.repositories;

import com.crm.marchbatch.model.Order;
import com.crm.marchbatch.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.*;
@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    public List<Order> findByProduct(Product product);
    public List<Order> findByProductNameContaining(String str);
    public List<Order> findByProductNameStartsWith(String str);
    public List<Order> findByProductNameEndsWith(String str);

    @Query("select o from Order o WHERE o.id = ?1")
    public Optional<Order> findById(Long id);

    @Query(value = "select * from Order o WHERE o.orderName LIKE %:str%",nativeQuery = true)
    public List<Order> findByStartsWithString(@Param("str") String str);

    @Query("select o from Order o WHERE o.orderName=?1 and o.id=?2")
    public List<Order> findByName(String orderName,Long id);

//    @Query

}
//@Query -> jpql

// where product like %a%
//@NativeQuery -> sql
//@NamedQuery -> java


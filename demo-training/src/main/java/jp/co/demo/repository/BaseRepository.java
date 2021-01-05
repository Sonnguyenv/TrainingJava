package jp.co.demo.repository;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.types.dsl.StringTemplate;
import jp.co.demo.enums.TypeCompare;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * BaseRepository
 */
@Repository
public class BaseRepository {

    protected OrderSpecifier[] getOrderSpecifiers(@NotNull Pageable pageable, @NotNull Class className) {
        String tableName = className.getSimpleName();
        final String orderVariable = String.valueOf(Character.toLowerCase(tableName.charAt(0))).concat(tableName.substring(1));

        return pageable.getSort().stream()
                .map(order -> new OrderSpecifier(
                        Order.valueOf(order.getDirection().toString()),
                        new PathBuilder(className, orderVariable).get(order.getProperty()))
                )
                .toArray(OrderSpecifier[]::new);
    }

    protected BooleanExpression dateCompare(DateTimePath<Date> dateTimePath, Date date, TypeCompare typeCompare) {
        StringTemplate dbDate = Expressions.stringTemplate("DATE({0})", dateTimePath);
        StringTemplate compareDate = Expressions.stringTemplate("DATE({0})", date);
        dbDate.between(compareDate, compareDate);

        switch (typeCompare) {
            case GT:
                return dbDate.gt(compareDate);
            case GOE:
                return dbDate.goe(compareDate);
            case LT:
                return dbDate.lt(compareDate);
            case LOE:
                return dbDate.loe(compareDate);
            default:
                return dbDate.eq(compareDate);
        }
    }

    protected BooleanExpression dateBetween(DateTimePath<Date> dateTimePath, Date from, Date to) {
        StringTemplate dbDate = Expressions.stringTemplate("DATE({0})", dateTimePath);
        StringTemplate fromDate = Expressions.stringTemplate("DATE({0})", from);
        StringTemplate toDate = Expressions.stringTemplate("DATE({0})", to);
        return dbDate.between(fromDate, toDate);
    }
}

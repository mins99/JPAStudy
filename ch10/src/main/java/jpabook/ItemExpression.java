package jpabook;

import com.mysema.query.annotations.QueryDelegate;
import com.mysema.query.types.expr.BooleanExpression;
import com.mysema.query.types.path.StringPath;

public class ItemExpression {

    @QueryDelegate(Item.class)
    public static BooleanExpression isExpensive(QItem item, Integer price) {
        return item.price.gt(price);
    }

    @QueryDelegate(String.class)
    public static BooleanExpression isHelloStart(StringPath stringPath) {
        return stringPath.startsWith("Hello");
    }
}

package org.spring.jdbc.tutorials.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QCustomer is a Querydsl query type for QCustomer
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QCustomer extends com.mysema.query.sql.RelationalPathBase<QCustomer> {

    private static final long serialVersionUID = -653977867;

    public static final QCustomer customer = new QCustomer("customer");

    public final NumberPath<Integer> age = createNumber("AGE", Integer.class);

    public final NumberPath<Integer> custId = createNumber("CUST_ID", Integer.class);

    public final StringPath name = createString("NAME");

    public final com.mysema.query.sql.PrimaryKey<QCustomer> primary = createPrimaryKey(custId);

    public final com.mysema.query.sql.ForeignKey<QAddress> _addressCustomerRef = createInvForeignKey(custId, "cust_id");

    public QCustomer(String variable) {
        super(QCustomer.class, forVariable(variable), "null", "customer");
    }

    @SuppressWarnings("all")
    public QCustomer(Path<? extends QCustomer> path) {
        super((Class)path.getType(), path.getMetadata(), "null", "customer");
    }

    public QCustomer(PathMetadata<?> metadata) {
        super(QCustomer.class, metadata, "null", "customer");
    }

}


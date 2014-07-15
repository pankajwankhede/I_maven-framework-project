package org.spring.jdbc.tutorials.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QAddress is a Querydsl query type for QAddress
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QAddress extends com.mysema.query.sql.RelationalPathBase<QAddress> {

    private static final long serialVersionUID = -1326889475;

    public static final QAddress address = new QAddress("address");

    public final StringPath city = createString("city");

    public final NumberPath<Integer> custId = createNumber("cust_id", Integer.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath street = createString("street");

    public final com.mysema.query.sql.PrimaryKey<QAddress> primary = createPrimaryKey(id);

    public final com.mysema.query.sql.ForeignKey<QCustomer> addressCustomerRef = createForeignKey(custId, "CUST_ID");

    public QAddress(String variable) {
        super(QAddress.class, forVariable(variable), "null", "address");
    }

    @SuppressWarnings("all")
    public QAddress(Path<? extends QAddress> path) {
        super((Class)path.getType(), path.getMetadata(), "null", "address");
    }

    public QAddress(PathMetadata<?> metadata) {
        super(QAddress.class, metadata, "null", "address");
    }

}


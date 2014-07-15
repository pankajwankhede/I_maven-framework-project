package org.spring.jdbc.tutorials.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QEmployee is a Querydsl query type for QEmployee
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QEmployee extends com.mysema.query.sql.RelationalPathBase<QEmployee> {

    private static final long serialVersionUID = -66683451;

    public static final QEmployee employee = new QEmployee("employee");

    public final NumberPath<Integer> age = createNumber("AGE", Integer.class);

    public final NumberPath<Integer> id = createNumber("ID", Integer.class);

    public final StringPath name = createString("NAME");

    public final com.mysema.query.sql.PrimaryKey<QEmployee> primary = createPrimaryKey(id);

    public QEmployee(String variable) {
        super(QEmployee.class, forVariable(variable), "null", "employee");
    }

    @SuppressWarnings("all")
    public QEmployee(Path<? extends QEmployee> path) {
        super((Class)path.getType(), path.getMetadata(), "null", "employee");
    }

    public QEmployee(PathMetadata<?> metadata) {
        super(QEmployee.class, metadata, "null", "employee");
    }

}


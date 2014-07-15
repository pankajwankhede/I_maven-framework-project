package org.spring.jdbc.tutorials.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QContact is a Querydsl query type for QContact
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QContact extends com.mysema.query.sql.RelationalPathBase<QContact> {

    private static final long serialVersionUID = 772329001;

    public static final QContact contact = new QContact("contact");

    public final StringPath address = createString("address");

    public final NumberPath<Integer> contactId = createNumber("contact_id", Integer.class);

    public final StringPath email = createString("email");

    public final StringPath name = createString("name");

    public final StringPath telephone = createString("telephone");

    public final com.mysema.query.sql.PrimaryKey<QContact> primary = createPrimaryKey(contactId);

    public QContact(String variable) {
        super(QContact.class, forVariable(variable), "null", "contact");
    }

    @SuppressWarnings("all")
    public QContact(Path<? extends QContact> path) {
        super((Class)path.getType(), path.getMetadata(), "null", "contact");
    }

    public QContact(PathMetadata<?> metadata) {
        super(QContact.class, metadata, "null", "contact");
    }

}


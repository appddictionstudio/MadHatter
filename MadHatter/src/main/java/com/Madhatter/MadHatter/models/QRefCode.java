package com.Madhatter.MadHatter.models;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRefCode is a Querydsl query type for RefCode
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QRefCode extends EntityPathBase<RefCode> {

    private static final long serialVersionUID = 1015265352L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRefCode refCode = new QRefCode("refCode");

//    public final QAuditable _super = new QAuditable(this);

    public final StringPath cidneNm = createString("cidneNm");

    public final StringPath createdBy = createString("createdBy");

    //inherited
//    public final DateTimePath<java.util.Date> creationDate = _super.creationDate;

    public final StringPath customCategory = createString("customCategory");

    public final StringPath dataType = createString("dataType");

    public final StringPath descr = createString("descr");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath lastModifiedBy = createString("lastModifiedBy");

    //inherited
//    public final DateTimePath<java.util.Date> lastModifiedDate = _super.lastModifiedDate;

    public final StringPath maxSize = createString("maxSize");

    public final StringPath nm = createString("nm");

    public final StringPath obsoleteYn = createString("obsoleteYn");

    public final NumberPath<Long> parentId = createNumber("parentId", Long.class);

    public final QRefCode parentRefCode;

    public final StringPath permitAddChildrenYn = createString("permitAddChildrenYn");

    public final StringPath regExp = createString("regExp");

    public final NumberPath<java.math.BigDecimal> sortOrder = createNumber("sortOrder", java.math.BigDecimal.class);

    public QRefCode(String variable) {
        this(RefCode.class, forVariable(variable), INITS);
    }

    public QRefCode(Path<? extends RefCode> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRefCode(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRefCode(PathMetadata metadata, PathInits inits) {
        this(RefCode.class, metadata, inits);
    }

    public QRefCode(Class<? extends RefCode> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.parentRefCode = inits.isInitialized("parentRefCode") ? new QRefCode(forProperty("parentRefCode"), inits.get("parentRefCode")) : null;
    }

}


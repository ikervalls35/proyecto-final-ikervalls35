package com.tienda.config;

import org.hibernate.dialect.Dialect;
import org.hibernate.engine.jdbc.env.spi.NameQualifierSupport;

public class SQLiteDialect extends Dialect {


    public boolean supportsIdentityColumns() {
        return true;
    }


    public String getIdentityColumnString(int type) {
        return "integer";
    }


    public String getIdentitySelectString() {
        return "select last_insert_rowid()";
    }

    @Override
    public NameQualifierSupport getNameQualifierSupport() {
        return NameQualifierSupport.NONE;
    }
}
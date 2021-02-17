package com.systemk.ams.Config.MultiDataBase;

import org.hibernate.dialect.SQLServerDialect;
import org.hibernate.type.StringType;
import static java.sql.Types.NVARCHAR;
 
/**
 * NVARCHAR 타입에 대한 Mapping Custom
 * 
 */
public class CustomSQLServerDialect extends SQLServerDialect{

	public CustomSQLServerDialect(){
		super();
		registerHibernateType(NVARCHAR, StringType.INSTANCE.getName());
	}
}

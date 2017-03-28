package com.mall.common.dao;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;

import org.springframework.jdbc.core.RowMapper;

@SuppressWarnings("rawtypes")
public class EntityMapper implements RowMapper {
	private Class<?> entityClass;

	public EntityMapper(Class<?> entityClass) {
		this.entityClass = entityClass;
	}

	@SuppressWarnings("unchecked")
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		Object obj = null;

		List colName = new ArrayList();
		ResultSetMetaData meta = rs.getMetaData();
		for (int i = 0; i < meta.getColumnCount(); ++i) {
			colName.add(meta.getColumnLabel(i + 1).toUpperCase());
		}
		try {
			obj = this.entityClass.newInstance();

			Field[] fields = this.entityClass.getDeclaredFields();
			for (Field field : fields)
				if (field.isAnnotationPresent(Column.class)) {
					Column col = (Column) field.getAnnotation(Column.class);
					if (colName.contains(col.name().toUpperCase()))
						setObject(obj, rs, field, col.name());
				}
		} catch (Exception e) {
			throw new SQLException(e);
		}
		return obj;
	}

	private static void setObject(Object obj, ResultSet rs, Field filed,
			String colName) throws Exception {
		if (rs.getObject(colName) == null) {
			return;
		}
		Class filedType = filed.getType();

		filed.setAccessible(true);
		if (filedType.equals(String.class))
			filed.set(obj, rs.getString(colName));
		else if ((filedType.equals(Long.class))
				|| (filedType.equals(Long.TYPE)))
			filed.set(obj, Long.valueOf(rs.getLong(colName)));
		else if ((filedType.equals(Date.class))
				&& (rs.getTimestamp(colName) != null))
			filed.set(obj, new Date(rs.getTimestamp(colName).getTime()));
		else if ((filedType.equals(Double.class))
				|| (filedType.equals(Double.TYPE)))
			filed.set(obj, Double.valueOf(rs.getDouble(colName)));
		else if ((filedType.equals(Float.class))
				|| (filedType.equals(Float.TYPE)))
			filed.set(obj, Float.valueOf(rs.getFloat(colName)));
		else if ((filedType.equals(Short.class))
				|| (filedType.equals(Short.TYPE)))
			filed.set(obj, Short.valueOf(rs.getShort(colName)));
		else if ((filedType.equals(Integer.class))
				|| (filedType.equals(Integer.TYPE)))
			filed.set(obj, Integer.valueOf(rs.getInt(colName)));
		else if (filedType.equals(BigDecimal.class))
			filed.set(obj, rs.getBigDecimal(colName));
		else
			filed.set(obj, rs.getObject(colName));
	}
}

package com.rohit.util;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import com.rohit.exception.SORuntimeException;

public class SchedulerIdGenerator implements IdentifierGenerator {

	private static final String GET_SCHEDULER_ID = "SELECT GEN_ID ('SCH') FROM DUAL";

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		// TODO Auto-generated method stub

		String schedulerId = null;
		Connection connection = null;
		Statement stmt = null;
		// fixed for "Improper Resource Shutdown or Release"
		try {

			connection = session.connection();
			stmt = connection.createStatement();
			ResultSet rows = stmt.executeQuery(GET_SCHEDULER_ID);
			if (rows.next()) {
				schedulerId = rows.getString(1);
			}

		} catch (SQLException sqlException) {
			throw new SORuntimeException("Exception occured while generating scheduler id");
		}

		return schedulerId;
	}

}

package gestionParcInfo.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;

public abstract class Repository {
	protected PreparedStatement pstmt;
	protected Connection conn;
	
	public Repository(Connection conn) {
		this.conn = conn;
	}
}

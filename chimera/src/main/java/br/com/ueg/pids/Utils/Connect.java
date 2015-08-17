package br.com.ueg.pids.Utils;



import java.sql.Connection;
import java.sql.DriverManager;

public class Connect {

	public static Connection fabricar() throws Exception {
		Class.forName("org.postgresql.Driver").newInstance();

		System.out.println("Conexão aberta!");

		return DriverManager.getConnection(
				"jdbc:postgresql://localhost:5432/chimera", "postgres",
				"postgres");

	}

}
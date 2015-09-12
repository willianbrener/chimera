package br.com.ueg.pids.DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.ueg.pids.Enum.TypeMessage;
import br.com.ueg.pids.Model.Cargo;
import br.com.ueg.pids.Utils.Return;


public class CargoDAO extends GenericDAO{
	private Connection con;

	public CargoDAO(Connection con) {
		this.con = con;
	}

	
}

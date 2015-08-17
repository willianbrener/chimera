package br.com.ueg.pids.DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.ueg.pids.Enum.TypeMessage;
import br.com.ueg.pids.Model.Cargo;
import br.com.ueg.pids.Utils.Return;


public class CargoDAO {
	private Connection con;

	public CargoDAO(Connection con) {
		this.con = con;
	}

	public Return cadastrar(Cargo cargo) throws Exception {
		PreparedStatement p = con.prepareStatement("insert into Cargo (name, descricao, departamento,status) values (?,?,?,?)");
		p.setString(1, cargo.getName());
		p.setString(2, cargo.getDescricao());
		p.setString(3, cargo.getDepartamento());
		p.setBoolean(4, true);
		p.executeUpdate();
		p.close();
		return new Return(true, "Salvo com sucesso", TypeMessage.SUCESSO);
	}

	public void deletar(Cargo cargo) throws Exception {
		PreparedStatement p = con.prepareStatement("delete from cargo where id = ?");
		p.setInt(1, cargo.getId());
		p.executeUpdate();
		p.close();
	}

	public void update(Cargo cargo) throws Exception {
		PreparedStatement p = con
				.prepareStatement("update cargo set name = ?, descricao = ?, departamento =?,status = ? where id = ?");
		p.setString(1, cargo.getName());
		p.setString(2, cargo.getDescricao());
		p.setString(3, cargo.getDepartamento());
		p.setBoolean(4, true);
		p.setInt(5, cargo.getId());
		p.executeUpdate();
		p.close();
	}

	public List<Cargo> listarTodos() throws Exception {
		List<Cargo> cargos = new ArrayList<Cargo>();
		PreparedStatement p = con.prepareStatement("select * from cargo order by id");
		ResultSet rs = p.executeQuery();
		while (rs.next()) {
			Cargo cargo = new Cargo();
			cargo.setId(rs.getInt("id"));
			cargo.setName(rs.getString("name"));
			cargo.setDescricao(rs.getString("descricao"));
			cargo.setDepartamento(rs.getString("departamento"));
			cargo.setStatus(rs.getBoolean("status"));
			cargos.add(cargo);

		}
		rs.close();
		p.close();
		return cargos;
	}

	public List<Cargo> Pesquisa(String string1)
			throws Exception {
		List<Cargo> cargos = new ArrayList<Cargo>();
		PreparedStatement p = con.prepareStatement("SELECT * FROM cargo where name='" + string1 + "'");

		ResultSet rs = p.executeQuery();
		while (rs.next()) {
			Cargo cargo = new Cargo();
			cargo.setId(rs.getInt("id"));
			cargo.setName(rs.getString("name"));
			cargo.setDescricao(rs.getString("descricao"));
			cargo.setDepartamento(rs.getString("departamento"));
			cargo.setStatus(rs.getBoolean("status"));
			cargos.add(cargo);

		}
		rs.close();
		p.close();
		return cargos;
	}
}

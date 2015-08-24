package br.com.ueg.pids.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.ueg.pids.Model.Table;


/**
 * @author Guiliano
 *
 */
@SuppressWarnings({"rawtypes","unused"})
public class GenericDAO {
	
	private Connection con = null;
	
	private static GenericDAO dao = null;
	
	public static GenericDAO getInstance(){
		if (dao == null) {
			dao = new GenericDAO();
		}
		return dao;
	}
	
	private GenericDAO(){
		try {
			this.con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/chimera","postgres","postgres");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/** verifica se uma objeto já existe no banco de dados.
	 * @param table
	 * @return
	 */
	public boolean exists(Table<?> table){
		boolean result = false;
		Object object = this.getObject(table);
		if (object != null) {
			result = true;
		}
		return result;
	}
	
	
	
	public <T extends Table> T  getObject(T table){

		String sql = "select "+table.getTableColumnNames()+" from " + table.getTableName() + " ";
		sql = sql + " where " + table.getPKName() + "='" + String.valueOf(table.getPK()) + "'";
		System.out.println("sql:"+sql);
		String strSetValues = "";
		Statement st;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			int colCount = rs.getMetaData().getColumnCount();
			if (rs.next()) {
				HashMap<String,Object> record = new HashMap<String, Object>();
				for (int i = 1; i <= colCount; i++) {
					strSetValues = strSetValues +","+ rs.getString(i)+"";
				}
			}else{
				return null;
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		strSetValues = strSetValues.substring(1);
		
		table.setTableColumnsValues(strSetValues);
		
		return table;
	}
	/** remove um objeto do banco de dados.
	 * @param table
	 * @return
	 */
	public boolean deletar(Table<?> table){
		boolean result = false;
		String sql = "delete from " + table.getTableName() + " ";
		sql = sql + " where " + table.getPKName() + "='" + String.valueOf(table.getPK()) + "'";
		System.out.println("sql:"+sql);
		Statement st;
		
			try {
				st = con.createStatement();
			
				int row = st.executeUpdate(sql);
				if (row>0) {
					result = true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return result;
	}

	
	/** Metodo utilizado para salvar uma tabela no banco de dados.(o objeto deve ser novo)
	 * @param table
	 * @return true caso consiga salvar e falso do contrario
	 */
	public boolean salvar(Table<?> table){
		boolean result = false;
		String cols = GenericDAO.quotedString(table.getTableColumnValues());
		
		String sql = "insert into " + table.getTableName();
			   sql = sql + "(" + table.getTableColumnNames() + ")";			   
			   sql = sql + " values(" + cols + ")";
		
		try {
			Statement st = this.con.createStatement();
			result = st.execute(sql);
			st.close();
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("sql:"+sql);
		
		return result;
	}
	
	/** Metodo utilizado pra atualizar um objeto no banco de dados.
	 * @param table
	 * @return
	 */
	public boolean alterar(Table<?> table){
		boolean result = false;
		
		String sql = "update " + table.getTableName() + " " ;		
			   sql = sql + "set "+GenericDAO.getSetString(table) + " ";
			   sql = sql + "where " + table.getPKName() + "='"+String.valueOf(table.getPK())+"'";
		
	    System.out.println("sql:"+sql);
	    
	    Statement st;
	    int row =0;
		try {
			st = con.createStatement();
			row = st.executeUpdate(sql);
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}	    	    
	    
	    if(row>0){ 
	    	result= true;
	    }
		return result;
	}
	
	public List<HashMap<String, Object>> listarTodos(Table<?> table){
		
		List<HashMap<String, Object>> result = new ArrayList<HashMap<String,Object>>();
		
		
		String sql = "select "+table.getTableColumnNames()+" from "+table.getTableName()+ " ";

		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			int colCount = rs.getMetaData().getColumnCount();
			while(rs.next()){
				HashMap<String,Object> record = new HashMap<String, Object>();
				for (int i = 1; i <= colCount; i++) {
					record.put(rs.getMetaData().getColumnName(i), rs.getString(i));
				}
				result.add(record);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	/** recebe uma string separada por virgula e pega os valores e envolve em aspas simples
	 * @param v
	 * @return
	 */
	private static String quotedString(String v){
		String[] values = v.split(",");
		String retorno ="";
		for (int i = 0; i < values.length; i++) {
			retorno = retorno + ",'" + values[i] + "'";
		}
		retorno = retorno.substring(1);
		return retorno;
	}
	
	
	/** retorna a string de Set utilizada para atualizar as colunas de uma tabela
	 * @param table
	 * @return
	 */
	private static String getSetString(Table<?> table){
		String[] colNames  = table.getTableColumnNames().split(",");
		String[] colValues = table.getTableColumnValues().split(",");
		
		String retorno = "";
		
		for (int i = 0; i < colNames.length; i++) {
			retorno = retorno + ", " + colNames[i] + "='" + colValues[i] + "'";
		}
		return retorno.substring(1);
	}
}
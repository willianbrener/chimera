package br.com.ueg.pids.DAO;


import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import br.com.ueg.pids.Annotations.Campo;
import br.com.ueg.pids.Enum.TypeMessage;
import br.com.ueg.pids.Model.IModel;
import br.com.ueg.pids.Utils.Connect;
import br.com.ueg.pids.Utils.Mensagem;
import br.com.ueg.pids.Utils.Reflection;
import br.com.ueg.pids.Utils.Return;




public class GenericDAO {

	static GenericDAO genericDAO;
	Mensagem message = new Mensagem();
	
	static { genericDAO = null; }
		
	public static GenericDAO obterInstancia(){
			if (genericDAO == null ) {
				genericDAO = new GenericDAO();
			}
			return genericDAO;
		}
	
	public Return inserir(IModel<?> entidade){
		Return res = null;
		
		String auxFieldsName="";
		String auxFieldsValues="";
		Class<?> cls = entidade.getClass();	
		Field[] fld = cls.getDeclaredFields();
		
		try {
			for(int i = 1; i < fld.length; i++){
				Campo cmp = fld[i].getAnnotation(Campo.class);				
				if(cmp!=null){
					if(Reflection.getFieldValue(entidade, fld[i].getName())!=null){
						String str = "";
						if(Reflection.getFieldValue(entidade, fld[i].getName()) instanceof IModel<?>){
							str = "" + (((IModel<?>) Reflection.getFieldValue(entidade, fld[i].getName())).getPK());
						}else{
							str = "" + Reflection.getFieldValue(entidade, fld[i].getName());
						}
						auxFieldsName=auxFieldsName+","+cmp.nome();
						auxFieldsValues=auxFieldsValues+", '"+ str +"'";
					}
				}				
			}
		}catch (Throwable e) {
			System.err.println(e);
			
		}		
		
		
		String sql = "insert into " + entidade.getTableName();
			   sql = sql + "(" + auxFieldsName.substring(1) + ")";			   
			   sql = sql + " values(" + auxFieldsValues.substring(1) + ")";
		
		
		if(Connect.getConexao()){
			int i = Connect.runSQL(sql);
			System.out.println(sql);
			if (i == 1){
				Connect.close();
				return new Return(true,"O "+ entidade.getTableName()+ "foi inserido com sucesso!", TypeMessage.SUCESSO);
				
			}else{
				System.out.println("O " + entidade.getTableName() +"  Não Foi Inserido problema no DAO.inserir");  
				Connect.close();
				return  new Return(false, "Não Foi Inserido", TypeMessage.ERROR);
			}
				
		}
		System.out.println("O " + entidade.getTableName() + "Não Foi Inserido problema no Connect.getConexão()");
		
		return  new Return(false,"Não Foi Inserido problema no Connect.getConexão()", TypeMessage.ERROR);
	}
	
	
	public Return alterar(IModel<?> entidade){
		String auxFields="";
		Class<?> cls = entidade.getClass();	
		Field[] fld = cls.getDeclaredFields();
		
		try {
			for(int i = 0; i < fld.length; i++){
				Campo cmp = fld[i].getAnnotation(Campo.class);				
				if(cmp!=null){
					if(Reflection.getFieldValue(entidade, fld[i].getName())!=null){
						String str = "";
						if(Reflection.getFieldValue(entidade, fld[i].getName()) instanceof IModel<?>){
							str = "" + (((IModel<?>) Reflection.getFieldValue(entidade, fld[i].getName())).getPK());
						}else{
							str = "" + Reflection.getFieldValue(entidade, fld[i].getName());
						}
						auxFields=auxFields+","+cmp.nome()+"= '" + str + "'";
					}
				}				
			}
		}catch (Throwable e) {
			System.err.println(e);
			
		}		
		
		String sql = "update " + entidade.getTableName() + " " ;		
		   sql = sql + "set "+auxFields.substring(1) + " ";
		   sql = sql + " where " + entidade.getPKName() + "='" + String.valueOf(entidade.getPK()) + "'";
		
		if(Connect.getConexao()){
			System.out.println("sql = "+sql);
			int i = Connect.runSQL(sql);
			if (i == 1){
				Connect.close();
				return new Return(true, entidade.getTableName()+ " Alterado com sucesso!", TypeMessage.SUCESSO);
				
			}else{
				System.out.println("O " + entidade.getTableName() + "Não Foi Alterado problema no DAO.Alterar");  
				Connect.close();
				return  new Return(false, "Não Foi Alterado", TypeMessage.ERROR);
			}
		}
		System.out.println("O "+ entidade.getTableName() +" Não Foi Alterado problema no DAO.alterar.Connect.getConexão");
		return new Return(false);
	}
	
	public Return excluir(IModel<?> entidade){
		String auxFields="";
		Class<?> cls = entidade.getClass();	
		Field[] fld = cls.getDeclaredFields();
		
		try {
			for(int i = 0; i < fld.length; i++){
				Campo cmp = fld[i].getAnnotation(Campo.class);				
				if(cmp!=null){
					if(Reflection.getFieldValue(entidade, fld[i].getName())!=null){
						auxFields=auxFields+","+cmp.nome()+"=?";
					}
				}				
			}
		}catch (Throwable e) {
			System.err.println(e);
			
		}		
		
		String sql = "update " + entidade.getTableName() + " " ;		
		   sql = sql + "set ativo= 'false'";
		   sql = sql + " where " + entidade.getPKName() + "='" + String.valueOf(entidade.getPK()) + "'";
		System.out.println("sql = "+sql);
		if(Connect.getConexao()){
			
			int i = Connect.runSQL(sql);
			if (i == 1){
				Connect.close();
				return new Return(false, entidade.getTableName()+ " Desativado com sucesso!", TypeMessage.SUCESSO);
				
			}else{
				System.out.println("O "+ entidade.getTableName() + "Não Foi excluido problema no DAO.excluir");  
				Connect.close();
				return new Return(false);
			}
		}
		System.out.println("O " + entidade.getTableName() + "Não Foi Excluido problema no DAOUsuario.excluir.Connect.getConexão");
		return new Return(false);
	}

	
	public Return ativar(IModel<?> entidade){
		String auxFields="";
		Class<?> cls = entidade.getClass();	
		Field[] fld = cls.getDeclaredFields();
		
		try {
			for(int i = 0; i < fld.length; i++){
				Campo cmp = fld[i].getAnnotation(Campo.class);				
				if(cmp!=null){
					if(Reflection.getFieldValue(entidade, fld[i].getName())!=null){
						auxFields=auxFields+","+cmp.nome()+"=?";
					}
				}				
			}
		}catch (Throwable e) {
			System.err.println(e);
			
		}		
		
		String sql = "update " + entidade.getTableName() + " " ;		
		   sql = sql + "set ativo= 'true'";
		   sql = sql + " where " + entidade.getPKName() + "='" + String.valueOf(entidade.getPK()) + "'";
		System.out.println("sql = "+sql);
		if(Connect.getConexao()){
			
			int i = Connect.runSQL(sql);
			if (i == 1){
				Connect.close();
				return new Return(false, entidade.getTableName()+ " Ativado com sucesso!", TypeMessage.SUCESSO);
				
			}else{
				System.out.println("O "+ entidade.getTableName() + "Não Foi excluido problema no DAO.excluir");  
				Connect.close();
				return new Return(false);
			}
		}
		System.out.println("O " + entidade.getTableName() + "Não Foi Excluido problema no DAOUsuario.excluir.Connect.getConexão");
		return new Return(false);
	}
	/*
	 * função pesquisarUsuario pode ser chamada por uma string onde pesquisa qualquer 
	 * parte da palavra do titulo, diretor ou genero
	 * ou por um inteiro onde se pesquisa pelo id do filme..
	 */

	
	public ArrayList<HashMap<String,Object>>  pesquisarNome(IModel<?> entidade, String nome) throws SQLException{
		String sql = null;
		if(nome != null ){
			String sqlPorNome = "select "+ entidade.getTableColumnNames()+" from " + entidade.getTableName() + " ";
			sqlPorNome = sqlPorNome + " where ativo = true and ";
			
			
			String nomeVariaveis = entidade.getVariaveisPesquisarNome();
			String[] vetorVariaveis = new String [nomeVariaveis.split(",").length];
			vetorVariaveis = nomeVariaveis.split(",");
			for (int i = 0; i < vetorVariaveis.length ; i++){
				sqlPorNome = sqlPorNome + " "+ vetorVariaveis[i] + " LIKE '%" + nome + "%' OR";
			}
			
			sqlPorNome = sqlPorNome.substring(0, sqlPorNome.length()-2);
			sqlPorNome = sqlPorNome + " ;";
			System.out.println("sql:"+sqlPorNome);
			sql = sqlPorNome;
		
		}else{
			String sqlAll = "select "+ entidade.getTableColumnNames()+" from " + entidade.getTableName() + " ";
			sqlAll = sqlAll + " where ativo = true";
			sql = sqlAll;
		}
		
				
	 
	//	System.out.println("sql:"+sql);
		if(Connect.getConexao()){
			
			ArrayList<HashMap<String,Object>> result = new ArrayList<HashMap<String,Object>>();
			
		ResultSet rs =  Connect.setResultSet(sql);
		int colCount = rs.getMetaData().getColumnCount();
		while(rs.next()){
				HashMap<String,Object> record = new HashMap<String, Object>();
				for(int i=1;i<=colCount;i++){
					record.put(rs.getMetaData().getColumnName(i), rs.getString(i));
				}
				result.add(record);
		}
		Connect.close();
		return result;
		}
		System.out.println("O " + entidade.getTableName() + " Não Foi pesquisado problema no DAOUsuario pesquisarusuario(String) Connect.getConexão");
		return null;
	}
	
public ArrayList<HashMap<String,Object>>  pesquisarNomeAtivo(IModel<?> entidade, String nome) throws SQLException{
		
		String sql = "select "+ entidade.getTableColumnNames()+" from " + entidade.getTableName() + " ";
		sql = sql + " where ativo = 'true' and ";
		
		String nomeVariaveis = entidade.getVariaveisPesquisarNome();
		String[] vetorVariaveis = new String [nomeVariaveis.split(",").length];
		vetorVariaveis = nomeVariaveis.split(",");
		for (int i = 0; i < vetorVariaveis.length ; i++){
			sql = sql + " "+ vetorVariaveis[i] + " LIKE '%" + nome + "%' OR";
		}
		
		sql = sql.substring(0, sql.length()-2);
		sql = sql + " ;";
		
		System.out.println("sql:"+sql);
		if(Connect.getConexao()){
			
			ArrayList<HashMap<String,Object>> result = new ArrayList<HashMap<String,Object>>();
			
		ResultSet rs =  Connect.setResultSet(sql);
		int colCount = rs.getMetaData().getColumnCount();
		while(rs.next()){
				HashMap<String,Object> record = new HashMap<String, Object>();
				for(int i=1;i<=colCount;i++){
					record.put(rs.getMetaData().getColumnName(i), rs.getString(i));
				}
				result.add(record);
		}
		Connect.close();
		return result;
		}
		System.out.println("O " + entidade.getTableName() + " Não Foi pesquisado problema no DAOUsuario pesquisarusuario(String) Connect.getConexão");
		return null;
	}
	
	public ArrayList<HashMap<String,Object>>  pesquisarID(IModel<?> entidade) throws SQLException{
		
		String sql = "select "+ entidade.getTableColumnNames()+" from " + entidade.getTableName() + " ";
		sql = sql + " where  ativo = 'true' and " + entidade.getPKName() + "='" + String.valueOf(entidade.getPK()) + "'";
		
		System.out.println("sql:"+sql);
		if(Connect.getConexao()){
			
			ArrayList<HashMap<String,Object>> result = new ArrayList<HashMap<String,Object>>();
			
		ResultSet rs =  Connect.setResultSet(sql);
		int colCount = rs.getMetaData().getColumnCount();
		while(rs.next()){
				HashMap<String,Object> record = new HashMap<String, Object>();
				for(int i=1;i<=colCount;i++){
					record.put(rs.getMetaData().getColumnName(i), rs.getString(i));
				}
				result.add(record);
		}
		Connect.close();
		return result;
		}
		System.out.println("O " + entidade.getTableName() + " Não Foi pesquisado problema no DAOUsuario pesquisarusuario(String) Connect.getConexão");
		return null;
	}
	
	
public ArrayList<HashMap<String,Object>>  pesquisarCriterio(IModel<?> entidade, int criterio) throws SQLException{
		
		String sql = "select "+ entidade.getTableColumnNames()+" from " + entidade.getTableName() + " ";
		sql = sql + " where ativo = 'true' and " + entidade.getCriterio() +" = " +criterio+ " order by " + entidade.getOrdenacao() + " ;";
		
		
		System.out.println("sql pesquisarCriterio :"+sql);
		if(Connect.getConexao()){
			
			ArrayList<HashMap<String,Object>> result = new ArrayList<HashMap<String,Object>>();
			
		ResultSet rs =  Connect.setResultSet(sql);
		int colCount = rs.getMetaData().getColumnCount();
		while(rs.next()){
				HashMap<String,Object> record = new HashMap<String, Object>();
				for(int i=1;i<=colCount;i++){
					record.put(rs.getMetaData().getColumnName(i), rs.getString(i));
				}
				result.add(record);
		}
		Connect.close();
		return result;
		}
		System.out.println("O " + entidade.getTableName() + " Não Foi pesquisado problema no DAOUsuario pesquisarusuario(String) Connect.getConexão");
		return null;
	}

public ArrayList<HashMap<String,Object>>  pesquisarPorCategoriaOuNome(IModel<?> entidade, String nome, int idSupCategoria) throws SQLException{
	
	String sql = "select "+ entidade.getTableColumnNames()+" from " + entidade.getTableName() + " ";
	sql = sql + " where  ativo = 'true' and supcategoria ='" + idSupCategoria + "' and";
	
	System.out.println("sql:"+sql);
	String nomeVariaveis = entidade.getVariaveisPesquisarNome();
	String[] vetorVariaveis = new String [nomeVariaveis.split(",").length];
	vetorVariaveis = nomeVariaveis.split(",");
	for (int i = 0; i < vetorVariaveis.length ; i++){
		sql = sql + " "+ vetorVariaveis[i] + " LIKE '%" + nome + "%' OR";
	}
	
	sql = sql.substring(0, sql.length()-2);
	sql = sql + " ;";
	
	System.out.println("sql:"+sql);
	if(Connect.getConexao()){
		
		ArrayList<HashMap<String,Object>> result = new ArrayList<HashMap<String,Object>>();
		
	ResultSet rs =  Connect.setResultSet(sql);
	int colCount = rs.getMetaData().getColumnCount();
	while(rs.next()){
			HashMap<String,Object> record = new HashMap<String, Object>();
			for(int i=1;i<=colCount;i++){
				record.put(rs.getMetaData().getColumnName(i), rs.getString(i));
			}
			result.add(record);
	}
	Connect.close();
	return result;
	}
	System.out.println("O " + entidade.getTableName() + " Não Foi pesquisado problema no DAOUsuario pesquisarusuario(String) Connect.getConexão");
	return null;
}

public Return validarItemUnico(IModel<?> entidade, String[] valores, String[]nomesVariaveis) throws SQLException{
	int contador = 0;
	String sql = null;
	if(valores.length != nomesVariaveis.length){
		return  new Return(false, "Validacão de item ùnico Incorreta, valores devem possuir o mesmo número de variáveis", TypeMessage.ERROR);
	}
	if(Connect.getConexao()){
		while (contador < valores.length){
			sql = "select "+ entidade.getTableColumnNames()+" from " + entidade.getTableName() + " ";
			sql = sql + " where "+ nomesVariaveis[contador]+ " = '"+ valores[contador]+"' ;";
			ResultSet rs =  Connect.setResultSet(sql);
			if(rs.next()){
				return  new Return(false, "Já possui este registro no campo " + nomesVariaveis[contador] , TypeMessage.ERROR);
			}
			contador++;
		}
		return  new Return(true, "", TypeMessage.SUCESSO);
	}else{
		return  new Return(false, "Erro de conexão com o banco na validação de item único...", TypeMessage.ERROR);
	}
}


}



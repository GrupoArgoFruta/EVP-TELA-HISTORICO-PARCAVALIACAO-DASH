package br.com.argo.service;

import java.math.BigDecimal;
import java.sql.ResultSet;

import com.sankhya.util.BigDecimalUtil;
import com.sankhya.util.JdbcUtils;

import br.com.sankhya.jape.EntityFacade;
import br.com.sankhya.jape.core.JapeSession;
import br.com.sankhya.jape.core.JapeSession.SessionHandle;
import br.com.sankhya.jape.dao.JdbcWrapper;
import br.com.sankhya.jape.sql.NativeSql;
import br.com.sankhya.jape.wrapper.JapeFactory;
import br.com.sankhya.jape.wrapper.JapeWrapper;
import br.com.sankhya.modelcore.MGEModelException;
import br.com.sankhya.modelcore.util.DynamicEntityNames;
import br.com.sankhya.modelcore.util.EntityFacadeFactory;

public class ServiceEmail {
	
	public void notificarUsuarios(BigDecimal coduser, String obs, String titulo)
			throws MGEModelException {

		JdbcWrapper jdbc = null;
		NativeSql sql = null;
		ResultSet rset = null;
		SessionHandle hnd = null;
		String emails  = null;
		try {

			hnd = JapeSession.open();
			hnd.setFindersMaxRows(-1);
			EntityFacade entity = EntityFacadeFactory.getDWFFacade();
			jdbc = entity.getJdbcWrapper();
			jdbc.openSession();

			sql = new NativeSql(jdbc);

			sql.appendSql("SELECT EMAIL FROM TSIUSU \r\n"
					+ "WHERE CODUSU ="+coduser);

			sql.setNamedParameter("CODUSU", coduser);
			rset = sql.executeQuery();

			while (rset.next()) {

				emails  = rset.getString("EMAIL");
				enviarEmail(obs, titulo, emails);
		                 
		      
		        }
		} catch (Exception e) {
			MGEModelException.throwMe(e);
		} finally {
			JdbcUtils.closeResultSet(rset);
			NativeSql.releaseResources(sql);
			JdbcWrapper.closeSession(jdbc);
			JapeSession.close(hnd);
		}

}
	
	
	public  void enviarEmail(String titulo, String mensagem,String email) throws Exception {
		SessionHandle hnd = null;
		try {
			hnd = JapeSession.open();
			JapeWrapper ordemServicoDAO = JapeFactory.dao(DynamicEntityNames.FILA_MSG);
			ordemServicoDAO.create()
			.set("EMAIL", email)// envia para esse email
			.set("CODCON", BigDecimal.ZERO)
			.set("STATUS", "Pendente")
			.set("TIPOENVIO", "E")
			.set("MAXTENTENVIO", BigDecimalUtil.valueOf(3))
			.set("ASSUNTO", titulo)
			.set("MENSAGEM", mensagem.toCharArray())
			.save();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JapeSession.close(hnd);
		}

//joao.nascimento@argofruta.com
	}
}

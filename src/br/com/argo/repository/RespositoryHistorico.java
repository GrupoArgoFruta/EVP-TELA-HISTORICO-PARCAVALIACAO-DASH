package br.com.argo.repository;

import java.math.BigDecimal;
import java.sql.Timestamp;

import br.com.sankhya.jape.core.JapeSession;
import br.com.sankhya.jape.vo.DynamicVO;
import br.com.sankhya.jape.wrapper.JapeFactory;
import br.com.sankhya.jape.wrapper.JapeWrapper;
import br.com.sankhya.modelcore.MGEModelException;

public class RespositoryHistorico {
	public void lancarHistoricoParceiro(BigDecimal codparc,String avaliado,String motivo,String usuarioavaliador,Timestamp dtAlteracao,String nomeparc) throws MGEModelException {
		// TODO Auto-generated method stub
		
		JapeSession.SessionHandle hnd = null;
		JapeWrapper hisDAO = JapeFactory.dao("AD_PARCEIROAVALIACAO");
		try {
			
			hnd = JapeSession.open(); // Abertura da sessão do JapeSession
			DynamicVO histoVo = hisDAO.create()
				.set("CODPARC", codparc)
				.set("AD_AVALIACAO", avaliado)
				.set("AD_OBSAVAL", motivo)
				.set("NOMEUSU", usuarioavaliador)
				.set("DATALTER", dtAlteracao)
				.set("NOMEPARC", nomeparc)
//				.set("NUMCERTIFICADO", numeroCertifcado)
//				.set("DATAVALIDADE", ValidadeCertificadoAnitgo)
//				.set("DATAVALATUAL", validadeCertAtual)
//				.set("NOMECERT", nomeCertificado)
//				.set("CODTIPOCERT", codTipoCert)
				.save();  	
			
		} catch (Exception e) {
			MGEModelException.throwMe(e);
		} finally {
			JapeSession.close(hnd);
		}

	}

}

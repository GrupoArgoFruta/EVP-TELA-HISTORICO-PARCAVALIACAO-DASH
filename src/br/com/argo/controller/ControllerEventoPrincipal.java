package br.com.argo.controller;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import br.com.argo.repository.RespositoryHistorico;
import br.com.argo.service.ServiceEmail;
import br.com.sankhya.extensions.eventoprogramavel.EventoProgramavelJava;
import br.com.sankhya.jape.event.PersistenceEvent;
import br.com.sankhya.jape.event.TransactionContext;
import br.com.sankhya.jape.vo.DynamicVO;
import br.com.sankhya.modelcore.auth.AuthenticationInfo;
import br.com.sankhya.ws.ServiceContext;


public class ControllerEventoPrincipal implements EventoProgramavelJava {
	String titulo = "Avaliação Parceiro";
	 ServiceEmail emails = new ServiceEmail();
	 RespositoryHistorico repository = new RespositoryHistorico();
	@Override
	public void afterDelete(PersistenceEvent arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterInsert(PersistenceEvent arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterUpdate(PersistenceEvent arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeCommit(TransactionContext arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeDelete(PersistenceEvent arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeInsert(PersistenceEvent arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeUpdate(PersistenceEvent event) throws Exception {
		// TODO Auto-generated method stub
		DynamicVO vo = (DynamicVO) event.getVo();
		 BigDecimal usuarioLogadoID = ((AuthenticationInfo) ServiceContext.getCurrent().getAutentication()).getUserID();
		 String usuarioLogadoNome = ((AuthenticationInfo) ServiceContext.getCurrent().getAutentication()).getUsuVO().getNOMEUSU();
		 BigDecimal codGrupo = ((AuthenticationInfo) ServiceContext.getCurrent().getAutentication()).getUsuVO().getCODGRUPO();
//		 String email = ((AuthenticationInfo) ServiceContext.getCurrent().getAutentication()).getUsuVO().setEMAIL(usuarioLogadoNome);
		 String emailusuario = ((AuthenticationInfo) ServiceContext.getCurrent().getAutentication()).getUsuVO().getEMAIL();
		String avaliacao = vo.asString("AD_AVALIACAO");
		String obsavaliacao = vo.asString("AD_OBSAVAL");
		BigDecimal codparc = vo.asBigDecimal("CODPARC"); 
		String nomeparc = vo.asString("RAZAOSOCIAL");
		Timestamp dtAlteracao = new Timestamp(new Date().getTime());
		
//		 boolean statusavaliaçao = "RUIM".equals(avaliacao);
		 
		boolean avaliacaoModificada = event.getModifingFields().isModifing("AD_AVALIACAO");
	    boolean obsavaliacaoModificada = event.getModifingFields().isModifing("AD_OBSAVAL"); 

	    if ("RUIM".equalsIgnoreCase(avaliacao) && (avaliacaoModificada || obsavaliacaoModificada)) {
//				if ( avaliacao !=null ) {
					String mensagemEmail = "<html>" +
		            	    "<head>" +
		            	        "<title>Avaliação Parceiro</title>" +
		            	        "<link href='https://fonts.googleapis.com/css?family=Poppins:200,300,400,500,600,700' rel='stylesheet'>" +
		            	        "<style>" +
		            	            ".rating-emoji {" +
		            	                "font-size: 48px;" +
		            	                "text-align: center;" +
		            	                "margin: 20px 0;" +
		            	            "}" +
		            	            ".rating-reason {" +
		            	                "background-color: #f8f9fa;" +
		            	                "padding: 15px;" +
		            	                "border-radius: 8px;" +
		            	                "margin-top: 10px;" +
		            	                "font-size: 14px;" +
		            	                "color: #333;" +
		            	            "}" +
		            	            ".header {" +
		            	                "border-bottom: 1px solid #eaeaea;" +
		            	                "padding-bottom: 20px;" +
		            	                "margin-bottom: 20px;" +
		            	            "}" +
		            	        "</style>" +
		            	    "</head>" +
		            	    "<body style='background-color: #f4f4f4; margin: 0; padding: 0; width: 100%; height: 100%; font-family: Poppins, sans-serif; color: rgba(0, 0, 0, .4);'>" +
		            	        "<table width='100%' height='100%' border='0' cellpadding='0' cellspacing='0'>" +
		            	            "<tr>" +
		            	                "<td align='center' valign='top' style='padding-top: 30px; padding-bottom: 30px;'>" +
		            	                    "<table width='600' border='0' cellpadding='20' cellspacing='0' style='background-color: white; margin: auto; box-shadow: 0 0 10px rgba(0,0,0,0.1); border-radius: 8px;'>" +
		            	                        "<tr>" +
		            	                            "<td align='center' class='header'>" +
		            	                                "<img src='https://argofruta.com/wp-content/uploads/2021/05/Logo-text-green.png' alt='Argo Fruta Logo' width='200' style='margin-bottom: 10px;'>" +
		            	                                "<h2 style='color: #333; margin: 10px 0;'>Avaliação do Parceiro</h2>" +
		            	                            "</td>" +
		            	                        "</tr>" +
		            	                        "<tr>" +
		            	                            "<td style='padding: 0 30px 30px 30px;'>" +
		            	                                "<div class='rating-emoji'>" +
		            	                                    getEmojiHtml(avaliacao) +
		            	                                "</div>" +
		            	                                "<div style='text-align: center; font-size: 24px; font-weight: 500; color: " + getRatingColor(avaliacao) + "; margin-bottom: 20px;'>" +
		            	                                    avaliacao +
		            	                                "</div>" +
		            	                                "<div class='rating-reason'>" +
		            	                                    "<h3 style='margin-top: 0; color: #555;'>Motivo:</h3>" +
		            	                                    "<p>" + obsavaliacao + "</p>" +
		            	                                "</div>" +
		            	                                "<div class='rating-reason'>" +
	            	                                    "<h3 style='margin-top: 0; color: #555;'>Parceiro:</h3>" +
	            	                                    "<p>" + nomeparc + "</p>" +
	            	                                "</div>" +
		            	                                    
		            	                            "</td>" +
		            	                        "</tr>" +
		            	                    "</table>" +
		            	                "</td>" +
		            	            "</tr>" +
		            	        "</table>" +
		            	    "</body>" +
		            	"</html>";
					

					emails.notificarUsuarios(usuarioLogadoID, titulo, mensagemEmail);
					repository.lancarHistoricoParceiro(codparc, avaliacao, obsavaliacao,usuarioLogadoNome,dtAlteracao, nomeparc);

				}

			} 
		
		
		
	
		
		
	
	private String getEmojiHtml(String avaliacao) {
	    if (avaliacao == null) return "&#10067;"; // ❓
	    switch (avaliacao.toUpperCase()) {
	        case "BOM": return "&#128522;";  // 😊
	        case "REGULAR": return "&#128528;";  // 😐
	        case "RUIM": return "&#128544;";  // 😠
	        default: return "&#10067;"; // ❓
	    }
	}


	private String getRatingColor(String avaliacao) {
	    if(avaliacao == null) return "#999";
	    switch(avaliacao.toUpperCase()) {
	        case "BOM": return "#4CAF50";
	        case "REGULAR": return "#FFC107";
	        case "RUIM": return "#F44336";
	        default: return "#999";
	    }
	}

}

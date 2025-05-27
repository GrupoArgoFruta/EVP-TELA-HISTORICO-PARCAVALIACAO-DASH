package br.com.argo.controller;

import java.math.BigDecimal;

import br.com.argo.service.ServiceEmail;
import br.com.sankhya.extensions.actionbutton.AcaoRotinaJava;
import br.com.sankhya.extensions.actionbutton.ContextoAcao;
import br.com.sankhya.extensions.actionbutton.Registro;
import br.com.sankhya.jape.core.JapeSession;
import br.com.sankhya.jape.core.JapeSession.SessionHandle;
import br.com.sankhya.jape.dao.JdbcWrapper;
import br.com.sankhya.jape.sql.NativeSql;
import br.com.sankhya.jape.wrapper.JapeFactory;

public class PrincipalController implements AcaoRotinaJava {

	@Override
	public void doAction(ContextoAcao ctx) throws Exception {
	    Registro[] linha = ctx.getLinhas();
	    StringBuilder mensagemRetorno = new StringBuilder();
	    ServiceEmail emails = new ServiceEmail();
	    String titulo = "Avaliação Parceiro";
	    
	    // Construção do HTML


	    try {
	        for (Registro registro : linha) {
	            String avaliacao = (String) registro.getCampo("AD_AVALIACAO");
	            String obsavaliacao = (String) registro.getCampo("AD_OBSAVAL");
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
	            	                            "</td>" +
	            	                        "</tr>" +
	            	                    "</table>" +
	            	                "</td>" +
	            	            "</tr>" +
	            	        "</table>" +
	            	    "</body>" +
	            	"</html>";
	           
//	        emails.enviarEmail(titulo, mensagemEmail);
	        
	
	        }  
	    } catch (Exception e) {
	        e.printStackTrace();
	        ctx.mostraErro("Erro ao processar avaliações: " + e.getMessage());
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

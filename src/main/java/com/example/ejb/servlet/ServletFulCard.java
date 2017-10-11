package com.example.ejb.servlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.ejb.bussines.GameService;
import com.example.ejb.domain.Card;

import util.TemplateHtml;


@WebServlet("/ServletFulCard")
public class ServletFulCard extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String STATEFUL_CLICK_BEAN_KEY = "card_bean";
		
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
											throws ServletException, IOException {
		
		
		ArrayList<ArrayList<Card>> array3x7;
		GameService gameServices = getStatefulGameService(request);
				
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		
		TemplateHtml.setPathURL(request.getContextPath());
		
		out.println(TemplateHtml.getHead("Card") + 
		TemplateHtml.getMenu());
			
		
		//out.println("<p>Estatus: "+gameServices.getStatus()+"</p>");
				
		if(gameServices.getStatus()==0){			
			array3x7 = gameServices.startGame();			
			out.println(getRenderArray3x7(array3x7));			
		}else if(gameServices.getStatus()==1 || gameServices.getStatus()==2){				
			int column = Integer.valueOf(request.getParameter("column"));			
			array3x7 = gameServices.nextBaraja(column);								
			out.println(getRenderArray3x7(array3x7));		
		}else if(gameServices.getStatus()==3){			
			int column = Integer.valueOf(request.getParameter("column"));
			Card cardWinner = gameServices.cardFinal(column);
			out.println(getCardWinner(cardWinner));			
		}
		
		out.println(TemplateHtml.getFooter());		
	}

	
	
			
	
	private String getCardWinner(Card cardWinner) {
		StringBuilder str = new StringBuilder();
		
		str.append("<h1 align=\"center\">Resultado</h1>");
		str.append("<h2 align=\"center\">"+ cardWinner.getName() +"</h2>");
		str.append("<h2 align=\"center\"><img alt='"+cardWinner.getName()
					+"' src='/servletprojecttwo/img/"+cardWinner.getPathImage()
					+"' width='80' heigth='80'></h2>");	
		return str.toString();
	}





	private String getRenderArray3x7(ArrayList<ArrayList<Card>> array3x7) {
		
		StringBuilder str = new StringBuilder();
		
		str.append("<div class='container'>");
		str.append("<div class='row'>");
		str.append("<h1 align=\"center\">Game Card</h1>");					
		str.append("</div>");	
		str.append("</div>");		
		
		str.append("<div class='container'>");
		str.append("<div class='row'>");
		str.append("<table>");		
		
		str.append("<tr>");
		str.append("<th>1<th>");
		str.append("<th>2<th>");
		str.append("<th>3<th>");
		str.append("</tr>");
		
		for(int i=0; i<7;i++){
			str.append("<tr>");
			for(int j=0; j<1; j++){					
				str.append("<td>"
						+ "<img alt='"+array3x7.get(j).get(i).getName()
						+ "' src='/servletprojecttwo/img/"+array3x7.get(j).get(i).getPathImage()
						+ "' width='90' heigth='25'>"
						+ "</td>");
				str.append("<td>"
						+ "<img alt='"+array3x7.get(j+1).get(i).getName()
						+ "' src='/servletprojecttwo/img/"+array3x7.get(j+1).get(i).getPathImage()
						+ "' width='90' heigth='25'>"
						+ "</td>");
				str.append("<td>"
						+ "<img alt='"+array3x7.get(j+2).get(i).getName()
						+ "' src='/servletprojecttwo/img/"+array3x7.get(j+2).get(i).getPathImage()
						+ "' width='90' heigth='25'>"
						+ "</td>");
			}
			str.append("</tr>");
		}			
					
		str.append("</table>");
		str.append("</div>");	
		str.append("</div>");			
		
		
		str.append("<form action='/servletprojecttwo/servlet/card' method='post'>");
	    
		str.append("<select name=\"column\">"
	    		+ "<option value=\"1\">1</option>"
	    		+ "<option value=\"2\">2</option>"
	    		+ "<option value=\"3\">3</option>"
	    		+ "</select>");
	    
		str.append("<input type='submit' value='Column'>");
		str.append("</form>");
		return str.toString();
	}
	

	private GameService getStatefulGameService(HttpServletRequest request)
	         throws ServletException {
		
	     HttpSession httpSession = request.getSession(true);
	     GameService statefulTestBean = 
	             (GameService) httpSession.getAttribute(STATEFUL_CLICK_BEAN_KEY);
	     
	     
	     if (statefulTestBean == null) {
	         try {
	        	 
	             InitialContext ic = new InitialContext();
	             statefulTestBean =   (GameService) ic.lookup("java:module/GameService");
	             httpSession.setAttribute(STATEFUL_CLICK_BEAN_KEY, statefulTestBean);	          	       
	         } catch (NamingException e) {
	             throw new ServletException(e);
	         }
	     }
	     return statefulTestBean;
	 }
	
	
	
}
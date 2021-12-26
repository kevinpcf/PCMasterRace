package control;

import java.io.IOException; 
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ProductModelDS;
import model.Cart;

public class ProductHandling extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static ProductModelDS model = new ProductModelDS();
	
	public ProductHandling() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Cart carrello = (Cart)request.getSession().getAttribute("cart");
		if(carrello == null) 
		{
			carrello = new Cart();
			request.getSession().setAttribute("cart", carrello);
		}
		
		try {
			request.getSession().setAttribute("products", null);
			request.getSession().setAttribute("products", model.doRetrieveAll(""));
		} 
		catch (SQLException e) 
		{
			System.out.println("Error:" + e.getMessage());
		}

		String azione = request.getParameter("action");

		try {
			if (azione != null) {
				if (azione.equalsIgnoreCase("carrello")) 
				{
					int id = Integer.parseInt(request.getParameter("codice"));
					carrello.aggiungiProdotto(model.doRetrieveByKey(id));
					request.getSession().setAttribute("cart", carrello);
				} 
				else if (azione.equalsIgnoreCase("dettagli")) 
				{
					int id = Integer.parseInt(request.getParameter("codice"));
					request.removeAttribute("product");
					request.setAttribute("product", model.doRetrieveByKey(id));
					RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/main/dettagliProdotto.jsp");
					dispatcher.forward(request, response);
					return;
				} 
				else if(azione.equalsIgnoreCase("filtraRicerca"))
				{
					String ricerca = request.getParameter("ricerca");
					try {
						request.getSession().setAttribute("products", null);
						request.getSession().setAttribute("products", model.doRetrieveByName(ricerca));
					} 
					catch (SQLException e) 
					{
						System.out.println("Error:" + e.getMessage());
					}
				}
				
				else if(azione.equalsIgnoreCase("filtraCategoria"))
				{
					String ricerca = request.getParameter("categoria");
					try {
						request.getSession().setAttribute("products", null);
						request.getSession().setAttribute("products", model.doRetrieveByCategory(ricerca));
					} 
					catch (SQLException e) 
					{
						System.out.println("Error:" + e.getMessage());
					}
				}
			}			
		} 
		catch (SQLException e) 
		{
			System.out.println("Error:" + e.getMessage());
		}
		
		if(request.getSession().getAttribute("admin")!=null && (Boolean)request.getSession().getAttribute("admin"))
		{
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/admin/paginaAdmin.jsp");
			dispatcher.forward(request, response);
		}
		else
		{
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/main/paginaPrincipale.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}
}

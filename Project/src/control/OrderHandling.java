package control;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.OrderBean;
import model.OrderDS;
import model.ProductBean;
import model.ProductModel;
import model.ProductModelDS;
import model.UserBean;

@WebServlet("/OrderHandling")

public class OrderHandling extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	static ProductModel modelProdotto = new ProductModelDS();
	static OrderDS modelOrdine = new OrderDS();
       
    public OrderHandling() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String azione = request.getParameter("action");
		String username = ((UserBean)request.getSession().getAttribute("utenteAttivo")).getUsername();
		List<OrderBean> ordini = null;
		
		try 
		{
			ordini = (List<OrderBean>) modelOrdine.doRetrieveByUsername(username);
		} 
		catch (SQLException | ParseException e1) 
		{
			e1.printStackTrace();
		}
		
		request.getSession().setAttribute("ordini", ordini);
		
		if(azione!=null)
		{
			if(azione.equals("dettagli"))
			{
				ProductBean prodotto = new ProductBean();
				OrderBean ordine = new OrderBean();
				try 
				{
					ordine = modelOrdine.doRetrieveById(Integer.parseInt(request.getParameter("id")));
					prodotto = modelProdotto.doRetrieveByKey(ordine.getCodice());
				} 
				catch (NumberFormatException | SQLException | ParseException e) 
				{
					e.printStackTrace();
				} 
				request.getSession().setAttribute("dettagliProdotto", prodotto);
				request.getSession().setAttribute("dettagliOrdine", ordine);
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/ordering/dettagliOrdine.jsp");
				dispatcher.forward(request, response);
			}
		}
		
		else
		{
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/ordering/ordini.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}

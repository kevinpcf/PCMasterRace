package control;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.OrderBean;
import model.OrderDS;
import model.ProductBean;
import model.ProductModelDS;

@WebServlet("/AdminHandling")
public class AdminHandling extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	static ProductModelDS modelProduct = new ProductModelDS();
	static OrderDS modelOrder = new OrderDS();

    public AdminHandling() 
    {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String azione = request.getParameter("action");
		if(azione != null)
		{
			if(azione.equals("redirectModifica"))
			{
				int codice = Integer.valueOf(request.getParameter("codice"));
				ProductBean prodotto = new ProductBean();
				try 
				{
					 prodotto = modelProduct.doRetrieveByKey(codice);
				} 
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
				request.getSession().setAttribute("prodotto", prodotto);
				response.sendRedirect("pages/admin/gestioneProdotto.jsp");
			}
			else if(azione.equals("modifica"))
			{
				ProductBean prodotto = new ProductBean();
				prodotto.setNome(request.getParameter("nome"));
				prodotto.setCodice(Integer.parseInt(request.getParameter("codice")));
				prodotto.setDescrizione(request.getParameter("descrizione"));
				prodotto.setCategoria(request.getParameter("categoria"));
				prodotto.setPrezzoBase(Double.parseDouble(request.getParameter("prezzo")));
				prodotto.setQuantita(Integer.parseInt(request.getParameter("quantita")));
				prodotto.setIVA(Integer.parseInt(request.getParameter("IVA")));
				try 
				{
					modelProduct.doUpdate(prodotto);
				} 
				catch (SQLException e) 
				{
					response.sendRedirect("pages/error/inserimentoFallito.html");
				}
				response.sendRedirect("./product");
			}
			else if(azione.equals("inserisci"))
			{
				ProductBean prodotto = new ProductBean();
				prodotto.setNome(request.getParameter("nome"));
				prodotto.setCodice(Integer.parseInt(request.getParameter("codice")));
				prodotto.setDescrizione(request.getParameter("descrizione"));
				prodotto.setCategoria(request.getParameter("categoria"));
				prodotto.setPrezzoBase(Double.parseDouble(request.getParameter("prezzo")));
				prodotto.setQuantita(Integer.parseInt(request.getParameter("quantita")));
				prodotto.setIVA(Integer.parseInt(request.getParameter("IVA")));
				try 
				{
					modelProduct.doSave(prodotto);
				} 
				catch (SQLException e) 
				{
					response.sendRedirect("pages/error/inserimentoFallito.html");
				}
				response.sendRedirect("./product");
			}
			else if(azione.equals("redirectInserisci"))
			{
				request.getSession().removeAttribute("prodotto");
				response.sendRedirect("pages/admin/gestioneProdotto.jsp");
			}
			
			else if(azione.equals("ordini"))
			{
				try 
				{
					List<OrderBean> ordini = (List<OrderBean>) modelOrder.doRetrieveAll();
					request.getSession().setAttribute("ordini", ordini);
					response.sendRedirect("pages/admin/ordiniAdmin.jsp");
				} 
				catch (SQLException | ParseException e) 
				{
					e.printStackTrace();
				}
			}
			
			else if(azione.equals("filtraOrdini"))
			{
				String username = request.getParameter("username");
				String dataInizio = request.getParameter("start");
				String dataFine = request.getParameter("end");
				ArrayList<OrderBean> ordini = new ArrayList<OrderBean>();
				
				if(username != null && !username.isBlank() && !username.isEmpty())
				{
					try 
					{
						ordini = (ArrayList<OrderBean>) modelOrder.doRetrieveBetweenDates(dataInizio, dataFine, username);
					} 
					catch (SQLException | ParseException e) 
					{
						e.printStackTrace();
					}
				}
				else
				{
					try 
					{
						ordini = (ArrayList<OrderBean>) modelOrder.doRetrieveBetweenDates(dataInizio, dataFine, null);
					} 
					catch (SQLException | ParseException e) 
					{
						e.printStackTrace();
					}
				}
				request.getSession().setAttribute("ordini", ordini);
				response.sendRedirect("pages/admin/ordiniAdmin.jsp");
			}
			else if(azione.equals("elimina"))
			{
				try 
				{
					modelProduct.doDeleteAll(Integer.parseInt(request.getParameter("codice")));
				} 
				catch (NumberFormatException | SQLException e) 
				{
					e.printStackTrace();
				}
				response.sendRedirect("./product");
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}
}

package control;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.AddressDS;
import model.Cart;
import model.OrderBean;
import model.OrderDS;
import model.PaymentDS;
import model.ProductBean;
import model.ProductModel;
import model.ProductModelDS;
import model.UserBean;
import model.AddressBean;
import model.PaymentBean;

import java.util.GregorianCalendar;
import java.util.*;

@WebServlet("/Ordering")
public class CartHandling extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	static ProductModel modelProdotto = new ProductModelDS();
	static OrderDS modelOrdine = new OrderDS();
	static AddressDS modelAddress = new AddressDS();
	static PaymentDS modelPayment = new PaymentDS();
       
    public CartHandling() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Cart carrello = (Cart) request.getSession().getAttribute("cart");
		String azione = request.getParameter("action");

		if(azione!=null)
		{
			if(azione.equals("ordina"))
			{
					List<ProductBean> prodotti = carrello.getProdotti();
					String indirizzo = request.getParameter("indirizzi");
					String pagamento = request.getParameter("pagamenti");
					double totale = 0;
					
					for(int i=0;i<prodotti.size();i++)
					{
						OrderBean order = new OrderBean();
						order.setCodice(prodotti.get(i).getCodice());
						order.setData(new GregorianCalendar());
						order.setPrezzo(prodotti.get(i).getPrezzo()*prodotti.get(i).getQuantita());
						order.setUsername(((UserBean)request.getSession().getAttribute("utenteAttivo")).getUsername());
						order.setQuantita(prodotti.get(i).getQuantita());
						order.setCodiceIndirizzo(Integer.valueOf(indirizzo));
						order.setCodicePagamento(Integer.valueOf(pagamento));
						
						try 
						{
							modelOrdine.doSave(order);
							totale += order.getPrezzo();
						}
						catch (SQLException e1) 
						{
							e1.printStackTrace();
						}
							   
						for(int j=0; j<prodotti.get(i).getQuantita(); j++)
						{
							try 
							{
								modelProdotto.doDelete(prodotti.get(i).getCodice());
							} 
							catch (SQLException e) 
							{
								e.printStackTrace();
							} 
						}
					}
					    
					request.getSession().setAttribute("cart", carrello);
					request.getSession().setAttribute("products", null);
					request.getSession().setAttribute("totale", totale);
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/ordering/ordineEffettuato.jsp");
					dispatcher.forward(request, response);
			}
			
			else if(azione.equals("datiOrdine"))
			{
				if(request.getSession().getAttribute("utenteAttivo")==null)
				{
					response.sendRedirect("pages/auth/login.html");
				}
				
				else
				{
					List<ProductBean> prodotti = carrello.getProdotti();
					boolean errore=false;
					
					for(int i=0; i<prodotti.size(); i++)
					{
						int id = prodotti.get(i).getCodice();
						int quantita = Integer.parseInt(request.getParameter(String.valueOf(id)));
						
						try 
						{
							if(quantita > ((ProductBean) modelProdotto.doRetrieveByKey(id)).getQuantita())	errore=true;
						} 
						catch (SQLException e) 
						{
							e.printStackTrace();
						}
					}
					
					if(errore)
					{
						request.getSession().setAttribute("cart", new Cart());
						RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/error/prodottoNonDisponibile.html");
						dispatcher.forward(request, response);
					}
					else
					{
					    for(int i=0; i<prodotti.size(); i++)
					    {
						   int id = prodotti.get(i).getCodice();
						   int quantita = Integer.parseInt(request.getParameter(String.valueOf(id)));
						   prodotti.get(i).setQuantita(quantita);
					    }
				
						ArrayList<PaymentBean> payments = new ArrayList<PaymentBean>();
						ArrayList<AddressBean> addresses = new ArrayList<AddressBean>();
						UserBean user = (UserBean)request.getSession().getAttribute("utenteAttivo");
						try 
						{
							payments = (ArrayList<PaymentBean>)modelPayment.doRetrieveByUser(user.getUsername());
							addresses = (ArrayList<AddressBean>)modelAddress.doRetrieveByUser(user.getUsername());
						} 
						catch (SQLException | ParseException e) 
						{
							e.printStackTrace();
						}
						request.getSession().setAttribute("pagamenti", payments);
						request.getSession().setAttribute("indirizzi", addresses);
						RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/ordering/datiOrdine.jsp");
						dispatcher.forward(request, response);
					}
				}
			}
			
			else if(azione.equals("elimina"))
			{
				int codice = Integer.valueOf(request.getParameter("codice"));
				List<ProductBean> prodotti = carrello.getProdotti();
				for(int i=0;i<prodotti.size();i++)
				{
					if(prodotti.get(i).getCodice()==codice)
					{
						carrello.cancellaProdotto(prodotti.get(i));;
						break;
					}
				}
			    request.getSession().setAttribute("cart", carrello);
			    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/ordering/carrello.jsp");
				dispatcher.forward(request, response);
			}
			
			else if(azione.equals("svuota"))
			{
				request.getSession().setAttribute("cart", new Cart());
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/ordering/carrello.jsp");
				dispatcher.forward(request, response);
			}
			
	     }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}

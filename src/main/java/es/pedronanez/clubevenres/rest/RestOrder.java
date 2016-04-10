package es.pedronanez.clubevenres.rest;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONArray;
import org.json.JSONObject;

import es.pedronanez.clubevenres.db.Database;
import es.pedronanez.clubevenres.entities.Order;

@Path("orders")
public class RestOrder {

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String createNewOrder() {
        Calendar date = Calendar.getInstance();
        date.set(Calendar.HOUR_OF_DAY, 0);
        date.set(Calendar.MINUTE, 0);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);
        Order order = new Order();
        order.setActive(true);
        order.setDate(date);
        order = Database.create(order);
        return "OK -> " + order.getId();
    }
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String ordersList() {
        JSONArray result = new JSONArray();
        List<Order> orders = Database.getAll(Order.class);
        for (Order order : orders) {
            JSONObject jsOrder = new JSONObject();
            jsOrder.put("id", order.getId());
            
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            jsOrder.put("date", sdf.format(order.getDate().getTime()));
            result.put(jsOrder);
        }
        return result.toString();
    }
    
    
    
}

package es.pedronanez.clubevenres.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONArray;
import org.json.JSONObject;

import es.pedronanez.clubevenres.db.Database;
import es.pedronanez.clubevenres.entities.Ingredient;
import es.pedronanez.clubevenres.entities.Plate;

@Path("pratos")
public class RestPlates {

	/**
	 * 
	 * @return a JSON with the full list of plates (ID + Ingredients in string).
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String listaPlatos() {
		JSONArray result = new JSONArray();
		List<Plate> platos = Database.getAll(Plate.class);
		for (Plate plato : platos) {
			JSONObject jsPlato = new JSONObject();
			jsPlato.put("id", plato.getId());
			String descripcion = plato.getNombre() + " con ";
			List<String> ingredientes = new ArrayList<>();
			for (Ingredient ingrediente : plato.getComplementos()) {
				ingredientes.add(ingrediente.getName());
			}
			descripcion += ((descripcion.isEmpty()) ? "" : " ") + String.join(", ", ingredientes);
			descripcion += " - " + plato.getPrecio() + "\u20AC";
			jsPlato.put("descripcion", descripcion);
			result.put(jsPlato);
		}
		return result.toString();
	}

	/**
	 * 
	 * @param id identifier of the selected plate
	 * @return a detailed json of the selected plate with its ingredients, prices...
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Path("{id}")
	public String detallesPlato(@PathParam("id") String id) {
		Plate plato = Database.getElementById(Plate.class, Integer.valueOf(id));
		JSONObject result = new JSONObject(plato);
		return result.toString();
	}
}

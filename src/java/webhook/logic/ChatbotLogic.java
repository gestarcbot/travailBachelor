/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webhook.logic;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateful;
import javax.inject.Inject;
import org.json.JSONObject;
import service.Services;

/**
 *
 * @author boris.klett
 */
@Stateful
public class ChatbotLogic implements Serializable{

    private static final long serialVersionUID = 1L;
    @Inject
    Services services;

    public ChatbotLogic() {
    }

    public String getCosts(JSONObject requestObject) {

        String shippingZone = new String();
        int cost;
        try {
            JSONObject costJson = new JSONObject("{\"Europe\":100, \"North America\":200, \"South America\":300, \"Asia\":400, \"Africa\":500}");
            JSONObject parametersObject = requestObject.getJSONObject("parameters");
            shippingZone = parametersObject.getString("shippingZone");
            cost = costJson.getInt(shippingZone);

        } catch (Exception ex) {
            System.out.println(ex);
            Logger.getLogger(ChatbotLogic.class.getName()).log(Level.SEVERE, null, ex);
            return "Je n'arrive pas à avoir l'information que tu cherches. Contact un même de la Gest'arc";
        }
        return "Le coût de l'envoie vers " + shippingZone + " est " + cost + " euros." + services.getEventName(1);
    }

}

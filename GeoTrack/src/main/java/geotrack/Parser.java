package geotrack;

import org.json.JSONObject;

/**
 * Класс для парсера.
 */
public class Parser {
    /**
     * Я не хочу создавать объект парсера кучу раз для действий,
     * что не зависят от полей самого объекта парсера,
     * так что метод статический.
     * @param jsonData местоположение, записанное в строке json.
     * @return распаршенное местоположение.
     * @throws org.json.JSONException если json строка сломанная.
     */
    public static Location parseJSONtoLocation(String jsonData){
        JSONObject jsonObject = new JSONObject(jsonData);

        return new Location(
                jsonObject.has("country_name") ? jsonObject.getString("country_name") : "",
                jsonObject.has("region_name")? jsonObject.getString("region_name"):"",
                jsonObject.has("city")? jsonObject.getString("city"):"",
                jsonObject.has("latitude")? jsonObject.getDouble("latitude"):0,
                jsonObject.has("longitude")? jsonObject.getDouble("longitude"):0);
    }
}

package geotrack;

import org.json.JSONException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {


    @Test
    public void test_parseJSONtoLocation_goodJson_is_parsed() {
        String json ="{\"who\":\"Гарри Поттер\",\"country_code\":\"ENG\",\"country_name\":\"Великобритания\"," +
                "\"region_code\":\"LON\",\"region_name\":\"Лондон\"," +
                "\"city\":\"Литл Уининг\",\"latitude\":12.0,\"longitude\":0.12}";
        String expectedResult = "Страна: Великобритания\n" +
                "Регион: Лондон\n" +
                "Город: Литл Уининг\n" +
                "Широта: 12.0\n" +
                "Долгота: 0.12\n";
        String actualResult = Parser.parseJSONtoLocation(json).toString();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void test_parseJSONtoLocation_badJson_causes_JSONException(){
        String json ="";
        assertThrows(JSONException.class, () -> Parser.parseJSONtoLocation(json));
        String json1 ="{";
        assertThrows(JSONException.class, () -> Parser.parseJSONtoLocation(json1));
        String json2 = "{\"ip\":\"Гарри Поттер\",\"region_code\":\"LON\",\"region_name\":\"Лондон\",";
        assertThrows(JSONException.class, () -> Parser.parseJSONtoLocation(json2));
    }

    @Test
    public void test_parseJSONtoLocation_json_has_not_string_fields(){
        String json = "{\"what\":\"Бермудский треугольник\",\"latitude\":26.37,\"longitude\":70.53}";
        String expectedResult = "Широта: 26.37\n" +
                "Долгота: 70.53\n";
        String actualResult = Parser.parseJSONtoLocation(json).toString();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void test_parseJSONtoLocation_json_has_empty_string_fields(){
        String json ="{\"who\":\"Букля\",\"country_code\":\"ENG\",\"country_name\":\" \"," +
                "\"region_code\":\"LON\",\"region_name\":\"\"," +
                "\"city\":\"    \",\"latitude\":34.0,\"longitude\":18}";
        String expectedResult = "Широта: 34.0\n" +
                "Долгота: 18.0\n";
        String actualResult = Parser.parseJSONtoLocation(json).toString();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void test_parseJSONtoLocation_json_has_not_double_fields(){
        String json ="{\"who\":\"Альбус Дамблдор\",\"country_code\":\"ENG\",\"country_name\":\"Великобритания\"," +
                "\"region_name\":\"Шотландия\",\"city\":\"Хогвартс\"}";
        String expectedResult = "Страна: Великобритания\n" +
                "Регион: Шотландия\n" +
                "Город: Хогвартс\n" +
                "Широта: 0.0\n" +
                "Долгота: 0.0\n";
        String actualResult = Parser.parseJSONtoLocation(json).toString();
        assertEquals(expectedResult, actualResult);
    }
}
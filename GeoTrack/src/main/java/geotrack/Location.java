package geotrack;

public class Location {

    private final String countryName;
    private final String regionName;
    private final String city;
    private final double latitude;
    private final double longitude;

    /**
     * Публичный конструктор местоположения.
     *
     * @param countryName страна
     * @param regionName  регион
     * @param city        город
     * @param latitude    широта
     * @param longitude   долгота
     */
    public Location(String countryName, String regionName,
                    String city, double latitude,
                    double longitude) {
        this.countryName = countryName;
        this.regionName = regionName;
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;

    }

    /**
     * @return строковое представление о местоположении.
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        if (!countryName.isBlank()) {
            stringBuilder.append("Country: ").append(countryName).append("\n");
        }

        if (!regionName.isBlank()) {
            stringBuilder.append("Region: ").append(regionName).append("\n");
        }

        if (!city.isBlank()) {
            stringBuilder.append("City: ").append(city).append("\n");
        }

        stringBuilder.append("Latitude: ").append(latitude).append("\n");
        stringBuilder.append("Longitude: ").append(longitude).append("\n");

        return stringBuilder.toString();
    }

    public String text(){
        StringBuilder stringBuilder = new StringBuilder();
        if (!countryName.isBlank()) {
            stringBuilder.append(countryName).append(", ");
        }
        if (!regionName.isBlank()) {
            stringBuilder.append(regionName).append(", ");
        }
        if (!city.isBlank()) {
            stringBuilder.append(city);
        }
        return stringBuilder.toString();
    }
}

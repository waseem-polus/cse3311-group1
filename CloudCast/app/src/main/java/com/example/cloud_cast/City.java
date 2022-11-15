package com.example.cloud_cast;
//Eno Mbosowo
public class City {

    private double temp;
    private double tempMin;
    private double tempMax;
    private String cityName;
    private String stateName;
    private String countryName;
    private double windSpeed;
    private double precipitation;
    private double humidity;
    private double pressure;

    public City() {
        temp = 0;
        tempMin = 0;
        tempMax = 0;
        cityName = "";
        stateName = "";
        countryName = "";
        windSpeed = 0;
        precipitation = 0;
        humidity = 0;
        pressure = 0;
    }

    public City(String cityName, String stateName, String countryName){
        this.cityName = cityName;
        this.stateName = stateName;
        this.countryName = countryName;

        temp = 0;
        tempMin = 0;
        tempMax = 0;
        windSpeed = 0;
        precipitation = 0;
        humidity = 0;
        pressure = 0;
    }

    public City(String cityName, String countryName){
        this.cityName = cityName;
        this.stateName = "";
        this.countryName = countryName;

        temp = 0;
        tempMin = 0;
        tempMax = 0;
        windSpeed = 0;
        precipitation = 0;
        humidity = 0;
        pressure = 0;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public int getTempMin() {
        return tempMin;
    }

    public void setTempMin(int tempMin) {
        this.tempMin = tempMin;
    }

    public int getTempMax() {
        return tempMax;
    }

    public void setTempMax(int tempMax) {
        this.tempMax = tempMax;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public int getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(int windSpeed) {
        this.windSpeed = windSpeed;
    }

    public int getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(int precipitation) {
        this.precipitation = precipitation;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }
}
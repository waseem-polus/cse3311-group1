package com.example.cloud_cast;

public class SearchCityInfo {
    //Christopher Nguyen
    private String cityName;
    private String countryName;
    private double temperatureKelvin;
    //Personal Note: In the future, perhaps we can set it to a single temperature, and adjust the temperature shown with a bunch of math
    private int rainChance;
    //Since when did we care for the chance of rain to be a double? like ever?

    public SearchCityInfo(){}

    public SearchCityInfo(String cityInput, String countryInput, double temperature, int rainPercent){
        this.cityName=cityInput;
        this.countryName=countryInput;
        this.temperatureKelvin=temperature;
        this.rainChance=rainPercent;
    }

    public String getCity(){
        return cityName;
    }
    public void setCity(String cityName){
        this.cityName=cityName;
    }

    public String getCountry(){
        return countryName;
    }
    public void setCounty(String countryName){
        this.countryName=countryName;
    }

    public double getTemperature(){
        return temperatureKelvin;
    }
    public void setTemperature(double temperatureKelvin){this.temperatureKelvin=temperatureKelvin; }

    public int getRainChance(){
        return rainChance;
    }
    public void setRainChance(int rainChance){
        this.rainChance=rainChance;
    }

}

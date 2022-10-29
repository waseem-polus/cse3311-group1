package com.example.cloud_cast;

//This class is to convert imperial units to metric units or vice versa, and it also converts temperature units
//m/s is default, and celsius is default
//This class has not been used anywhere

public class ConvertUnits {
    private static Double meterBySecond;
    private static Double mileByHour;

    private static Double celsius;
    private static Double fahrenheit;

    public ConvertUnits() {
    }

    public static Double getMeterBySecond(Double milePerHour) {
        meterBySecond = milePerHour / 2.237;
        return meterBySecond;
    }

    public static Double getMileByHour(Double meterBySecond) {
        mileByHour = meterBySecond * 2.237;
        return mileByHour;
    }

    public static Double getCelsius(Double kelvin) {
        celsius = (double)(kelvin - 273.15);
        return celsius;
    }

    public static Double getFahrenheit(Double kelvin) {
        fahrenheit = (double)(getCelsius(kelvin) * 1.8 + 32);
        return fahrenheit;
    }

}

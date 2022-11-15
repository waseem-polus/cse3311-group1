import java.util.ArrayList;
import java.net.URLEncoder;

//http://api.openweathermap.org/geo/1.0/direct?q={name of city}&limit=5&appid=0ec192d57d6c5e00396f88cd7cad1f6e [this gets lat, lon]
//https://api.openweathermap.org/data/3.0/onecall?lat={lat}&lon={lon}&exclude={part}&appid=0ec192d57d6c5e00396f88cd7cad1f6e [this gets weather data]

public class Cities{

    private ArrayList<City> cities;

    public Cities(){
        cities = new ArrayList<Cities>();
    }

    public void addCity(City c){
        cities.add(c);
    }

    public City identify(String city, String state, String country){
         return ;
    }

    public void update(){

    }
}

//placeholder class for list of cities
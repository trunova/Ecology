package trunova.model;

import trunova.build.Component;

import java.util.ArrayList;

@Component
public class CityDAO {
    private ArrayList<Factory> cities;

    public ArrayList<Factory> getCities() {
        return cities;
    }

    public void setCities(ArrayList<Factory> cities) {
        this.cities = cities;
    }
}

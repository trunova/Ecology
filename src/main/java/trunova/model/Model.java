package trunova.model;

import java.util.List;

public class Model {
    private int id = 0;
    private List<Factory> factories;
    private int carCount;
    private int realiseForCar;
    private int square;
    private int money;
    private int filterCount;
    private int filterCost;
    private int release;

    public Model() {
    }

    public Model(int id, List<Factory> factories, int carCount, int realiseForCar, int square, int money, int filterCount, int filterCost, int release) {
        this.id = id;
        this.factories = factories;
        this.carCount = carCount;
        this.realiseForCar = realiseForCar;
        this.square = square;
        this.money = money;
        this.filterCount = filterCount;
        this.filterCost = filterCost;
        this.release = release;
    }

    public Model(List<Factory> factories, int carCount, int realiseForCar, int square, int money, int filterCount, int filterCost, int release) {
        this.factories = factories;
        this.carCount = carCount;
        this.realiseForCar = realiseForCar;
        this.square = square;
        this.money = money;
        this.filterCount = filterCount;
        this.filterCost = filterCost;
        this.release = release;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Factory> getFactories() {
        return factories;
    }

    public void setFactories(List<Factory> factories) {
        this.factories = factories;
    }

    public int getCarCount() {
        return carCount;
    }

    public void setCarCount(int carCount) {
        this.carCount = carCount;
    }

    public int getRealiseForCar() {
        return realiseForCar;
    }

    public void setRealiseForCar(int realiseForCar) {
        this.realiseForCar = realiseForCar;
    }

    public int getSquare() {
        return square;
    }

    public void setSquare(int square) {
        this.square = square;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getFilterCount() {
        return filterCount;
    }

    public void setFilterCount(int filterCount) {
        this.filterCount = filterCount;
    }

    public int getFilterCost() {
        return filterCost;
    }

    public void setFilterCost(int filterCost) {
        this.filterCost = filterCost;
    }

    public int getRelease() {
        return release;
    }

    public void setRelease(int release) {
        this.release = release;
    }

    public String toCSVString(String CSV_SEPARATOR){
        StringBuilder stringBuffer = new StringBuilder();
        stringBuffer.append(carCount);
        stringBuffer.append(CSV_SEPARATOR);
        stringBuffer.append(realiseForCar);
        stringBuffer.append(CSV_SEPARATOR);
        stringBuffer.append(square);
        stringBuffer.append(CSV_SEPARATOR);
        stringBuffer.append(money);
        stringBuffer.append(CSV_SEPARATOR);
        stringBuffer.append(filterCount);
        stringBuffer.append(CSV_SEPARATOR);
        stringBuffer.append(filterCost);
        stringBuffer.append(CSV_SEPARATOR);
        stringBuffer.append(release);
        for (Factory c : factories) {
            stringBuffer.append("\n");
            stringBuffer.append(c.toCSVString(CSV_SEPARATOR));
        }
        return stringBuffer.toString();
    }
}

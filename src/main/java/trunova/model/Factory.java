package trunova.model;

public class Factory {
    private int id = 0;
    private int posX;
    private int posY;
    private int maxRelease;
    private int tax;

    public Factory() {
    }

    public Factory(int id, int posX, int posY, int release, int tax) {
        this.id = id;
        this.posX = posX;
        this.posY = posY;
        this.maxRelease = release;
        this.tax = tax;
    }

    public Factory(int posX, int posY, int release, int tax) {
        this.posX = posX;
        this.posY = posY;
        this.maxRelease = release;
        this.tax = tax;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getMaxRelease() {
        return maxRelease;
    }

    public void setMaxRelease(int maxRelease) {
        this.maxRelease = maxRelease;
    }

    public int getTax() {
        return tax;
    }

    public void setTax(int tax) {
        this.tax = tax;
    }

    public String toCSVString(String CSV_SEPARATOR){
        return posX +
                CSV_SEPARATOR +
                posY +
                CSV_SEPARATOR +
                maxRelease +
                CSV_SEPARATOR +
                tax +
                CSV_SEPARATOR;
    }
}



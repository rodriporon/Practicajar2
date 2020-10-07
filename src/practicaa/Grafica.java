package practicaa;

public class Grafica {
    
    private String ejeX;
    private String ejeY;

    public Grafica() {
    }

    public Grafica(String ejeX, String ejeY) {
        this.ejeX = ejeX;
        this.ejeY = ejeY;
    }
    
    

    /**
     * @return the ejeX
     */
    public String getEjeX() {
        return ejeX;
    }

    /**
     * @param ejeX the ejeX to set
     */
    public void setEjeX(String ejeX) {
        this.ejeX = ejeX;
    }

    /**
     * @return the ejeY
     */
    public String getEjeY() {
        return ejeY;
    }

    /**
     * @param ejeY the ejeY to set
     */
    public void setEjeY(String ejeY) {
        this.ejeY = ejeY;
    }
    
}

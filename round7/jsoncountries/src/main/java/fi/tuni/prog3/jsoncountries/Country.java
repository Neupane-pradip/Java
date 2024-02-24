package fi.tuni.prog3.jsoncountries;

public class Country implements Comparable<Country>{

    // Private elements
    private final String name;
    private final double area;
    private final long population;
    private final double gdp;

    public Country(String name, double area, long population,double gdp){
        this.name=name;
        this.area=area;
        this.population=population;
        this.gdp=gdp;
    }
    public String getName(){return name;}
    public double getArea(){return area;}
    public long getPopulation(){return population;}
    public double getGdp(){return gdp;}
 
    @Override
    public int compareTo(Country o) {
        return this.name.compareTo(o.name);
    }

    // Obvious toString method to give info in wanted format
    @Override
    public String toString(){
        return String.format("%s%n" +
                        "  Area: %.1f km2%n" +
                        "  Population: %d%n" +
                        "  GDP: %.1f (2015 USD)%n",
                name,area,population,gdp);
    }  
}
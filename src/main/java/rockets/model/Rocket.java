package rockets.model;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.apache.commons.lang3.Validate.*;

public class Rocket extends Entity {
    private String name;

    private String nationality;

    private LaunchServiceProvider manufacturer;

    private String massToGTO;

    private String massToLEO;

    private String massToOther;

    /**
     * All parameters shouldn't be null.
     *
     * @param name
     * @param nationality
     * @param manufacturer
     */
    public Rocket(String name, String nationality, LaunchServiceProvider manufacturer) {
        notBlank(name,"name cannot be empty or null");
        notBlank(nationality, "country cannot be empty or null");
        notNull(manufacturer, "manufacturer cannot be null");
        isTrue(name.equals(name.trim()), "There should be no empty space at the beginning or the end of a name");
        isTrue(nationality.equals(nationality.trim()), "There should be no empty space at the beginning or the end of a country");
        inclusiveBetween(2,40, name.length(), "The length of the name must be equal or greater than 2 and equal or smaller than 40");
        inclusiveBetween(2,40, nationality.length(), "The length of the country must be equal or greater than 2 and equal or smaller than 40");

        this.name = name;
        this.nationality = nationality;
        this.manufacturer = manufacturer;
    }

    public String getName() {
        return name;
    }

    public String getNationality() {
        return nationality;
    }

    public LaunchServiceProvider getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(LaunchServiceProvider manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getMassToLEO() {
        return massToLEO;
    }

    public String getMassToOther() {
        return massToOther;
    }

    public void setMassToGTO(String massToGTO) {
        notNull(massToGTO, "massToGTO cannot be null");
        this.massToGTO = massToGTO;
    }

    public void setMassToLEO(String massToLEO) {
        notNull(massToLEO, "massToLEO cannot be null");
        this.massToLEO = massToLEO;
    }

    public void setMassToOther(String massToOther) {
        notNull(massToOther, "massToOther cannot be null");
        this.massToOther = massToOther;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rocket rocket = (Rocket) o;
        return Objects.equals(name, rocket.name) &&
                Objects.equals(nationality, rocket.nationality) &&
                Objects.equals(manufacturer, rocket.manufacturer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, nationality, manufacturer);
    }

    public boolean checkNumber(String input)
    {
        for(int i = 0; i < input.length(); i++)
        {
            char c = input.charAt(i);
            if (Character.isDigit(c))
            {
                return true;
            }
        }
        return false;
    }

    public boolean checkSpecialCharacter(String input){

        Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(input);
        boolean b = m.find();

        if (b){

            //System.out.println("There is a special character in my string");
            return true;
        }

        else{
            return false;
        }

    }

    @Override
    public String toString() {
        return "Rocket{" +
                "name='" + name + '\'' +
                ", nationality='" + nationality+ '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", massToGTO='" + massToGTO + '\'' +
                ", massToLEO='" + massToLEO + '\'' +
                ", massToOther='" + massToOther + '\'' +
                '}';
    }
}

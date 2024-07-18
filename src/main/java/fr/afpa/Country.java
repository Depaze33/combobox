package fr.afpa;

import java.util.ArrayList;
import java.util.Locale;

public class Country {
    private String name;
    private String isoCode;

    public Country(String name, String isoCode) {
        this.name = name;
        this.isoCode = isoCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsoCode() {
        return isoCode;
    }

    public void setIsoCode(String isoCode) {
        this.isoCode = isoCode;
    }

    public static ArrayList<Country> getCountriesList() {
        ArrayList<Country> countries = new ArrayList<Country>();
        String[] countryCodes = Locale.getISOCountries();
        for (String countryCode : countryCodes) {
            Locale obj = new Locale("", countryCode); 
            countries.add(new Country(obj.getDisplayCountry(), obj.getISO3Country()));
        }
        return countries;
    }

    @Override
    public String toString() {
        return name + " (" + isoCode + ")";
    }
}

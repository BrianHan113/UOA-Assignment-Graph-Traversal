package nz.ac.auckland.se281;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** This class is the main entry point. */
public class MapEngine {

  private Map<String, Country> countryMap;
  private Graph<Country> worldGraph;

  public MapEngine() {
    // HashSet is ideal for checking if an element is in it
    countryMap = new HashMap<>();
    worldGraph = new Graph<>();

    loadMap(); // keep this mehtod invocation
  }

  /** invoked one time only when constracting the MapEngine class. */
  private void loadMap() {
    // Read countries and adjacencies from provided files
    List<String> countries = Utils.readCountries();
    List<String> adjacencies = Utils.readAdjacencies();

    // Make country vertexes
    for (String countryEntry : countries) {
      String[] country = countryEntry.split(",");
      Country newCountry = new Country(country[0], country[1], Integer.parseInt(country[2]));
      countryMap.put(country[0], newCountry);
    }

    for (String adjacency : adjacencies) {
      String[] countriesNames = adjacency.split(",");

      String rootCountry = countriesNames[0];

      for (int i = 1; i < countriesNames.length; i++) {
        worldGraph.addEdge(countryMap.get(rootCountry), countryMap.get(countriesNames[i]));
      }
    }

    // System.out.println(worldGraph.map);
  }

  /** this method is invoked when the user run the command info-country. */
  public void showInfoCountry() {

    while (true) {
      try {
        Country country = getUserInputCountry();
        MessageCli.COUNTRY_INFO.printMessage(
            country.getName(), country.getContinent(), Integer.toString(country.getTaxRate()));
        break;
      } catch (CountryNotFound e) {
        System.out.println(e.getMessage());
      }
    }
  }

  /** this method is invoked when the user run the command route. */
  public void showRoute() {}

  private Country getUserInputCountry() throws CountryNotFound {
    MessageCli.INSERT_COUNTRY.printMessage();
    String inputCountry = Utils.capitalizeFirstLetterOfEachWord(Utils.scanner.nextLine());

    Country country = countryMap.get(inputCountry);
    if (country == null) {
      throw new CountryNotFound(inputCountry);
    }

    return country;
  }
}

class CountryNotFound extends Exception {
  public CountryNotFound(String countryName) {
    super(MessageCli.INVALID_COUNTRY.getMessage(countryName));
  }
}

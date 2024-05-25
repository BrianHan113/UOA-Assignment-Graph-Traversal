package nz.ac.auckland.se281;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/** This class is the main entry point. */
public class MapEngine {

  private Map<String, Country> countryMap;
  private Graph<Country> worldGraph;

  /** Constructor for the MapEngine class. */
  public MapEngine() {
    countryMap = new HashMap<>(); // Hashmap gets country by String input easy, and O(1)
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

    // Make country edges
    for (String adjacency : adjacencies) {
      String[] countriesNames = adjacency.split(",");

      String rootCountry = countriesNames[0];

      for (int i = 1; i < countriesNames.length; i++) {
        worldGraph.addEdge(countryMap.get(rootCountry), countryMap.get(countriesNames[i]));
      }
    }
  }

  /** this method is invoked when the user run the command info-country. */
  public void showInfoCountry() {

    // Loop until a valid country is entered by user
    while (true) {
      try {
        MessageCli.INSERT_COUNTRY.printMessage();
        Country country = getUserInputCountry();

        // Print country info
        MessageCli.COUNTRY_INFO.printMessage(
            country.getName(), country.getContinent(), Integer.toString(country.getTaxRate()));
        break;
      } catch (CountryNotFoundException e) {
        System.out.println(e.getMessage());
      }
    }
  }

  /** this method is invoked when the user run the command route. */
  public void showRoute() {

    Country startCountry = null;
    Country destinationCountry = null;
    Boolean firstInputValid = false;

    // Loop until two valid countries are entered by user
    while (true) {
      try {
        // Dont ask for the first country again if it was valid
        if (!firstInputValid) {
          MessageCli.INSERT_SOURCE.printMessage();
          startCountry = getUserInputCountry();
        }
        firstInputValid = true;

        MessageCli.INSERT_DESTINATION.printMessage();
        destinationCountry = getUserInputCountry();

        break;
      } catch (CountryNotFoundException e) {
        System.out.println(e.getMessage());
      }
    }

    List<Country> fastestRoute = worldGraph.findPathBetween(startCountry, destinationCountry);

    // If the fastest route is only one country, no cross-border travel is required
    if (fastestRoute.size() == 1) {
      MessageCli.NO_CROSSBORDER_TRAVEL.printMessage();
      return;
    }

    MessageCli.ROUTE_INFO.printMessage(fastestRoute.toString());

    // Find all continents visited
    Set<String> continents = new LinkedHashSet<>(); // Set: avoid duplicates linked: preserve order
    for (Country country : fastestRoute) {
      continents.add(country.getContinent());
    }
    MessageCli.CONTINENT_INFO.printMessage(continents.toString());

    // Calculate total tax
    int totalTax = 0;
    for (int i = 1; i < fastestRoute.size(); i++) { // Start from 1 to skip the first country taxes
      totalTax += fastestRoute.get(i).getTaxRate();
    }
    MessageCli.TAX_INFO.printMessage(Integer.toString(totalTax));
  }

  /**
   * Get user input country and return the corresponsding country object.
   *
   * @return the Country object corresponding to the user input.
   * @throws CountryNotFoundException if the country corresponding to the user input doesnt exist in
   *     the worldMap.
   */
  private Country getUserInputCountry() throws CountryNotFoundException {

    // Capitalize first letter of each word from input
    String inputCountry = Utils.capitalizeFirstLetterOfEachWord(Utils.scanner.nextLine());
    Country country = countryMap.get(inputCountry);

    // If country is not found, throw custom exception
    if (country == null) {
      throw new CountryNotFoundException(inputCountry);
    }

    return country;
  }
}

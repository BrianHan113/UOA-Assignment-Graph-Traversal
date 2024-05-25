package nz.ac.auckland.se281;

/** This exception is thrown when a country is not found. */
class CountryNotFoundException extends Exception {
  public CountryNotFoundException(String countryName) {
    super(MessageCli.INVALID_COUNTRY.getMessage(countryName));
  }
}

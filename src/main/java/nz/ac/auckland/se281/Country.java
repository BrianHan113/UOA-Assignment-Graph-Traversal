package nz.ac.auckland.se281;

/** Represents a country. */
public class Country {

  private String name;
  private String continent;
  private int taxRate;

  /**
   * Constructor for the Country class.
   *
   * @param name The name of the country.
   * @param continent The continent the country is in.
   * @param taxRate The tax rate of the country.
   */
  public Country(String name, String continent, int taxRate) {
    this.name = name;
    this.continent = continent;
    this.taxRate = taxRate;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getContinent() {
    return continent;
  }

  public void setContinent(String continent) {
    this.continent = continent;
  }

  public int getTaxRate() {
    return taxRate;
  }

  public void setTaxRate(int taxRate) {
    this.taxRate = taxRate;
  }

  /**
   * Returns a hash code value for the object based on the country name, continent, and tax rate.
   *
   * @return a hash code value for this object
   */
  @Override
  public int hashCode() {
    // Hash code is calculated based on the country name, continent and tax rate
    final int prime = 31;
    int result = 1;
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result + ((continent == null) ? 0 : continent.hashCode());
    result = prime * result + taxRate;
    return result;
  }

  /**
   * Compares this object to the specified object for equality. The result is true if and only if
   * the argument is not null and is a {@link Country} object that has the same name, continent, and
   * tax rate as this object.
   *
   * @param obj the object to compare this {@link Country} against
   * @return true if the given object represents a {@link Country} equivalent to this country, false
   *     otherwise
   */
  @Override
  public boolean equals(Object obj) {
    // Check for reference equality
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }

    Country other = (Country) obj;

    // Check for name equality
    if (name == null) {
      if (other.name != null) {
        return false;
      }
    } else if (!name.equals(other.name)) {
      return false;
    }

    // Check for continent equality
    if (continent == null) {
      if (other.continent != null) {
        return false;
      }
    } else if (!continent.equals(other.continent)) {
      return false;
    }

    // Check for tax rate equality
    if (taxRate != other.taxRate) {
      return false;
    }

    return true;
  }

  /**
   * Returns a string representation of the country.
   *
   * @return a string representation of the country
   */
  @Override
  public String toString() {
    return name;
  }
}

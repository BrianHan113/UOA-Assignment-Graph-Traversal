package nz.ac.auckland.se281;

public class Country {

  private String name;
  private String continent;
  private int taxRate;

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

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result + ((continent == null) ? 0 : continent.hashCode());
    result = prime * result + taxRate;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    Country other = (Country) obj;
    if (name == null) {
      if (other.name != null) return false;
    } else if (!name.equals(other.name)) return false;
    if (continent == null) {
      if (other.continent != null) return false;
    } else if (!continent.equals(other.continent)) return false;
    if (taxRate != other.taxRate) return false;
    return true;
  }

  @Override
  public String toString() {
    return name;
  }
}

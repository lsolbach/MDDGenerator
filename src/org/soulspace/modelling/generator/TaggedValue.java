package org.soulspace.modelling.generator;

/**
 * Datatype for TaggedValues used include or exclude specific
 * TaggedValues for generation.
 * @author soulman
 *
 */
public class TaggedValue {
  String name;
  String value;
  // List values; ??
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public String getValue() {
    return value;
  }
  
  public void setValue(String value) {
    this.value = value;
  }
}

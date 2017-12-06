package de.draegerit.vsp.response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StorageItem {

  private String token;

  private List<String> expectedKeys;

  private Map<Long, Item> values = new HashMap<>();

  public final List<String> getExpectedKeys() {
    return expectedKeys;
  }

  public final void setExpectedKeys(List<String> inExpectedKeys) {
    this.expectedKeys = inExpectedKeys;
  }

  public final String getToken() {
    return token;
  }

  public final void setToken(String inToken) {
    this.token = inToken;
  }

  public final Map<Long, Item> getValues() {
    return values;
  }

  public final void setValues(Map<Long, Item> inValues) {
    this.values = inValues;
  }

}

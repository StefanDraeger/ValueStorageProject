package de.draegerit.vsp.response;

import java.util.HashMap;
import java.util.Map;

public class Item {
  private long timestamp;

  private Map<String, String> values = new HashMap<>();

  public final long getTimestamp() {
    return timestamp;
  }

  public final void setTimestamp(long inTimestamp) {
    this.timestamp = inTimestamp;
  }

  public final Map<String, String> getValues() {
    return values;
  }

  public final void setValues(Map<String, String> inValues) {
    this.values = inValues;
  }

}

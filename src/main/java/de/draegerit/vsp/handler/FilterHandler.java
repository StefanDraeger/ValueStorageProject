package de.draegerit.vsp.handler;

import de.draegerit.vsp.VSPServerConstants;
import de.draegerit.vsp.json.JSONHandler;
import de.draegerit.vsp.response.Item;
import de.draegerit.vsp.response.StorageItem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import org.eclipse.jetty.server.Request;

public class FilterHandler extends AbstractVSPHandler {

  @Override
  public final void handle(Request baseRequest, HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {

    String timeFrom = getValueFromParameter(request, VSPServerConstants.PARAM_TIME_FROM);
    String timeTo = getValueFromParameter(request, VSPServerConstants.PARAM_TIME_TO);

    String valueFrom = getValueFromParameter(request, VSPServerConstants.PARAM_VALUE_FROM);
    String valueTo = getValueFromParameter(request, VSPServerConstants.PARAM_VALUE_TO);

    String token = getToken(request);
    String message = VSPServerConstants.UNDEFINED;
    if (token != null) {
      StorageItem item = JSONHandler.getFromJson(token);
      if (item != null) {
        List<Item> items = new ArrayList<>(
            item.getValues().entrySet().stream().map(i -> i.getValue()).collect(Collectors.toList()));

        List<Item> resultItems = new ArrayList<>();

        if (timeFrom != null && timeTo != null) {
          resultItems.addAll(filterByTimeFromAndTimeTo(items, Long.parseLong(timeFrom), Long.parseLong(timeTo)));
        } else if (timeFrom != null && timeTo == null) {
          resultItems.addAll(filterByTimeFrom(items, Long.parseLong(timeFrom)));
        } else if (timeFrom == null && timeTo != null) {
          resultItems.addAll(filterByTimeTo(items, Long.parseLong(timeTo)));
        } else if (timeFrom == null && timeTo == null) {
          resultItems.addAll(items);
        }

        // if (resultItems.isEmpty() && (timeFrom != null && timeTo != null)) {
        items = resultItems;
        // }

        if (!items.isEmpty() && (valueFrom != null || valueTo != null)) {
          resultItems.clear();

          Double vF = valueFrom != null ? Double.parseDouble(valueFrom) : 0d;
          Double vT = valueTo != null ? Double.parseDouble(valueTo) : 0d;

          if (valueFrom != null && valueTo != null) {
            resultItems.addAll(filterByValueFromAndValueTo(items, vF, vT));
          } else if (valueFrom != null && valueTo == null) {
            resultItems.addAll(filterByValueFrom(items, vF));
          } else if (valueFrom == null && valueTo != null) {
            resultItems.addAll(filterByValueTo(items, vT));
          }
        }

        message = new Gson().toJson(resultItems);
      }
    }
    response.getWriter().println(message);
  }

  private List<Item> filterByValueTo(List<Item> items, Double vT) {
    List<Item> result = new ArrayList<>();
    for (Item item : items) {
      Map<String, String> values = item.getValues();
      for (Entry<String, String> entry : values.entrySet()) {
        String value = entry.getValue();
        Double d = Double.parseDouble(value);
        if (d <= vT) {
          result.add(item);
        }
      }
    }

    return result;
  }

  private List<Item> filterByValueFrom(List<Item> items, Double vF) {
    List<Item> result = new ArrayList<>();
    for (Item item : items) {
      Map<String, String> values = item.getValues();
      for (Entry<String, String> entry : values.entrySet()) {
        String value = entry.getValue();
        Double d = Double.parseDouble(value);
        if (d >= vF) {
          result.add(item);
        }
      }
    }

    return result;
  }

  private List<Item> filterByValueFromAndValueTo(List<Item> items, Double vF, Double vT) {
    List<Item> result = new ArrayList<>();
    for (Item item : items) {
      Map<String, String> values = item.getValues();
      for (Entry<String, String> entry : values.entrySet()) {
        String value = entry.getValue();
        Double d = Double.parseDouble(value);
        if (d >= vF && d <= vT) {
          result.add(item);
        }
      }
    }

    return result;
  }

  private List<Item> filterByTimeTo(List<Item> items, long timeTo) {
    return items.stream().filter(sitem -> sitem.getTimestamp() <= timeTo).collect(Collectors.toList());
  }

  private List<Item> filterByTimeFrom(List<Item> items, long timeFrom) {
    return items.stream().filter(sitem -> sitem.getTimestamp() >= timeFrom).collect(Collectors.toList());
  }

  private List<Item> filterByTimeFromAndTimeTo(List<Item> items, long timeFrom, long timeTo) {
    return items.stream().filter(sitem -> sitem.getTimestamp() >= timeFrom && sitem.getTimestamp() <= timeTo)
        .collect(Collectors.toList());
  }

  private String getValueFromParameter(HttpServletRequest request, String parameter) {
    String[] str = request.getParameterMap().get(parameter);
    return str != null ? str[0] : null;
  }
}

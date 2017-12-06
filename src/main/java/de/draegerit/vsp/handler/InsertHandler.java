package de.draegerit.vsp.handler;

import de.draegerit.vsp.VSPServerConstants;
import de.draegerit.vsp.json.JSONHandler;
import de.draegerit.vsp.response.Item;
import de.draegerit.vsp.response.StorageItem;
import de.draegerit.vsp.util.MessageUtil;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;

public class InsertHandler extends AbstractVSPHandler {

  public final void handle(Request baseRequest, HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    boolean allRequestedParamsAvailable = checkForRequestedParams(request.getParameterMap());
    boolean insertSuccessfull = true;
    String resultMessage = VSPServerConstants.UNDEFINED;
    if (allRequestedParamsAvailable) {
      if (!request.getParameterMap().isEmpty()) {
        String token = getToken(request);
        if (token != null) {
          StorageItem item = JSONHandler.getFromJson(token);
          if (item != null && checkRequestForExpectedKeys(request.getParameterMap(), item.getExpectedKeys())) {
            inserValue(request.getParameterMap(), item);
            JSONHandler.storeItem(item);
            resultMessage = MessageUtil.getInsertDoneMessage();
          } else {
            insertSuccessfull = false;
          }
        }
      }
    }

    if (!insertSuccessfull || !allRequestedParamsAvailable) {
      resultMessage = MessageUtil.getInsertFailMessage();
    }

    response.getWriter().println(resultMessage);
  }

  private void inserValue(Map<String, String[]> parameterMap, StorageItem item) {
    for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
      String key = entry.getKey();
      if (key.equalsIgnoreCase(VSPServerConstants.PARAM_TOKEN)) {
        continue;
      }
      String value = entry.getValue()[0];
      Map<String, String> values = new LinkedHashMap<>();
      values.put(key, value);

      Item vItem = new Item();
      vItem.setTimestamp(System.currentTimeMillis());
      vItem.setValues(values);

      long valuecount = item.getValues().size() + 1;
      item.getValues().put(valuecount, vItem);
    }

  }

  private boolean checkRequestForExpectedKeys(Map<String, String[]> parameterMap, List<String> expectedKeys) {
    List<String> keys = parameterMap.keySet().stream().collect(Collectors.toList());
    return keys.containsAll(expectedKeys);
  }

  private boolean checkForRequestedParams(Map<String, String[]> parameterMap) {
    return parameterMap.get(VSPServerConstants.PARAM_TOKEN) != null;
  }

}

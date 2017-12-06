package de.draegerit.vsp;

import java.util.Arrays;
import java.util.List;

public final class Main {

  private enum StartParameter {
    PORT("-port"), SHTPWD("-shutdownpwd");

    private String parameter;

    StartParameter(String param) {
      this.setParameter(param);
    }

    public String getParameter() {
      return parameter;
    }

    public void setParameter(String inParameter) {
      this.parameter = inParameter;
    }
  }

  private static VSPServer server;

  private Main() {
  }

  public static void main(String[] args) {
    String portParam = getParamFromArgs(args, StartParameter.PORT);
    int port = portParam != null ? Integer.parseInt(portParam) : VSPServer.DEFAULT_PORT;

    String shutdownPwdParam = getParamFromArgs(args, StartParameter.SHTPWD);
    String shutdownPwd = shutdownPwdParam != null ? shutdownPwdParam : VSPServer.DEFAULT_SHUTDOWN_PWD;

    server = new VSPServer(port, shutdownPwd);
    server.start();
  }

  private static String getParamFromArgs(String[] args, StartParameter startParameter) {
    String startParameterValue = startParameter.getParameter();

    List<String> params = Arrays.asList(args);
    String parameter = params.stream().filter(param -> param.startsWith(startParameterValue)).findAny().orElse(null);
    return parameter != null ? parameter.substring(startParameterValue.length() + 1, parameter.length()) : null;
  }

}

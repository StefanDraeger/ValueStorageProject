package de.draegerit.vsp;

import java.util.Arrays;

/**
 * Hauptklasse zum starten des Projektes "ValueStorageProject"
 *
 * @author Stefan Draeger
 * @since 06.12.2017
 */
public final class Main { // NO_UCD (unused code)

  /**
   * Startparameter
   *
   * @author Stefan Draeger
   * @since 06.12.2017
   *
   */
  private enum StartParameter {
    PORT("-port"), SHTPWD("-shutdownpwd");

    private String parameter;

    StartParameter(String param) {
      parameter = param;
    }

    /**
     * Liefert den Wert für den Parameter.
     *
     * @return String
     */
    public String getParameter() {
      return parameter;
    }

  }

  /**
   * Privater Konstruktor.
   */
  private Main() {
    // Empty
  }

  /**
   * Startmethode
   *
   * @param args
   *          - String Array mit den Parameter welche der Anwendung mitgegeben
   *          wurde.
   */
  public static void main(String[] args) {
    String portParam = getParamFromArgs(args, StartParameter.PORT);
    int port = portParam != null ? Integer.parseInt(portParam) : VSPServer.DEFAULT_PORT;

    String shutdownPwdParam = getParamFromArgs(args, StartParameter.SHTPWD);
    String shutdownPwd = shutdownPwdParam != null ? shutdownPwdParam : VSPServer.DEFAULT_SHUTDOWN_PWD;

    new VSPServer(port, shutdownPwd).start();
  }

  private static String getParamFromArgs(String[] args, StartParameter startParameter) {
    String startParameterValue = startParameter.getParameter();

    String parameter = Arrays.asList(args).stream().filter(param -> param.startsWith(startParameterValue)).findAny()
        .orElse(null);
    return parameter != null ? parameter.substring(startParameterValue.length() + 1, parameter.length()) : null;
  }

}

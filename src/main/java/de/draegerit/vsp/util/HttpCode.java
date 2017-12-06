package de.draegerit.vsp.util;

public enum HttpCode {

  _404(404), _200(200);

  private int code;

  HttpCode(int httpCode) {
    this.setCode(httpCode);
  }

  @Override
  public String toString() {
    return String.valueOf(getCode());
  }

  public int getCode() {
    return code;
  }

  public void setCode(int inCode) {
    this.code = inCode;
  }

}

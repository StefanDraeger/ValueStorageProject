package de.draegerit.vsp.response;

import de.draegerit.vsp.util.HttpCode;

abstract class AbstractResponse {

  private int code;

  private HttpCode httpcode;

  AbstractResponse(int inCode, HttpCode inHttpcode) {
    super();
    this.code = inCode;
    this.httpcode = inHttpcode;
  }

  public final int getCode() {
    return code;
  }

  public final void setCode(int inCode) {
    this.code = inCode;
  }

  public final HttpCode getHttpcode() {
    return httpcode;
  }

  public final void setHttpcode(HttpCode inHttpcode) {
    this.httpcode = inHttpcode;
  }

}

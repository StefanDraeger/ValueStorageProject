package de.draegerit.vsp.response;

import de.draegerit.vsp.util.HttpCode;

public class Message extends AbstractResponse {

  private String text;

  public Message(String inText, int inCode, HttpCode inHttpcode) {
    super(inCode, inHttpcode);
    this.text = inText;
  }

  public final String getText() {
    return text;
  }

  public final void setText(String inText) {
    this.text = inText;
  }

}

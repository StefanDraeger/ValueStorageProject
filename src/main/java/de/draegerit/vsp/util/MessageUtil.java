package de.draegerit.vsp.util;

import de.draegerit.vsp.json.JSONHandler;
import de.draegerit.vsp.response.Message;

import com.google.gson.Gson;

public final class MessageUtil {

  private static final int NUM400 = 400;
  private static final int NUM200 = 200;

  private MessageUtil() {

  }

  public static String getStopMessage() {
    return messageToJson(new Message("Server will be shutdown!", NUM200, HttpCode._200));
  }

  public static String messageToJson(Message msg) {
    Gson gson = new Gson();
    return gson.toJson(msg);
  }

  public static Message jsonToMessage(String json) {
    return new Gson().fromJson(json, Message.class);
  }

  public static String getStopFailedPwdMessage() {
    return messageToJson(new Message("Password is empty or wrong, Server is still running!", NUM400, HttpCode._200));
  }

  public static String getHelloMessage() {
    return messageToJson(new Message("Server is running!", NUM200, HttpCode._200));
  }

  public static String getInsertDoneMessage() {
    return messageToJson(new Message("Insert successfull!", NUM200, HttpCode._200));
  }

  public static String getInsertFailMessage() {
    return messageToJson(new Message("Insert failed!", NUM400, HttpCode._200));
  }

  public static String getCreateFailedMessage() {
    return messageToJson(new Message("Create failed!", NUM400, HttpCode._200));
  }

  public static String getCreateSuccessfullMessage(String md5Hash) {
    return messageToJson(new Message("Create successfull, token is " + md5Hash + "!", NUM200, HttpCode._200));
  }

  public static String getDeleteFailedMessage(String token) {
    String filename = JSONHandler.OUTPUT_DIR + token + JSONHandler.JSON_SUFFIX;
    return messageToJson(new Message("File [" + filename + "] can't be deleted!", NUM400, HttpCode._200));
  }

  public static String getDeleteSuccessfullMessage(String token) {
    String filename = JSONHandler.OUTPUT_DIR + token + JSONHandler.JSON_SUFFIX;
    return messageToJson(new Message("File [" + filename + "] successfull deleted!", NUM200, HttpCode._200));
  }

}

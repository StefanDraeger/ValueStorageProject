package de.draegerit.vsp.util;

import de.draegerit.vsp.VSPServerConstants;
import de.draegerit.vsp.json.JSONHandler;
import de.draegerit.vsp.response.Message;

import com.google.gson.Gson;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class MessageUtil {

  private static Logger logger = LoggerFactory.getLogger("MessageUtil.class");

  private MessageUtil() {

  }

  public static String getStopMessage() {
    return messageToJson(new Message("Server will be shutdown!", VSPServerConstants.NUM200, HttpCode._200));
  }

  public static String messageToJson(Message msg) {
    Gson gson = new Gson();
    String message = gson.toJson(msg);
    if (logger.isDebugEnabled()) {
      logger.debug(message);
    }
    return message;
  }

  public static Message jsonToMessage(String json) {
    return new Gson().fromJson(json, Message.class);
  }

  public static String getStopFailedPwdMessage() {
    return messageToJson(new Message("Password is empty or wrong, Server is still running!", VSPServerConstants.NUM400, HttpCode._200));
  }

  public static String getHelloMessage() {
    return messageToJson(new Message("Server is running!", VSPServerConstants.NUM200, HttpCode._200));
  }

  public static String getInsertDoneMessage() {
    return messageToJson(new Message("Insert successfull!", VSPServerConstants.NUM200, HttpCode._200));
  }

  public static String getInsertFailMessage() {
    return messageToJson(new Message("Insert failed!", VSPServerConstants.NUM400, HttpCode._200));
  }

  public static String getCreateFailedMessage() {
    return messageToJson(new Message("Create failed!", VSPServerConstants.NUM400, HttpCode._200));
  }

  public static String getCreateSuccessfullMessage(String md5Hash) {
    return messageToJson(new Message("Create successfull, token is " + md5Hash + "!", VSPServerConstants.NUM200, HttpCode._200));
  }

  public static String getDeleteFailedMessage(String token) {
    String filename = JSONHandler.OUTPUT_DIR + token + JSONHandler.JSON_SUFFIX;
    return messageToJson(new Message("File [" + filename + "] can't be deleted!", VSPServerConstants.NUM400, HttpCode._200));
  }

  public static String getDeleteSuccessfullMessage(String token) {
    String filename = JSONHandler.OUTPUT_DIR + token + JSONHandler.JSON_SUFFIX;
    return messageToJson(new Message("File [" + filename + "] successfull deleted!", VSPServerConstants.NUM200, HttpCode._200));
  }

}

package com.jason;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

/**
 * Hello world!
 *
 */
public class App {

  private static Logger logger = LoggerFactory.getLogger(App.class);
  static {
    MDC.put("userId", "jason98bae");
  }

  public static void main( String[] args ) {
    insertImageInfo("123456", " <Some Image ''URL> ", "  ");
  }

  /**
   * Insert image's info to `IMAGE` table.
   * 
   * @param contentId COT_ID.
   * @param url Image's URL.
   * @param description Image's description.
   */
  protected static void insertImageInfo(String contentId, String url, String description) {
    if (url == null || url.length() == 0) return;
    String dmlFormat = "insert into IMAGE (IMG_ID, COT_ID, IMAGE_DESCRIPTION, URL) values ('%s', '%s', '%s', '%s')";
    String imageId = UUID.randomUUID().toString();
    String script = String.format(dmlFormat, imageId, contentId,
        removeUselessCharacters(description), removeUselessCharacters(url));

    logger.info("#insertImageInfo() dml script : \"{}\"", script);
  }

  private static String removeUselessCharacters(String src) {
    String result = src.replaceAll("^\\s*|\'|\\s*$", "");  // surports trimed string
    if (result.length() == 0) return null;
    return result;
  }
}

package com.apiautomation.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConfigurationHolder {

  private static final String APP_PROPERTIES = "apiautomation.properties";
  private static Properties properties;
  private static ConfigurationHolder instance;

  private ConfigurationHolder() {
    properties = new Properties();

    try (InputStream resourceAsStream = Thread.currentThread()
                                              .getContextClassLoader()
                                              .getResourceAsStream(APP_PROPERTIES)) {

      properties.load(resourceAsStream);
      log.debug(APP_PROPERTIES + " file is read");
    } catch (IOException e) {
      log.error(APP_PROPERTIES + " file can not read", e);
    }
  }

  public static ConfigurationHolder getInstance() {
    if (instance == null) {
      instance = new ConfigurationHolder();
    }
    return instance;
  }

  public String getProperty(String var1) {
    return properties.getProperty(var1);
  }

}


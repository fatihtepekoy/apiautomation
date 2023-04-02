package com.apiautomation.operation;

import com.apiautomation.models.BaseOperation;
import com.apiautomation.models.Transaction;
import io.restassured.response.Response;

public class DeviceOperations extends BaseOperation {

  public DeviceOperations() {
    super();
  }

  public static Response create(Transaction transaction) {
    return postRequest(transaction);
  }

  public static Response update(Transaction transaction) {
    return putRequest(transaction);
  }

  public static Response getDevices(Transaction transaction) {
    return getRequest(transaction);
  }
}


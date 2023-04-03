package com.apiautomation.operation;

import com.apiautomation.models.BaseOperation;
import com.apiautomation.models.HttpResponse;
import com.apiautomation.models.Transaction;

public class DeviceOperations extends BaseOperation {

  public DeviceOperations() {
    super();
  }

  public static HttpResponse create(Transaction transaction) {
    return postRequest(transaction);
  }

  public static HttpResponse update(Transaction transaction) {
    return putRequest(transaction);
  }

  public static HttpResponse getDevices(Transaction transaction) {
    return getRequest(transaction);
  }
}


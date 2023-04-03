package com.apiautomation.stepdefinition;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.apiautomation.helper.Helper;
import com.apiautomation.models.AbstractSteps;
import com.apiautomation.models.dto.request.DeviceRequestDTO;
import com.apiautomation.models.testcontext.DeviceContext;
import com.apiautomation.operation.DeviceOperations;
import com.apiautomation.util.Endpoint;
import com.apiautomation.util.Utils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class DeviceRestControllerSteps extends AbstractSteps {

  private final DeviceContext deviceContext;

  public DeviceRestControllerSteps(DeviceContext deviceContext) {
    this.deviceContext = deviceContext;
  }

  @And("a device is already available")
  public void aDeviceIsAlreadyAvailable() {
    DeviceRequestDTO deviceRequestDTO = DeviceRequestDTO.generateDevicePayload();
    deviceContext.setDeviceRequestDTO(deviceRequestDTO);
    deviceContext.setDeviceResponseDTO(Helper.createDevice(deviceRequestDTO));
  }

  @Given("a list of devices are already available")
  public void aListOfDevicesAreAlreadyAvailable() {
    deviceContext.getDeviceIds()
                 .add(Helper.createDevice(DeviceRequestDTO.generateDevicePayload()).getId());
    deviceContext.getDeviceIds()
                 .add(Helper.createDevice(DeviceRequestDTO.generateDevicePayload()).getId());
  }


  @Given("device objects endpoint")
  public void deviceObjectsEndpoint() {
    transaction.initRequestWithJsonHeaders();
    transaction.setUrl(Endpoint.OBJECTS.getEndpoint());
  }

  @Given("device objects endpoint with id")
  public void deviceObjectsEndpointWithId() {
    transaction.initRequestWithJsonHeaders();
    Map<String, String> parameters = new HashMap<>();
    parameters.put("id", deviceContext.getDeviceResponseDTO().getId());
    transaction.setUrl(Utils.generateUrl(Endpoint.OBJECTS_WITH_ID.getEndpoint(), parameters));
  }

  @And("device objects endpoint with multiple id")
  public void deviceObjectsEndpointWithMultipleId() {
    transaction.initRequestWithJsonHeaders();
    List<NameValuePair> parameters = deviceContext.getDeviceIds()
                                                  .stream()
                                                  .map(deviceId -> new BasicNameValuePair("id",
                                                      deviceId))
                                                  .collect(Collectors.toList());
    transaction.setUrl(Utils.addQuery2Url(Endpoint.OBJECTS.getEndpoint(), parameters));
  }

  @And("a device payload with valid values")
  public void aDevicePayloadWithValidValues() {
    transaction.setObjectPayload(DeviceRequestDTO.generateDevicePayload());
  }

  @When("the device is created")
  public void theDeviceIsCreated() {
    transaction.setResponse(DeviceOperations.create(transaction));
  }

  @When("the device is listed")
  public void theDeviceIsListed() {
    transaction.setResponse(DeviceOperations.getDevices(transaction));
  }

  @When("the device is updated")
  public void theDeviceIsUpdated() {
    transaction.setResponse(DeviceOperations.update(transaction));
  }

  @When("the devices are listed")
  public void theDevicesAreListed() {
    transaction.setResponse(DeviceOperations.getDevices(transaction));
  }

  @And("a device payload with different name {string}")
  public void aDevicePayloadWithDifferentName(String name) {
    DeviceRequestDTO deviceRequestDTO = DeviceRequestDTO.generateDevicePayload();
    deviceRequestDTO.setName(name);
    deviceContext.setDeviceRequestDTO(deviceRequestDTO);
    transaction.setObjectPayload(deviceRequestDTO);
  }

  @And("the returned device name is same {string} with the we sent")
  public void theReturnedDeviceNameIsSameWithTheWeSent(String name) {
    assertThat(transaction.getValueFromResponse("name"))
        .isEqualTo(deviceContext.getDeviceRequestDTO().getName());
  }

  @And("the returned device id is same with the we created")
  public void theReturnedDeviceIdIsSameWithTheWeCreated() {
    assertThat(transaction.getValueFromResponse("id"))
        .isEqualTo(deviceContext.getDeviceResponseDTO().getId());
  }

  @And("the returned devices contains the devices that we created")
  public void theReturnedDevicesContainsTheDevicesThatWeCreated() {
    assertThat(transaction.getValueListFromResponse("id")
                          .containsAll(deviceContext.getDeviceIds())).isTrue();
  }
}


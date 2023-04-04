package com.apiautomation.stepdefinition;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.apiautomation.helper.Helper;
import com.apiautomation.models.AbstractSteps;
import com.apiautomation.models.dto.request.DeviceRequestDTO;
import com.apiautomation.models.testcontext.DeviceContext;
import com.apiautomation.operation.DeviceApi;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

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

  @And("a device payload with valid values")
  public void aDevicePayloadWithValidValues() {
    transaction.setObjectPayload(DeviceRequestDTO.generateDevicePayload());
  }

  @When("the device is created")
  public void theDeviceIsCreated() {
    transaction.setResponse(DeviceApi.create(transaction, deviceContext));
  }

  @When("the device is listed")
  public void theDeviceIsListed() {
    transaction.setResponse(DeviceApi.getDevice(transaction, deviceContext));
  }

  @When("the device is updated")
  public void theDeviceIsUpdated() {
    transaction.setResponse(DeviceApi.update(transaction, deviceContext));
  }

  @When("the all devices are listed")
  public void theDevicesAreListed() {
    transaction.setResponse(DeviceApi.getDevices(transaction));
  }


  @When("the some devices are listed")
  public void theSomeDevicesAreListed() {
    transaction.setResponse(DeviceApi.getSomeDevices(transaction, deviceContext));
  }

  @Given("a device payload with following name {string}")
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


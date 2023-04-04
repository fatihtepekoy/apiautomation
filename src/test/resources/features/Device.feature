Feature: Real REST API which is ready to handle your HTTP requests 24/7 for free -  https://restful-api.dev/

  Rule: Create device

    @smokeTest
    Scenario: Create a device with valid values
      Given a device payload with valid values
      When the device is created
      Then the response code is "200"
      And the response has a ID field


    Scenario Outline: Create a device with different name values
      Given a device payload with following name "<values>"
      When the device is created
      Then the response code is "<ReturnCode>"
      Examples:
        | Desc          | ReturnCode | values                                                                                                                                                                                                                                                            |
        | too long      | 500        | Bp9DdRdVlg7SeBpNDjnBqPDNcCIRCvj0IZ7TVuHfLG27b5MIzzrjY9RVb57FD2EF7TRc2oJjIiBk0MvancINcLzbWZS2oRkDwOkEtovGoBVQp8hk4dye94ddR8uATDPRE2yvAzCC5AKqznOQiEC2Kkk6AUbqZvGFzo1bqpbdWDlgPIfDZGTMJb1N0KZtsztYMs4dELDRJ2zl6WXH6cjFxtzz6jVzhu3m1GDmETy31VS75YWVIkAb9sy1VuHo278f8 |
        | too short     | 200        | a                                                                                                                                                                                                                                                                 |
        | empty         | 200        | a                                                                                                                                                                                                                                                                 |
        | special chars | 200        | _?=)(/&%%+^'                                                                                                                                                                                                                                                      |


  Rule: Update device

    Scenario Outline: Update the device with different name values
      Given a device is already available
      And a device payload with following name "<value>"
      When the device is updated
      Then the response code is "<ReturnCode>"
      And the returned device name is same "<value>" with the we sent
      Examples:
        | Desc          | ReturnCode | value                               |
        | too long      | 200        | Apple MacBook Pro 16 (Updated Name) |
        | special chars | 200        | _?=)(/&%%+^'                        |




    Rule: List device
    Scenario: List device
      Given a device is already available
      When the device is listed
      Then the response code is "200"
      And the returned device id is same with the we created


    Scenario: List devices
      Given a list of devices are already available
      When the all devices are listed
      Then the response code is "200"
#      And the returned devices contains the devices that we created

      @Run
    Scenario: List some devices
        Given a list of devices are already available
        When the some devices are listed
        Then the response code is "200"
      And the returned devices contains the devices that we created

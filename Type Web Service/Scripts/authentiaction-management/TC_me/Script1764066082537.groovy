import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

def jsonPass
'Gửi request me với status là 200'
ResponseObject responseMe=WS.sendRequest(findTestObject('authentication-management/me'))

WS.verifyResponseStatusCode(responseMe, Integer.parseInt(statusCode))

'Lưu id vào global để phục vụ cho call test case'
GlobalVariable.id=WS.getElementPropertyValue(responseMe, 'id')

jsonPass=
'''
{
  "$schema": "http://json-schema.org/draft-07/schema#",
  "type": "object",
  "properties": {
    "id": {
      "type": "string"
    },
    "name": {
      "type": "string"
    },
    "email": {
      "type": "string"
    },
    "avatarUrl": {
      "type": "string"
    },
    "phone": {
      "type": "string"
    },
    "address": {
      "type": "string"
    },
    "config": {}
  },
  "required": [
    "id",
    "name",
    "email",
    "avatarUrl",
    "phone",
    "address"
  ]
}
'''

'Lấy dữ liệu response'
String responseText=responseMe.getResponseText()

if(responseText.startsWith("{")) {
	boolean isValidSchema=WS.validateJsonAgainstSchema(responseMe, jsonPass)
	assert isValidSchema: "JSON Schema Validation Failed!"
}
else {
	println("Trả về text không phải verify Schema")
}

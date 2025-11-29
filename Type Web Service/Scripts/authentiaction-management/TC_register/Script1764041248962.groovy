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

ResponseObject responseRegister = null
def jsonPass

if(statusCode.equals('201')) {
    responseRegister = WS.sendRequest(findTestObject('authentication-management/register', [
        ('name') : GlobalVariable.name, 
        ('email') : GlobalVariable.email, 
        ('password') : GlobalVariable.password,
        ('avatarUrl') : GlobalVariable.avatarUrl, 
        ('phone') : GlobalVariable.phone, 
        ('address') : GlobalVariable.address
    ]))
    
    jsonPass = '''
    {
      "$schema": "http://json-schema.org/draft-07/schema#",
      "type": "object",
      "properties": {
        "msg": {
          "type": "string"
        }
      },
      "required": [
        "msg"
      ]
    }
    '''
}
else {
    responseRegister = WS.sendRequest(findTestObject('authentication-management/register', [
        ('name') : name, 
        ('email') : email, 
        ('password') : password, 
        ('avatarUrl') : avatarUrl, 
        ('phone') : phone, 
        ('address') : address
    ]))
    
    jsonPass = '''
    {
      "$schema": "http://json-schema.org/draft-07/schema#",
      "type": "object",
      "properties": {
        "msg": {
          "type": "string"
        },
        "fields": {
          "type": "object",
          "patternProperties": {
            "^(.*)$": {
              "type": "array",
              "items": {
                "type": "string"
              }
            }
          }
        }
      },
      "required": [
        "msg",
        "fields"
      ]
    }
    '''
}

'Verify Status Code'
WS.verifyResponseStatusCode(responseRegister,  Integer.parseInt(statusCode) )

String responseText = responseRegister.getResponseText()

if (responseText.startsWith("{")) {
    'Verify Schema'
    boolean isValidSchema = WS.validateJsonAgainstSchema(responseRegister, jsonPass)
    assert isValidSchema : "JSON Schema Validation Failed!"
} else {
    println "⚠️ Server trả về Text (không phải JSON), bỏ qua validate schema: " + responseText
}
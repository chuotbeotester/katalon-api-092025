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

String accessTokenValue = null
ResponseObject responseLogin=null
def jsonPass

if(statusCode.equals('200')) {
	'Gửi request login với status là 200'
	responseLogin=WS.sendRequest(findTestObject('authentication-management/login', [('email') : GlobalVariable.email, ('password') : GlobalVariable.password]))
	'Lấy token'
	accessTokenValue = WS.getElementText(responseLogin, 'accessToken')
	
	GlobalVariable.token = accessTokenValue

	
	String myFile="data-test.xlsx"
	String sheetName="user-management"
	
	'Lưu email vào excel phục vụ cho get'
	HelperKeyword.writeExcel(myFile, sheetName, 1, 2, GlobalVariable.email)
	
	'Lưu email vào excel phục vụ cho patch'
	HelperKeyword.writeExcel(myFile, sheetName, 3, 2, GlobalVariable.email)
	
	'Lưu password vào excel phục vụ cho get'
	HelperKeyword.writeExcel(myFile, sheetName, 1, 3, GlobalVariable.password)
	
	jsonPass=
	'''
	{
	  "$schema": "http://json-schema.org/draft-07/schema#",
	  "type": "object",
	  "properties": {
	    "msg": {
	      "type": "string"
	    },
	    "accessToken": {
	      "type": "string"
	    },
	    "exp": {
	      "type": "string"
	    }
	  },
	  "required": [
	    "msg",
	    "accessToken",
	    "exp"
	  ]
	}


	'''
}
else {
	'Gửi request login với status khác 200'
	responseLogin=WS.sendRequest(findTestObject('authentication-management/login', [('email') : email, ('password') : password]))
	
	jsonPass=
	'''
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
'Verify statusCode'
WS.verifyResponseStatusCode(responseLogin,statusCode.toInteger() )

String responseText = responseLogin.getResponseText()

if (responseText.startsWith("{")) {
    'Verify Json Schema'
    boolean isValidSchema = WS.validateJsonAgainstSchema(responseLogin, jsonPass)
    assert isValidSchema : "JSON Schema Validation Failed!"
} else {
    println "⚠️ Server trả về Text, bỏ qua schema: " + responseText
}
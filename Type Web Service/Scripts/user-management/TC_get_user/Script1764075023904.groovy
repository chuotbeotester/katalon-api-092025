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
import com.kms.katalon.core.testobject.ResponseObject as ResponseObject
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys


'Call test case login để có thể dùng me'
WebUI.callTestCase(findTestCase('authentiaction-management/TC_Login'), [('email') :email, ('password') : password
        , ('statusCode') : '200'], FailureHandling.STOP_ON_FAILURE)

ResponseObject responseObject

if (statusCode.equals('200')) {
	'Call test case me để lấy login'
    WebUI.callTestCase(findTestCase('authentiaction-management/TC_me'), [('statusCode') : '200'], FailureHandling.STOP_ON_FAILURE)

    responseObject = WS.sendRequest(findTestObject('user-management/get-user', [('id') : GlobalVariable.id]))
	
} else {
    responseObject = WS.sendRequest(findTestObject('user-management/get-user', [('id') : id]))
}

WS.verifyResponseStatusCode(responseObject, Integer.parseInt(statusCode))


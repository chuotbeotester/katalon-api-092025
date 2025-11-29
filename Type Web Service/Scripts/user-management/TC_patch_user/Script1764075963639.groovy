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

ResponseObject responseObject=null
if(statusCode.equals('404')) {
	'Gửi request patch với status là 404'
	responseObject=WS.sendRequest(findTestObject('user-management/patch-user', [('id') : id, ('name') : name, ('email') : email, ('password') : password
		, ('address') : address, ('phone') : phone]))

}
else
{
	'Gửi request patch với status khác 404'
	responseObject=WS.sendRequest(findTestObject('user-management/patch-user', [('id') : GlobalVariable.id, ('name') : name, ('email') : email, ('password') : password
		, ('address') : address, ('phone') : phone]))
}

println "Response Body: " + responseObject.getResponseBodyContent()

WS.verifyResponseStatusCode(responseObject, Integer.parseInt(statusCode))
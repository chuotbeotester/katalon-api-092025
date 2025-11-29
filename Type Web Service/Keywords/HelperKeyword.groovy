import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import com.kms.katalon.core.configuration.RunConfiguration
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Cell
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.File

import internal.GlobalVariable

public class HelperKeyword {

	public static void writeExcel(String fileName, String sheetName, int rowIndex, int colIndex, String value) {
		try {
			// 1. Cấu hình đường dẫn mặc định: Data Files/excel-files/
			String folderPath = RunConfiguration.getProjectDir() + "/Data Files/excel-files/"
			String fullFilePath = folderPath + fileName

			File folder = new File(folderPath)
			File file = new File(fullFilePath)

			// Tạo thư mục nếu chưa có
			if (!folder.exists()) {
				folder.mkdirs()
			}

			XSSFWorkbook workbook = null
			XSSFSheet sheet = null

			// 2. Load file hoặc Tạo mới
			if (file.exists()) {
				FileInputStream fis = new FileInputStream(file)
				workbook = new XSSFWorkbook(fis)
				fis.close()
			} else {
				workbook = new XSSFWorkbook()
			}

			// 3. Lấy Sheet hoặc Tạo mới
			sheet = workbook.getSheet(sheetName)
			if (sheet == null) {
				sheet = workbook.createSheet(sheetName)
			}

			// 4. Xử lý Dòng (Row) - Quan trọng: Phải kiểm tra null để không ghi đè row cũ
			Row row = sheet.getRow(rowIndex)
			if (row == null) {
				row = sheet.createRow(rowIndex)
			}

			// 5. Xử lý Ô (Cell) - Tạo ô tại cột mong muốn
			Cell cell = row.getCell(colIndex)
			if (cell == null) {
				cell = row.createCell(colIndex)
			}

			// 6. Ghi giá trị
			cell.setCellValue(value)

			// 7. Lưu file
			FileOutputStream fos = new FileOutputStream(file)
			workbook.write(fos)
			fos.close()
			workbook.close()

			// Comment để dễ debug (có thể tắt đi nếu thấy spam log)
			WebUI.comment("Đã ghi '$value' vào file $fileName [Sheet: $sheetName | R:$rowIndex | C:$colIndex]")
		} catch (Exception e) {
			WebUI.comment("Lỗi ghi Excel: " + e.getMessage())
			e.printStackTrace()
		}
	}
}

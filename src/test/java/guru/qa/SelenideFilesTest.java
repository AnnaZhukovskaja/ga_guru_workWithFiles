package guru.qa;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import guru.qa.model.Hero;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


public class SelenideFilesTest {
    private ClassLoader cl = SelenideFilesTest.class.getClassLoader();

    @Test
    void csvFileParsingTest() throws Exception {

        try (ZipInputStream zis = new ZipInputStream(cl.getResourceAsStream("example.zip"))) {
            ZipEntry zipEntry;
            while ((zipEntry = zis.getNextEntry()) != null) {
                if (zipEntry.getName().equals("productCardShouldBeWithPrice.csv")) {
                    CSVReader csvReader = new CSVReader(new InputStreamReader(zis));
                    List<String[]> data = csvReader.readAll();
                    Assertions.assertEquals(2, data.size());
                    Assertions.assertArrayEquals(
                            new String[]{"Platinum Elite Business Plus Carry-On Expandable Hardside Spinner", "$256.40"},
                            data.get(1));
                }
            }
        }
    }

    @Test
    void pdfFileParsingTest() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(cl.getResourceAsStream("example.zip"))) {
            ZipEntry zipEntry;
            while ((zipEntry = zis.getNextEntry()) != null) {
                if (zipEntry.getName().equals("examplePdf.pdf")) {
                    PDF pdf = new PDF(zis);
                    Assertions.assertTrue(pdf.text.trim().contains("Sample PDF"));
                }
            }
        }
    }

    @Test
    void xlsFileParsingTest() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(cl.getResourceAsStream("example.zip"))) {
            ZipEntry zipEntry;
            while ((zipEntry = zis.getNextEntry()) != null) {
                if (zipEntry.getName().equals("import_ou_xlsx.xlsx")) {
                    XLS xls = new XLS(zis);
                    String stringValue = xls.excel.getSheetAt(0).getRow(2).getCell(1).getStringCellValue();
                    Assertions.assertEquals("OU001", stringValue);
                }
            }
        }
    }

    @Test
    void jsonFileParsingTest() throws Exception {
        try (Reader reader = new InputStreamReader(
                cl.getResourceAsStream("hero.json"))) {
            ObjectMapper objectMapper = new ObjectMapper();
            Hero hero = objectMapper.readValue(reader, Hero.class);
            Assertions.assertEquals(2016, hero.getFormed());
            Assertions.assertTrue(hero.getSquad().contains("Super hero"));
            Assertions.assertTrue(hero.getMember().getName().contains("Molecule Man"));
            Assertions.assertEquals(29, hero.getMember().getAge());
        }
    }

}

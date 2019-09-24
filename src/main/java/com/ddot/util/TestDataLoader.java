package com.ddot.util;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

import org.apache.poi.hpsf.Decimal;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class TestDataLoader {
    private String testdataFile;
    private HashMap<String, HashMap<String, String>> dataMap = new HashMap<String, HashMap<String, String>>();

    public TestDataLoader(String fileName) {
        this.testdataFile = fileName;
    }
    private static String generatedResourceFolder;

    public void loadData() {
        int eachTest = 0;
        HashMap<String, String> eachRow = null;
        StringBuilder temp = new StringBuilder();

        if (this.dataMap.size() > 0) {
            return;
        }
        HashMap<String, String> columnMap = new HashMap<String, String>();
        FileInputStream file = null;
        try {

            InputStream tempIs = this.getClass().getResourceAsStream(
                            "/testdata" + File.separatorChar + this.testdataFile);


            try {

                if (generatedResourceFolder == null) {

                    boolean firstTime = false;

                    String pathResourceFolder = (new File(".").getCanonicalPath())

                            + File.separator + "used_resources" + File.separator;

                    File f = new File(pathResourceFolder);

                    if (!f.exists()) {

                        f.mkdir();

                    } else {

                        if (firstTime) {

                            deleteFile(f);

                        }

                    }

                    generatedResourceFolder = f.getAbsolutePath();

                }

            } catch (IOException e) {



            }

            File tempFile =new  File(generatedResourceFolder + File.separator + this.testdataFile);



            OutputStream outputStreamMeta = new FileOutputStream(tempFile);
            IOUtils.copy(tempIs, outputStreamMeta);

            file = new FileInputStream(tempFile);

            // Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            // Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);
            // Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();
            outerloop : while (rowIterator.hasNext()) {

                Row row = rowIterator.next();

                if (row.getRowNum() == 0) {
                    for (int cn = 0; cn < row.getLastCellNum(); cn++) {
                        Cell cell = row.getCell(cn, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                        // All columns will be holding string cell value
                        String columnName = cell.getStringCellValue();
                        int cellIndex = cell.getColumnIndex();
                        columnMap.put("" + cellIndex, columnName.trim());
                    }
                } else {
                    eachTest++;

                    // System.out.println(columnMap);
                    eachRow = new HashMap<String, String>();
                    for (int cn = 0; cn < row.getLastCellNum(); cn++) {
                        Cell cell = row.getCell(cn, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                        switch(cell.getCellType())
                        {
                            case STRING:
                                temp.append(StringUtils.trim(cell.getStringCellValue()));
                                break;
                            case NUMERIC:
                                temp.append( ( Double.toString(cell.getNumericCellValue())));
                                break;


                        }



                        if (StringUtils.isBlank(String.valueOf(row.getCell(0)
                                ))) {
                            break outerloop;
                        }

                        eachRow.put(columnMap.get("" + cell.getColumnIndex()),
                                temp.toString());

                        temp.delete(0, temp.capacity());

                        this.dataMap.put("" + eachTest,
                                new HashMap<String, String>(eachRow));
                    }

                }

            }// while end
        } catch (Exception e) {
int h=0;
        } finally {
            if (file != null) {
                try {
                    file.close();
                } catch (IOException e) {
                }
            }
        }

        // System.out.println(this.dataMap);

        for (Map.Entry<String, HashMap<String, String>> row : this.dataMap
                .entrySet()) {
            HashMap<String, String> myRow = row.getValue();
            String key = row.getKey();

            System.out.println("row num:" + key);
            System.out.println("Columns:" + myRow);
        }
    }

    public HashMap<String, HashMap<String, String>> getTestData() {
        return this.dataMap;
    }


    private static void deleteFile(File f) {

        if (f.isDirectory()) {

            if (f.list().length == 0) {

                f.delete();

                System.out.println("Directory is deleted : "

                        + f.getAbsolutePath());

            } else {

                String files[] = f.list();

                for (String temp : files) {

                    File fileDelete = new File(f, temp);

                    deleteFile(fileDelete);

                }

                if (f.list().length == 0) {

                    f.delete();

                    System.out.println("Directory is deleted : "

                            + f.getAbsolutePath());

                }

            }

        } else {

            f.delete();

            System.out.println("File is deleted : " + f.getAbsolutePath());

        }

    }



}


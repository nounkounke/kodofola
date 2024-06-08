package com.moria.nounkouke.nko_kodofola;

import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.yaml.snakeyaml.util.EnumUtils;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;

@Component
@RequiredArgsConstructor
public class WordLoader implements ApplicationRunner {
    private final WordController wordController;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("templates/kodafola.xlsx").getFile());

        FileInputStream inputStream = new FileInputStream(file);
        Workbook workbook = WorkbookFactory.create(inputStream);
        var dummySheet = workbook.getSheetAt(0);
        int startRow = 1;
        int endRow = 9;
        //int endRow = getFirstEmptyRow(dummySheet);
        //long diff = endRow-startRow;

        Map<Integer,List<List<Object>>> indexToWordMapping = new HashMap<>();

        workbook.sheetIterator().forEachRemaining(sheet-> {

            String sheetName = sheet.getSheetName();
            var language = EnumUtils.findEnumInsensitiveCase(Language.class,sheetName);
            Map<Integer,List<Object>> result =  buildRequest(sheet,language,startRow,endRow);

            for(Integer rowIndex: result.keySet()){
                if (CollectionUtils.isEmpty(indexToWordMapping.get(rowIndex))){
                    indexToWordMapping.put(rowIndex,new ArrayList<>());
                }
                indexToWordMapping.get(rowIndex).add(result.get(rowIndex));
            }
        });

        var words = indexToWordMapping.values();
        for (List<List<Object>> trans: words){
            int x = 2;
            //Word.builder().translations()
        }

        wordController.add(null);
        var x = 3;
    }

    public Map<Integer,List<Object>> buildRequest(Sheet sheet, Language language, int startRow,int endRow){
        Map<Integer,List<Object>> mapping = new HashMap<>();

        for(int index = startRow; index < endRow; index ++){
            var row = sheet.getRow(index);
            if (!ObjectUtils.isEmpty(row)){

                var translationBuilder = Translation.builder().language(language);
                var transliterationBuilder = Transliteration.builder();

                for(Cell cell: row){
                    int cellIndex = cell.getColumnIndex();
                    var cellValue = cell.getStringCellValue();

                    switch (cellIndex){
                        case 0:{
                            translationBuilder.text(cellValue);
                            break;
                        }
                        case 1:{
                            translationBuilder.usage(cellValue);
                            break;
                        }

                        case 2:{
                            translationBuilder.context(cellValue);
                            break;
                        }

                        case 3:{
                            translationBuilder.history(cellValue);
                            break;
                        }

                        case 4:{
                            transliterationBuilder.language(Language.LATIN).text(cellValue);
                            break;
                        }

                    }
                }

                var translation = translationBuilder.build();
                var transliteration = translationBuilder.build();
                mapping.put(index, Arrays.asList(translation,transliteration));
            }

        }
        return mapping;
    }

    private static int getFirstEmptyRow(Sheet sheet) {
        int firstEmptyRowIndex = -1;
        for (Row row : sheet) {
            if (isRowEmpty(row)) {
                firstEmptyRowIndex = row.getRowNum();
                break;
            }
        }
        return firstEmptyRowIndex;
    }

    private static boolean isRowEmpty(Row row) {
        for (Cell cell : row) {
            if (cell != null && cell.getCellType() != CellType.BLANK) {
                return false;
            }
        }
        return true;
    }

    private Language getLanguage(int col) {
        return switch (col) {
            case 0, 3 -> Language.NKO;
            case 1, 4 -> Language.FRENCH;
            default -> Language.ENGLISH;
        };
    }
}

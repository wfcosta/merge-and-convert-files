package br.com.wagner.framework.adapter.in.console;

import br.com.wagner.application.port.dto.MergeAndConvertFilesRequestDTO;
import br.com.wagner.application.port.in.MergeAndConvertFilesPortIn;
import br.com.wagner.domain.beanFactory.UseCaseBeanFactory;
import br.com.wagner.framework.adapter.out.ConsoleAdapterOut;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

class ConsoleAdapterInTest {

    private final String USER_DIR_FILES = System.getProperty("user.dir")+"/files/";

    @Autowired
    MergeAndConvertFilesPortIn mergeAndConvertFilesPortIn;

    @BeforeAll
    public static void beforeAll(){
        File f =  new File(System.getProperty("user.dir")+"/src/test/resources");
        String[] fileNames = f.list();

        try {
            for (int i = 0; i < fileNames.length; i++) {
                Files.copy(new File(System.getProperty("user.dir")+"/src/test/resources/" + fileNames[i]).toPath(),
                           new File(System.getProperty("user.dir") + "/files/" + fileNames[i]).toPath(), StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterAll
    public static void afterAll(){
        File f =  new File(System.getProperty("user.dir") + "/files/");
        String[] fileNames = f.list();

        for (int i = 0; i < fileNames.length; i++) {
            new File(System.getProperty("user.dir") + "/files/" + fileNames[i]).delete();
        }
    }

    @Test
    void mergePDFs() {

        UseCaseBeanFactory useCaseBeanFactory = new UseCaseBeanFactory();
        ConsoleAdapterOut consoleAdapterOut = new ConsoleAdapterOut();
        MergeAndConvertFilesPortIn mergeAndConvertFilesPortIn = new MergeAndConvertFilesPortIn(consoleAdapterOut, useCaseBeanFactory);

        MergeAndConvertFilesRequestDTO mergeAndConvertFilesRequestDTO = new MergeAndConvertFilesRequestDTO();
        mergeAndConvertFilesRequestDTO.setFile1(USER_DIR_FILES + "Medium.pdf");
        mergeAndConvertFilesRequestDTO.setFile2(USER_DIR_FILES + "Udemy.pdf");
        mergeAndConvertFilesRequestDTO.setFileMerged(USER_DIR_FILES + "Merged.pdf");
        mergeAndConvertFilesRequestDTO.setMerge(Boolean.TRUE);

        mergeAndConvertFilesPortIn.mergeAndConvertFilesController(mergeAndConvertFilesRequestDTO);

        File f =  new File(USER_DIR_FILES);
        String[] fileNames = f.list();
        boolean found = false;

        for (int i = 0; i < fileNames.length; i++) {
            if(fileNames[i].equals("Merged.pdf")){
                found = Boolean.TRUE;
            }
        }

        Assert.isTrue(found, "Successfully Merged");

    }

    @Test
    void mergeTXTs() {

        UseCaseBeanFactory useCaseBeanFactory = new UseCaseBeanFactory();
        ConsoleAdapterOut consoleAdapterOut = new ConsoleAdapterOut();
        MergeAndConvertFilesPortIn mergeAndConvertFilesPortIn = new MergeAndConvertFilesPortIn(consoleAdapterOut, useCaseBeanFactory);

        MergeAndConvertFilesRequestDTO mergeAndConvertFilesRequestDTO = new MergeAndConvertFilesRequestDTO();
        mergeAndConvertFilesRequestDTO.setFile1(USER_DIR_FILES + "Text1.txt");
        mergeAndConvertFilesRequestDTO.setFile2(USER_DIR_FILES + "Text2.txt");
        mergeAndConvertFilesRequestDTO.setFileMerged(USER_DIR_FILES + "MergedTXT.txt");
        mergeAndConvertFilesRequestDTO.setMerge(Boolean.TRUE);

        mergeAndConvertFilesPortIn.mergeAndConvertFilesController(mergeAndConvertFilesRequestDTO);

        File f =  new File(USER_DIR_FILES);
        String[] fileNames = f.list();
        boolean found = false;

        for (int i = 0; i < fileNames.length; i++) {
            if(fileNames[i].equals("MergedTXT.txt")){
                found = Boolean.TRUE;
            }
        }

        Assert.isTrue(found, "Successfully Merged");

    }

    @Test
    void convertTXTToPDF() {

        UseCaseBeanFactory useCaseBeanFactory = new UseCaseBeanFactory();
        ConsoleAdapterOut consoleAdapterOut = new ConsoleAdapterOut();
        MergeAndConvertFilesPortIn mergeAndConvertFilesPortIn = new MergeAndConvertFilesPortIn(consoleAdapterOut, useCaseBeanFactory);

        MergeAndConvertFilesRequestDTO mergeAndConvertFilesRequestDTO = new MergeAndConvertFilesRequestDTO();
        mergeAndConvertFilesRequestDTO.setFile1(USER_DIR_FILES + "Text1.txt");
        mergeAndConvertFilesRequestDTO.setFileConverted(USER_DIR_FILES + "convertPDF.pdf");
        mergeAndConvertFilesRequestDTO.setMerge(Boolean.FALSE);

        mergeAndConvertFilesPortIn.mergeAndConvertFilesController(mergeAndConvertFilesRequestDTO);

        File f =  new File(USER_DIR_FILES);
        String[] fileNames = f.list();
        boolean found = false;

        for (int i = 0; i < fileNames.length; i++) {
            if(fileNames[i].equals("convertPDF.pdf")){
                found = Boolean.TRUE;
            }
        }

        Assert.isTrue(found, "Successfully Converted");

    }
}

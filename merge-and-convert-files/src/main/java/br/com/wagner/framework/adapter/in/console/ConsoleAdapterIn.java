package br.com.wagner.framework.adapter.in.console;

import br.com.wagner.application.port.dto.MergeAndConvertFilesRequestDTO;
import br.com.wagner.application.port.usecases.MergeAndConvertFilesUseCase;

import br.com.wagner.domain.util.MergeAndConvertFilesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public class ConsoleAdapterIn {

    ConsoleAdapterIn(MergeAndConvertFilesUseCase mergeAndConvertFilesUseCase){
        this.mergeAndConvertFilesUseCase = mergeAndConvertFilesUseCase;
    }

    private static Logger LOG = LoggerFactory.getLogger(ConsoleAdapterIn.class);
    private final String USER_DIR_FILES = System.getProperty("user.dir")+"/files/";

    private MergeAndConvertFilesUseCase mergeAndConvertFilesUseCase;

    public void mergeAndCovertFiles(){

        LOG.info("STARTING THE CLASS: ConsoleAdapterIn METHOD: mergeAndCovertFiles");
        boolean finish = false;
        MergeAndConvertFilesRequestDTO mergeAndConvertFilesRequestDTO = null;

        MergeAndConvertFilesUtil.printLogo();
        try {
            while(!finish){
                printMenu();
                String line = MergeAndConvertFilesUtil.readLine().toLowerCase();
                if (line.equals("1")) {
                    mergeAndConvertFilesRequestDTO = selectFilesToMerge();
                    mergeAndConvertFilesRequestDTO.setMerge(Boolean.TRUE);
                    setNameFileMerged(mergeAndConvertFilesRequestDTO);
                } else if(line.equals("2")) {
                    mergeAndConvertFilesRequestDTO = selectFilesToConvert();
                    mergeAndConvertFilesRequestDTO.setMerge(Boolean.FALSE);
                    setNameFileConverted(mergeAndConvertFilesRequestDTO);
                }else {
                        MergeAndConvertFilesUtil.println("Invalid entry, please restart application.");
                        System.exit(0);
                }
                mergeAndConvertFilesUseCase.mergeAndConvertFilesController(mergeAndConvertFilesRequestDTO);

                finish = validateAnotherOperation();
            }
        } catch (IOException e) {
            LOG.error("ERROR CLASS: ConsoleAdapterIn METHOD: mergeAndCovertFiles", e);
        }
        LOG.info("FINISHING THE CLASS: ConsoleAdapterIn METHOD: mergeAndCovertFiles");

    }

    private void printMenu() {
        MergeAndConvertFilesUtil.println("Do you want merge or convert files?");
        MergeAndConvertFilesUtil.println("1 - merge");
        MergeAndConvertFilesUtil.println("2 - convert");
        MergeAndConvertFilesUtil.println("Type 1 or 2:");
    }

    private boolean validateAnotherOperation() throws IOException {
        MergeAndConvertFilesUtil.println("Do you want to continue merging pdf files?");
        MergeAndConvertFilesUtil.println("Type yes or no:");
        switch (MergeAndConvertFilesUtil.readLine().toLowerCase()){
            case "yes":
                return Boolean.FALSE;
            case "no":
                return Boolean.TRUE;
            default:
                MergeAndConvertFilesUtil.println("Invalid entry, restart application.");
                System.exit(0);
        }
        return false;
    }

    private void setNameFileMerged(MergeAndConvertFilesRequestDTO mergeAndConvertFilesRequestDTO) {
        try{
            MergeAndConvertFilesUtil.println("\nEnter the name of the merged file with extension ex: .pdf, .txt, ...: ");
            mergeAndConvertFilesRequestDTO.setFileMerged(USER_DIR_FILES + MergeAndConvertFilesUtil.readLine());
        } catch (IOException e) {
            LOG.error("ERROR CLASS: ConsoleAdapterIn METHOD: getNameFileMerge", e);
        }
    }

    private void setNameFileConverted(MergeAndConvertFilesRequestDTO mergeAndConvertFilesRequestDTO) {
        try{
            MergeAndConvertFilesUtil.println("\nEnter the name of the converted file with extension ex: .pdf: ");
            mergeAndConvertFilesRequestDTO.setFileConverted(USER_DIR_FILES + MergeAndConvertFilesUtil.readLine());
        } catch (IOException e) {
            LOG.error("ERROR CLASS: ConsoleAdapterIn METHOD: setNameFileConverted", e);
        }
    }

    private MergeAndConvertFilesRequestDTO selectFilesToConvert() {

        LOG.info("STARTING CLASS: ConsoleAdapterIn METHOD: selectFilesToConvert");

        MergeAndConvertFilesRequestDTO MergeAndConvertFilesRequestDTO = new MergeAndConvertFilesRequestDTO();

        try {

            printAssistance();
            String[] fileNames = readFileNames();
            printFilesOnDirectory(fileNames);

            getFilesToConvert(MergeAndConvertFilesRequestDTO, fileNames);

        } catch (IOException e) {
            LOG.error("ERROR CLASS: ConsoleAdapterIn METHOD: selectFilesToConvert", e);
        }

        LOG.info("FINISHING CLASS: ConsoleAdapterIn METHOD: selectFilesToConvert");

        return MergeAndConvertFilesRequestDTO;

    }


    private MergeAndConvertFilesRequestDTO selectFilesToMerge() {

        LOG.info("STARTING CLASS: ConsoleAdapterIn METHOD: selectFilesToMerge");

        MergeAndConvertFilesRequestDTO MergeAndConvertFilesRequestDTO = new MergeAndConvertFilesRequestDTO();

        try {

            printAssistance();
            String[] fileNames = readFileNames();
            printFilesOnDirectory(fileNames);

            getFilesToMerge(MergeAndConvertFilesRequestDTO, fileNames);

        } catch (IOException e) {
            LOG.error("ERROR CLASS: ConsoleAdapterIn METHOD: selectFilesToMerge", e);
        }

        LOG.info("FINISHING CLASS: ConsoleAdapterIn METHOD: selectFilesToMerge");

        return MergeAndConvertFilesRequestDTO;

    }

    private void printAssistance() {
        MergeAndConvertFilesUtil.println("Put your files in :" + USER_DIR_FILES);
    }

    private String[] readFileNames() {
        File f =  new File(USER_DIR_FILES);
        String[] fileNames = f.list();

        if(fileNames == null || fileNames.length == 0){
            MergeAndConvertFilesUtil.println("No files on " + USER_DIR_FILES);
            MergeAndConvertFilesUtil.println("Application finished.");
            System.exit(0);
        }

        return fileNames;
    }

    private void printFilesOnDirectory(String[] fileNames) {
        for (int i = 0; i < fileNames.length; i++) {
            MergeAndConvertFilesUtil.println(i+1 + " - " + fileNames[i]);
        }
    }

    private void getFilesToMerge(MergeAndConvertFilesRequestDTO mergeAndConvertFilesRequestDTO, String[] fileNames) throws IOException {
        MergeAndConvertFilesUtil.println("\nSelect two files for merge, types accepted are pdf and txt, please select the same type:");
        MergeAndConvertFilesUtil.println("Enter the number of file 1: ");
        mergeAndConvertFilesRequestDTO.setFile1(USER_DIR_FILES + fileNames[Integer.parseInt(MergeAndConvertFilesUtil.readLine()) - 1]);
        MergeAndConvertFilesUtil.println("Enter the number of file 2: ");
        mergeAndConvertFilesRequestDTO.setFile2(USER_DIR_FILES + fileNames[Integer.parseInt(MergeAndConvertFilesUtil.readLine()) - 1]);
    }

    private void getFilesToConvert(MergeAndConvertFilesRequestDTO mergeAndConvertFilesRequestDTO, String[] fileNames) throws IOException {
        MergeAndConvertFilesUtil.println("\nSelect files for convert, types accepted are txt to pdf");
        MergeAndConvertFilesUtil.println("Enter the number of file to convert: ");
        mergeAndConvertFilesRequestDTO.setFile1(USER_DIR_FILES + fileNames[Integer.parseInt(MergeAndConvertFilesUtil.readLine()) - 1]);
    }

}

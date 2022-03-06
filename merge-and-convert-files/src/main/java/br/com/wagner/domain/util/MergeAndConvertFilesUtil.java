package br.com.wagner.domain.util;

import br.com.wagner.domain.bo.MergeAndConvertFilesRequestBO;
import br.com.wagner.domain.bo.SupportedExtensionsToConvert;
import br.com.wagner.domain.bo.SupportedExtensionsToMerge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MergeAndConvertFilesUtil {

    public static void printLogo(){

        String logo = """   
                ===================================================================================================
                =                                                                                                 =
                =                                    MERGE AND CONVERT FILES                                      =
                =                                                                                                 =
                ===================================================================================================
                """;

        System.out.println(logo);
    }

    public static String readLine() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        return line;
    }

    public static void println(String phrase){
        System.out.println(phrase);
    }

    public static boolean validateFilesTypeAndSetSupportedExtensionToMerge(MergeAndConvertFilesRequestBO mergeAndConvertFilesRequestBO) {
        String extensionsFile1 = mergeAndConvertFilesRequestBO.getFile1().split("\\.")[1];
        String extensionsFile2 = mergeAndConvertFilesRequestBO.getFile2().split(".\\.")[1];
        if(extensionsFile1.equals(extensionsFile2)){
            for(SupportedExtensionsToMerge s : SupportedExtensionsToMerge.values()){
                if(s.toString().equals(extensionsFile1.toUpperCase())){
                    mergeAndConvertFilesRequestBO.setSupportedExtensionsToMerge(s);
                    return Boolean.TRUE;
                }
            }
        }
        mergeAndConvertFilesRequestBO.setErro(1);
        mergeAndConvertFilesRequestBO.setMessage("Files not equals or type not supported.");
        return Boolean.FALSE;
    }

    public static boolean validateFilesTypeAndSetSupportedExtensionToConvert(MergeAndConvertFilesRequestBO mergeAndConvertFilesRequestBO) {
        boolean file1OK = false;
        boolean file2OK = false;
        String extensionsFile1 = mergeAndConvertFilesRequestBO.getFile1().split("\\.")[1];
        String extensionsFile2 = mergeAndConvertFilesRequestBO.getFileConverted().split(".\\.")[1];
        for(SupportedExtensionsToConvert s : SupportedExtensionsToConvert.values()){
            if(s.toString().equals(extensionsFile1.toUpperCase())){
                file1OK = Boolean.TRUE;
            }
        }
        for(SupportedExtensionsToConvert s : SupportedExtensionsToConvert.values()){
            if(s.label.equals(extensionsFile2.toUpperCase())){
                mergeAndConvertFilesRequestBO.setSupportedExtensionsToConvert(s);
                file2OK = Boolean.TRUE;
            }
        }
        if(file1OK && file2OK){
            return Boolean.TRUE;
        }

        mergeAndConvertFilesRequestBO.setErro(1);
        mergeAndConvertFilesRequestBO.setMessage("Files selected for convertion not supported, try again.");
        return Boolean.FALSE;
    }

}

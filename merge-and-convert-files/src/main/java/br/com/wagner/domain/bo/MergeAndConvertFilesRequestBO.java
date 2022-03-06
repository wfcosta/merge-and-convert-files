package br.com.wagner.domain.bo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MergeAndConvertFilesRequestBO {

    private String file1;
    private String file2;
    private String fileMerged;
    private String fileConverted;
    private SupportedExtensionsToMerge supportedExtensionsToMerge;
    private SupportedExtensionsToConvert supportedExtensionsToConvert;
    private int erro;
    private String message;

}

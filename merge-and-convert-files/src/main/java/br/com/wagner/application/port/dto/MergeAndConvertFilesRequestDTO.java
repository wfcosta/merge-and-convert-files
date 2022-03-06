package br.com.wagner.application.port.dto;

import br.com.wagner.domain.bo.MergeAndConvertFilesRequestBO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MergeAndConvertFilesRequestDTO {

    private String file1;
    private String file2;
    private String fileMerged;
    private String fileConverted;
    private int erro;
    private String message;
    private boolean merge;

    public MergeAndConvertFilesRequestBO toBO() {
        MergeAndConvertFilesRequestBO mergeAndConvertFilesRequestBO = new MergeAndConvertFilesRequestBO();
        mergeAndConvertFilesRequestBO.setFile1(file1);
        mergeAndConvertFilesRequestBO.setFile2(file2);
        mergeAndConvertFilesRequestBO.setFileMerged(fileMerged);
        mergeAndConvertFilesRequestBO.setFileConverted(fileConverted);
        return mergeAndConvertFilesRequestBO;
    }

    public MergeAndConvertFilesRequestDTO fromBO(MergeAndConvertFilesRequestBO mergeAndConvertFilesRequestBO) {
        MergeAndConvertFilesRequestDTO mergeAndConvertFilesRequestDTO = new MergeAndConvertFilesRequestDTO();
        mergeAndConvertFilesRequestDTO.setFile1(mergeAndConvertFilesRequestBO.getFile1());
        mergeAndConvertFilesRequestDTO.setFile2(mergeAndConvertFilesRequestBO.getFile2());
        mergeAndConvertFilesRequestDTO.setFileMerged(mergeAndConvertFilesRequestBO.getFileMerged());
        mergeAndConvertFilesRequestDTO.setFileConverted(mergeAndConvertFilesRequestBO.getFileConverted());
        mergeAndConvertFilesRequestDTO.setErro(mergeAndConvertFilesRequestBO.getErro());
        mergeAndConvertFilesRequestDTO.setMessage(mergeAndConvertFilesRequestBO.getMessage());
        return mergeAndConvertFilesRequestDTO;
    }
}

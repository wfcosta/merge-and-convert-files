package br.com.wagner.domain.usecases.impl;

import br.com.wagner.domain.bo.MergeAndConvertFilesRequestBO;
import br.com.wagner.domain.usecases.MergeAndConvertFilesDomainUseCase;

import br.com.wagner.domain.util.MergeAndConvertFilesUtil;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class MergeAndConvertFilesDomainUseCasePDFImpl implements MergeAndConvertFilesDomainUseCase {

    private static Logger LOG = LoggerFactory.getLogger(MergeAndConvertFilesDomainUseCasePDFImpl.class);

    @Override
    public void mergeOrConvertFiles(MergeAndConvertFilesRequestBO mergeAndConvertFilesRequestBO) {
        LOG.info("STARTING THE CLASS: MergeAndConvertFilesDomainUseCasePDFImpl METHOD: mergeFiles");
        try{

            File file1 = new File(mergeAndConvertFilesRequestBO.getFile1());
            File file2 = new File(mergeAndConvertFilesRequestBO.getFile2());

            PDFMergerUtility obj = new PDFMergerUtility();

            obj.setDestinationFileName(mergeAndConvertFilesRequestBO.getFileMerged());

            obj.addSource(file1);
            obj.addSource(file2);

            obj.mergeDocuments();

            mergeAndConvertFilesRequestBO.setErro(0);


        }catch (Exception e){
            LOG.error("ERROR CLASS: MergeAndConvertFilesDomainUseCasePDFImpl METHOD: mergeFiles", e);
        }
        LOG.info("FINISHING THE CLASS: MergeAndConvertFilesDomainUseCasePDFImpl METHOD: mergeFiles");
    }

}

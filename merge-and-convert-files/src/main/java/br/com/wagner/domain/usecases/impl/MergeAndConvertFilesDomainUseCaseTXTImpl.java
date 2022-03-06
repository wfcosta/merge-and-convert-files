package br.com.wagner.domain.usecases.impl;

import br.com.wagner.domain.bo.MergeAndConvertFilesRequestBO;
import br.com.wagner.domain.bo.SupportedExtensionsToConvert;
import br.com.wagner.domain.usecases.MergeAndConvertFilesDomainUseCase;
import br.com.wagner.domain.util.MergeAndConvertFilesUtil;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

@Component
public class MergeAndConvertFilesDomainUseCaseTXTImpl implements MergeAndConvertFilesDomainUseCase {

    private static Logger LOG = LoggerFactory.getLogger(MergeAndConvertFilesDomainUseCaseTXTImpl.class);

    @Override
    public void mergeOrConvertFiles(MergeAndConvertFilesRequestBO mergeAndConvertFilesRequestBO) {
        LOG.info("STARTING THE CLASS: MergeAndConvertFilesDomainUseCaseTXTImpl METHOD: mergeFiles");
        try{

            PrintWriter pw = new PrintWriter(mergeAndConvertFilesRequestBO.getFileMerged());
            BufferedReader br = new BufferedReader(new FileReader(mergeAndConvertFilesRequestBO.getFile1()));

            String line = br.readLine();
            while (line != null) {
                pw.println(line);
                line = br.readLine();
            }

            br = new BufferedReader(new FileReader(mergeAndConvertFilesRequestBO.getFile2()));
            line = br.readLine();

            while (line != null) {
                pw.println(line);
                line = br.readLine();
            }

            pw.flush();
            br.close();
            pw.close();

            mergeAndConvertFilesRequestBO.setErro(0);


        }catch (Exception e){
            LOG.error("ERROR CLASS: MergeAndConvertFilesDomainUseCaseTXTImpl METHOD: mergeFiles", e);
        }
        LOG.info("FINISHING THE CLASS: MergeAndConvertFilesDomainUseCaseTXTImpl METHOD: mergeFiles");
    }

}

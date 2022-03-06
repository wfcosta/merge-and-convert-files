package br.com.wagner.domain.usecases.impl;
import br.com.wagner.domain.bo.MergeAndConvertFilesRequestBO;
import br.com.wagner.domain.usecases.MergeAndConvertFilesDomainUseCase;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;

@Component
public class MergeAndConvertFilesDomainUseCaseTXTToPDFImpl implements MergeAndConvertFilesDomainUseCase {

    private static Logger LOG = LoggerFactory.getLogger(MergeAndConvertFilesDomainUseCaseTXTToPDFImpl.class);

    @Override
    public void mergeOrConvertFiles(MergeAndConvertFilesRequestBO mergeAndConvertFilesRequestBO) {
        LOG.info("STARTING THE CLASS: MergeAndConvertFilesDomainUseCaseALLToPDFImpl METHOD: mergeOrConvertFiles");
        try{

            StringBuilder resultStringBuilder = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new FileReader(mergeAndConvertFilesRequestBO.getFile1()))) {
                String line;
                while ((line = br.readLine()) != null) {
                    resultStringBuilder.append(line).append("\n");
                }
            }

            PDDocument pdfDoc = new PDDocument();
            PDPage firstPage = new PDPage();
            pdfDoc.addPage(firstPage);
            try(PDPageContentStream cs = new PDPageContentStream(pdfDoc, firstPage)){
                cs.beginText();
                cs.setFont(PDType1Font.COURIER, 15);
                cs.setNonStrokingColor(Color.BLACK);
                cs.newLineAtOffset(20, 750);
                cs.showText(resultStringBuilder.toString().replace("\n", "").replace("\r", ""));
                cs.newLine();
                cs.endText();
            }
            pdfDoc.save(mergeAndConvertFilesRequestBO.getFileConverted());
            pdfDoc.close();
        }catch (Exception e){
            LOG.error("ERROR CLASS: MergeAndConvertFilesDomainUseCaseALLToPDFImpl METHOD: mergeOrConvertFiles", e);
        }
        LOG.info("FINISHING THE CLASS: MergeAndConvertFilesDomainUseCaseALLToPDFImpl METHOD: mergeOrConvertFiles");
    }

}

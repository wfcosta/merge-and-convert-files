package br.com.wagner.domain.beanFactory;

import br.com.wagner.domain.bo.MergeAndConvertFilesRequestBO;
import br.com.wagner.domain.bo.SupportedExtensionsToConvert;
import br.com.wagner.domain.bo.SupportedExtensionsToMerge;
import br.com.wagner.domain.usecases.MergeAndConvertFilesDomainUseCase;
import br.com.wagner.domain.usecases.impl.MergeAndConvertFilesDomainUseCasePDFImpl;
import br.com.wagner.domain.usecases.impl.MergeAndConvertFilesDomainUseCaseTXTToPDFImpl;
import br.com.wagner.domain.usecases.impl.MergeAndConvertFilesDomainUseCaseTXTImpl;
import org.springframework.stereotype.Component;

@Component
public class UseCaseBeanFactory {

    public MergeAndConvertFilesDomainUseCase getMergeAndConvertFilesDomainUseCase(MergeAndConvertFilesRequestBO mergeAndConvertFilesRequestBO) {
        if(mergeAndConvertFilesRequestBO.getSupportedExtensionsToMerge() != null){
            if (mergeAndConvertFilesRequestBO.getSupportedExtensionsToMerge().equals(SupportedExtensionsToMerge.PDF)) {
                return new MergeAndConvertFilesDomainUseCasePDFImpl();
            } else if(mergeAndConvertFilesRequestBO.getSupportedExtensionsToMerge().equals(SupportedExtensionsToMerge.TXT)){
                return new MergeAndConvertFilesDomainUseCaseTXTImpl();
            }
        } else if(mergeAndConvertFilesRequestBO.getSupportedExtensionsToConvert() != null){
            if(mergeAndConvertFilesRequestBO.getSupportedExtensionsToConvert().equals(SupportedExtensionsToConvert.TXT)){
                return new MergeAndConvertFilesDomainUseCaseTXTToPDFImpl();
            }
        }

        return null;
    }
}



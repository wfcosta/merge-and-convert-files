package br.com.wagner.domain.usecases;

import br.com.wagner.domain.bo.MergeAndConvertFilesRequestBO;

public interface MergeAndConvertFilesDomainUseCase {

    void mergeOrConvertFiles(MergeAndConvertFilesRequestBO mergeAndConvertFilesRequestBO);

}

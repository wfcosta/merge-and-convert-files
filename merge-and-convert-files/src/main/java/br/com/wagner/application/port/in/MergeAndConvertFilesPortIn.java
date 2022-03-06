package br.com.wagner.application.port.in;

import br.com.wagner.application.port.dto.MergeAndConvertFilesRequestDTO;
import br.com.wagner.application.port.out.MergeAndConvertFilesPortOut;
import br.com.wagner.application.port.usecases.MergeAndConvertFilesUseCase;
import br.com.wagner.domain.beanFactory.UseCaseBeanFactory;
import br.com.wagner.domain.bo.MergeAndConvertFilesRequestBO;

import br.com.wagner.domain.util.MergeAndConvertFilesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MergeAndConvertFilesPortIn implements MergeAndConvertFilesUseCase {

    public MergeAndConvertFilesPortIn(MergeAndConvertFilesPortOut mergeAndConvertFilesPortOut,
                                    UseCaseBeanFactory useCaseBeanFactory){
        this.mergeAndConvertFilesPortOut = mergeAndConvertFilesPortOut;
        this.useCaseBeanFactory = useCaseBeanFactory;
    }

    private static Logger LOG = LoggerFactory.getLogger(MergeAndConvertFilesPortIn.class);

    private MergeAndConvertFilesPortOut mergeAndConvertFilesPortOut;
    private UseCaseBeanFactory useCaseBeanFactory;

    @Override
    public void mergeAndConvertFilesController(MergeAndConvertFilesRequestDTO mergeAndConvertFilesRequestDTO) {

        LOG.info("STARTING THE CLASS: MergeAndConvertFilesPortIn METHOD: mergeAndConvertFilesController");

        boolean valid = false;

        MergeAndConvertFilesRequestBO mergeAndConvertFilesRequestBO = mergeAndConvertFilesRequestDTO.toBO();

        if(mergeAndConvertFilesRequestDTO.isMerge()){
            valid = MergeAndConvertFilesUtil.validateFilesTypeAndSetSupportedExtensionToMerge(mergeAndConvertFilesRequestBO);
        } else {
            valid = MergeAndConvertFilesUtil.validateFilesTypeAndSetSupportedExtensionToConvert(mergeAndConvertFilesRequestBO);
        }

        if(valid) {
            useCaseBeanFactory.getMergeAndConvertFilesDomainUseCase(mergeAndConvertFilesRequestBO).mergeOrConvertFiles(mergeAndConvertFilesRequestBO);
            mergeAndConvertFilesPortOut.printResult(mergeAndConvertFilesRequestDTO.fromBO(mergeAndConvertFilesRequestBO));
        }

        LOG.info("FINISHING THE CLASS: MergeAndConvertFilesPortIn METHOD: mergeAndConvertFilesController");

    }

}

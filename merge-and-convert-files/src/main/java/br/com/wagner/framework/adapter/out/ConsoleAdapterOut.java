package br.com.wagner.framework.adapter.out;

import br.com.wagner.application.port.dto.MergeAndConvertFilesRequestDTO;
import br.com.wagner.application.port.out.MergeAndConvertFilesPortOut;
import br.com.wagner.domain.util.MergeAndConvertFilesUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ConsoleAdapterOut implements MergeAndConvertFilesPortOut {

    private static Logger LOG = LoggerFactory.getLogger(ConsoleAdapterOut.class);

    @Override
    public void printResult(MergeAndConvertFilesRequestDTO mergeAndConvertFilesRequestDTO) {
        LOG.info("STARTING THE CLASS: ConsoleAdapterOut METHOD: printResult");
        if(mergeAndConvertFilesRequestDTO.getErro() == 0){
            if(mergeAndConvertFilesRequestDTO.getFileMerged() != null){
                MergeAndConvertFilesUtil.println("File save in:" + mergeAndConvertFilesRequestDTO.getFileMerged());
            } else{
                MergeAndConvertFilesUtil.println("File save in:" + mergeAndConvertFilesRequestDTO.getFileConverted());
            }
        } else {
            MergeAndConvertFilesUtil.println(mergeAndConvertFilesRequestDTO.getMessage());
        }
        LOG.info("FINISHING THE CLASS: ConsoleAdapterOut METHOD: printResult");
    }
}

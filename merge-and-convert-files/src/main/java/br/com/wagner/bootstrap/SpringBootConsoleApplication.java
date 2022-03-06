package br.com.wagner.bootstrap;

import br.com.wagner.framework.adapter.in.console.ConsoleAdapterIn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages={"br.com.wagner.*"})
public class SpringBootConsoleApplication implements CommandLineRunner {

    private static Logger LOG = LoggerFactory.getLogger(SpringBootConsoleApplication.class);

    @Autowired
    ConsoleAdapterIn consoleAdapterIn;

    public static void main(String[] args) {
        LOG.info("STARTING THE APPLICATION");
        SpringApplication.run(SpringBootConsoleApplication.class, args);
        LOG.info("APPLICATION FINISHED");
    }

    @Override
    public void run(String... args) {
        LOG.info("EXECUTING : merge-and-convert-files");
        consoleAdapterIn.mergeAndCovertFiles();
        LOG.info("FINISHING : merge-and-convert-files");

    }
}

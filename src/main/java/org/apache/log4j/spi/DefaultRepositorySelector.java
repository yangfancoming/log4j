



package org.apache.log4j.spi;


public class DefaultRepositorySelector implements RepositorySelector {

    final LoggerRepository repository;

    public
    DefaultRepositorySelector(LoggerRepository repository) {
        this.repository = repository;
    }

    @Override
    public LoggerRepository getLoggerRepository() {
        return repository;
    }
}


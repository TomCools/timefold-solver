open module ai.timefold.solver.benchmark {
    exports ai.timefold.solver.benchmark.impl.aggregator;
    exports ai.timefold.solver.benchmark.impl.cli;
    exports ai.timefold.solver.benchmark.impl.io;
    exports ai.timefold.solver.benchmark.impl.loader;
    exports ai.timefold.solver.benchmark.impl.ranking;
    exports ai.timefold.solver.benchmark.impl.report;
    exports ai.timefold.solver.benchmark.impl.result;
    exports ai.timefold.solver.benchmark.impl.statistic;
    exports ai.timefold.solver.benchmark.impl.xsd;

    exports ai.timefold.solver.benchmark.api;
    exports ai.timefold.solver.benchmark.config;
    exports ai.timefold.solver.benchmark.config.statistic;
    exports ai.timefold.solver.benchmark.config.report;
    exports ai.timefold.solver.benchmark.config.ranking;
    exports ai.timefold.solver.benchmark.config.blueprint;

    requires freemarker;
    requires java.desktop;

    requires ai.timefold.solver.jaxb;
    requires ai.timefold.solver.persistence.common;
    requires ai.timefold.solver.core;
    requires jakarta.xml.bind;
    requires org.jspecify;
    requires org.slf4j;
}
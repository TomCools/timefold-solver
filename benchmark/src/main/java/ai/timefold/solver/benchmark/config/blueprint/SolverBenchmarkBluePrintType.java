package ai.timefold.solver.benchmark.config.blueprint;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlEnum;

import ai.timefold.solver.benchmark.config.SolverBenchmarkConfig;
import ai.timefold.solver.core.config.constructionheuristic.ConstructionHeuristicPhaseConfig;
import ai.timefold.solver.core.config.constructionheuristic.ConstructionHeuristicType;
import ai.timefold.solver.core.config.localsearch.LocalSearchPhaseConfig;
import ai.timefold.solver.core.config.localsearch.LocalSearchType;
import ai.timefold.solver.core.config.phase.PhaseConfig;
import ai.timefold.solver.core.config.solver.SolverConfig;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

@XmlEnum
public enum SolverBenchmarkBluePrintType {
    /*
     * Run the default {@link ConstructionHeuristicType} with and without the default {@link LocalSearchType}.
     */
    CONSTRUCTION_HEURISTIC_WITH_AND_WITHOUT_LOCAL_SEARCH,
    /**
     * Run every {@link ConstructionHeuristicType}.
     */
    EVERY_CONSTRUCTION_HEURISTIC_TYPE,
    /**
     * Run the default {@link ConstructionHeuristicType} with every {@link LocalSearchType}.
     */
    EVERY_LOCAL_SEARCH_TYPE,
    /**
     * Run every {@link ConstructionHeuristicType} with every {@link LocalSearchType}.
     */
    EVERY_CONSTRUCTION_HEURISTIC_TYPE_WITH_EVERY_LOCAL_SEARCH_TYPE;

    protected @NonNull List<SolverBenchmarkConfig> buildSolverBenchmarkConfigList() {
        switch (this) {
            case CONSTRUCTION_HEURISTIC_WITH_AND_WITHOUT_LOCAL_SEARCH:
                return buildConstructionHeuristicWithAndWithoutLocalSearch();
            case EVERY_CONSTRUCTION_HEURISTIC_TYPE:
                return buildEveryConstructionHeuristicType();
            case EVERY_LOCAL_SEARCH_TYPE:
                return buildEveryLocalSearchType();
            case EVERY_CONSTRUCTION_HEURISTIC_TYPE_WITH_EVERY_LOCAL_SEARCH_TYPE:
                return buildEveryConstructionHeuristicTypeWithEveryLocalSearchType();
            default:
                throw new IllegalStateException("The solverBenchmarkBluePrintType ("
                        + this + ") is not implemented.");
        }
    }

    private List<SolverBenchmarkConfig> buildConstructionHeuristicWithAndWithoutLocalSearch() {
        List<SolverBenchmarkConfig> solverBenchmarkConfigList = new ArrayList<>(2);
        solverBenchmarkConfigList.add(buildSolverBenchmarkConfig(null, false, null));
        solverBenchmarkConfigList.add(buildSolverBenchmarkConfig(null, true, null));
        return solverBenchmarkConfigList;
    }

    private List<SolverBenchmarkConfig> buildEveryConstructionHeuristicType() {
        ConstructionHeuristicType[] chTypes = ConstructionHeuristicType.getBluePrintTypes();
        List<SolverBenchmarkConfig> solverBenchmarkConfigList = new ArrayList<>(chTypes.length);
        for (ConstructionHeuristicType chType : chTypes) {
            solverBenchmarkConfigList.add(buildSolverBenchmarkConfig(chType, false, null));
        }
        return solverBenchmarkConfigList;
    }

    private List<SolverBenchmarkConfig> buildEveryLocalSearchType() {
        LocalSearchType[] lsTypes = LocalSearchType.getBluePrintTypes();
        List<SolverBenchmarkConfig> solverBenchmarkConfigList = new ArrayList<>(lsTypes.length);
        for (LocalSearchType lsType : lsTypes) {
            solverBenchmarkConfigList.add(buildSolverBenchmarkConfig(null, true, lsType));
        }
        return solverBenchmarkConfigList;
    }

    private List<SolverBenchmarkConfig> buildEveryConstructionHeuristicTypeWithEveryLocalSearchType() {
        ConstructionHeuristicType[] chTypes = ConstructionHeuristicType.getBluePrintTypes();
        LocalSearchType[] lsTypes = LocalSearchType.getBluePrintTypes();
        List<SolverBenchmarkConfig> solverBenchmarkConfigList = new ArrayList<>(
                chTypes.length * lsTypes.length);
        for (ConstructionHeuristicType chType : chTypes) {
            for (LocalSearchType lsType : lsTypes) {
                solverBenchmarkConfigList.add(buildSolverBenchmarkConfig(chType, true, lsType));
            }
        }
        return solverBenchmarkConfigList;
    }

    protected @NonNull SolverBenchmarkConfig buildSolverBenchmarkConfig(
            @Nullable ConstructionHeuristicType constructionHeuristicType,
            boolean localSearchEnabled, @Nullable LocalSearchType localSearchType) {
        SolverBenchmarkConfig solverBenchmarkConfig = new SolverBenchmarkConfig();
        String constructionHeuristicName = constructionHeuristicType == null
                ? "Construction Heuristic"
                : constructionHeuristicType.name();
        String name;
        if (!localSearchEnabled) {
            name = constructionHeuristicName;
        } else {
            String localSearchName = localSearchType == null
                    ? "Local Search"
                    : localSearchType.name();
            name = constructionHeuristicType == null ? localSearchName
                    : constructionHeuristicName + " - " + localSearchName;
        }
        solverBenchmarkConfig.setName(name);
        SolverConfig solverConfig = new SolverConfig();
        List<PhaseConfig> phaseConfigList = new ArrayList<>(2);
        ConstructionHeuristicPhaseConfig constructionHeuristicPhaseConfig = new ConstructionHeuristicPhaseConfig();
        if (constructionHeuristicType != null) {
            constructionHeuristicPhaseConfig.setConstructionHeuristicType(constructionHeuristicType);
        }
        phaseConfigList.add(constructionHeuristicPhaseConfig);
        if (localSearchEnabled) {
            LocalSearchPhaseConfig localSearchPhaseConfig = new LocalSearchPhaseConfig();
            if (localSearchType != null) {
                localSearchPhaseConfig.setLocalSearchType(localSearchType);
            }
            phaseConfigList.add(localSearchPhaseConfig);
        }
        solverConfig.setPhaseConfigList(phaseConfigList);
        solverBenchmarkConfig.setSolverConfig(solverConfig);
        return solverBenchmarkConfig;
    }

}

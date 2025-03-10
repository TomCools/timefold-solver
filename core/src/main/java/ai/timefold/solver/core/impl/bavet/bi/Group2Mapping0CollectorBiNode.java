package ai.timefold.solver.core.impl.bavet.bi;

import java.util.function.BiFunction;

import ai.timefold.solver.core.config.solver.EnvironmentMode;
import ai.timefold.solver.core.impl.bavet.common.tuple.BiTuple;
import ai.timefold.solver.core.impl.bavet.common.tuple.TupleLifecycle;
import ai.timefold.solver.core.impl.util.Pair;

public final class Group2Mapping0CollectorBiNode<OldA, OldB, A, B>
        extends AbstractGroupBiNode<OldA, OldB, BiTuple<A, B>, Pair<A, B>, Void, Void> {

    private final int outputStoreSize;

    public Group2Mapping0CollectorBiNode(BiFunction<OldA, OldB, A> groupKeyMappingA, BiFunction<OldA, OldB, B> groupKeyMappingB,
            int groupStoreIndex, TupleLifecycle<BiTuple<A, B>> nextNodesTupleLifecycle,
            int outputStoreSize,
            EnvironmentMode environmentMode) {
        super(groupStoreIndex,
                tuple -> createGroupKey(groupKeyMappingA, groupKeyMappingB, tuple), nextNodesTupleLifecycle, environmentMode);
        this.outputStoreSize = outputStoreSize;
    }

    static <A, B, OldA, OldB> Pair<A, B> createGroupKey(BiFunction<OldA, OldB, A> groupKeyMappingA,
            BiFunction<OldA, OldB, B> groupKeyMappingB, BiTuple<OldA, OldB> tuple) {
        OldA oldA = tuple.factA;
        OldB oldB = tuple.factB;
        A a = groupKeyMappingA.apply(oldA, oldB);
        B b = groupKeyMappingB.apply(oldA, oldB);
        return new Pair<>(a, b);
    }

    @Override
    protected BiTuple<A, B> createOutTuple(Pair<A, B> groupKey) {
        return new BiTuple<>(groupKey.key(), groupKey.value(), outputStoreSize);
    }

    @Override
    protected void updateOutTupleToResult(BiTuple<A, B> outTuple, Void unused) {
        throw new IllegalStateException("Impossible state: collector is null.");
    }

}

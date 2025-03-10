package ai.timefold.solver.core.impl.solver;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import ai.timefold.solver.core.api.solver.Solver;
import ai.timefold.solver.core.api.solver.change.ProblemChange;
import ai.timefold.solver.core.impl.testdata.domain.TestdataSolution;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class BestSolutionHolderTest {

    @Test
    void setBestSolution() {
        BestSolutionHolder<TestdataSolution> bestSolutionHolder = new BestSolutionHolder<>();
        assertThat(bestSolutionHolder.take()).isNull();

        TestdataSolution solution1 = TestdataSolution.generateSolution();
        TestdataSolution solution2 = TestdataSolution.generateSolution();

        bestSolutionHolder.set(solution1, () -> true);
        assertThat(bestSolutionHolder.take().getBestSolution()).isSameAs(solution1);
        assertThat(bestSolutionHolder.take()).isNull();

        bestSolutionHolder.set(solution1, () -> true);
        bestSolutionHolder.set(solution2, () -> false);
        assertThat(bestSolutionHolder.take().getBestSolution()).isSameAs(solution1);

        bestSolutionHolder.set(solution1, () -> true);
        bestSolutionHolder.set(solution2, () -> true);
        assertThat(bestSolutionHolder.take().getBestSolution()).isSameAs(solution2);
    }

    @Test
    void completeProblemChanges() {
        BestSolutionHolder<TestdataSolution> bestSolutionHolder = new BestSolutionHolder<>();

        CompletableFuture<Void> problemChange1 = addProblemChange(bestSolutionHolder);
        bestSolutionHolder.set(TestdataSolution.generateSolution(), () -> true);
        CompletableFuture<Void> problemChange2 = addProblemChange(bestSolutionHolder);

        bestSolutionHolder.take().completeProblemChanges();
        assertThat(problemChange1).isCompleted();
        assertThat(problemChange2).isNotCompleted();

        CompletableFuture<Void> problemChange3 = addProblemChange(bestSolutionHolder);
        bestSolutionHolder.set(TestdataSolution.generateSolution(), () -> true);
        bestSolutionHolder.set(TestdataSolution.generateSolution(), () -> true);
        CompletableFuture<Void> problemChange4 = addProblemChange(bestSolutionHolder);

        bestSolutionHolder.take().completeProblemChanges();

        assertThat(problemChange2).isCompleted();
        assertThat(problemChange3).isCompleted();
        assertThat(problemChange4).isNotCompleted();
    }

    @Test
    void cancelPendingChanges_noChangesRetrieved() {
        BestSolutionHolder<TestdataSolution> bestSolutionHolder = new BestSolutionHolder<>();

        CompletableFuture<Void> problemChange = addProblemChange(bestSolutionHolder);
        bestSolutionHolder.set(TestdataSolution.generateSolution(), () -> true);

        bestSolutionHolder.cancelPendingChanges();

        BestSolutionContainingProblemChanges<TestdataSolution> bestSolution = bestSolutionHolder.take();
        bestSolution.completeProblemChanges();

        assertThat(problemChange).isCancelled();
    }

    private CompletableFuture<Void> addProblemChange(BestSolutionHolder<TestdataSolution> bestSolutionHolder) {
        Solver<TestdataSolution> solver = mock(Solver.class);
        ProblemChange<TestdataSolution> problemChange = mock(ProblemChange.class);
        CompletableFuture<Void> futureChange = bestSolutionHolder.addProblemChange(solver, List.of(problemChange));
        verify(solver, times(1)).addProblemChanges(
                Mockito.argThat(problemChanges -> problemChanges.size() == 1 && problemChanges.get(0) == problemChange));
        return futureChange;
    }
}

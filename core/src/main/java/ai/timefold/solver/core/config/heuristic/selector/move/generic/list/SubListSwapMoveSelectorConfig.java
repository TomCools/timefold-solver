package ai.timefold.solver.core.config.heuristic.selector.move.generic.list;

import java.util.function.Consumer;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

import ai.timefold.solver.core.config.heuristic.selector.list.SubListSelectorConfig;
import ai.timefold.solver.core.config.heuristic.selector.move.MoveSelectorConfig;
import ai.timefold.solver.core.config.util.ConfigUtils;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

@XmlType(propOrder = {
        "minimumSubListSize",
        "maximumSubListSize",
        "selectReversingMoveToo",
        "subListSelectorConfig",
        "secondarySubListSelectorConfig"
})
public class SubListSwapMoveSelectorConfig extends MoveSelectorConfig<SubListSwapMoveSelectorConfig> {

    public static final String XML_ELEMENT_NAME = "subListSwapMoveSelector";

    /**
     * @deprecated The minimumSubListSize on the SubListSwapMoveSelectorConfig is deprecated and will be removed in a future
     *             major version of Timefold. Use {@link SubListSelectorConfig#getMinimumSubListSize()} instead.
     */
    @Deprecated(forRemoval = true)
    protected Integer minimumSubListSize = null;
    /**
     * @deprecated The maximumSubListSize on the SubListSwapMoveSelectorConfig is deprecated and will be removed in a future
     *             major version of Timefold. Use {@link SubListSelectorConfig#getMaximumSubListSize()} instead.
     */
    @Deprecated(forRemoval = true)
    protected Integer maximumSubListSize = null;
    private Boolean selectReversingMoveToo = null;
    @XmlElement(name = "subListSelector")
    private SubListSelectorConfig subListSelectorConfig = null;
    @XmlElement(name = "secondarySubListSelector")
    private SubListSelectorConfig secondarySubListSelectorConfig = null;

    /**
     * @deprecated The minimumSubListSize on the SubListSwapMoveSelectorConfig is deprecated and will be removed in a future
     *             major version of Timefold. Use {@link SubListSelectorConfig#getMinimumSubListSize()} instead.
     */
    @Deprecated(forRemoval = true)
    public Integer getMinimumSubListSize() {
        return minimumSubListSize;
    }

    /**
     * @deprecated The minimumSubListSize on the SubListSwapMoveSelectorConfig is deprecated and will be removed in a future
     *             major version of Timefold. Use {@link SubListSelectorConfig#setMinimumSubListSize(Integer)} instead.
     */
    @Deprecated(forRemoval = true)
    public void setMinimumSubListSize(Integer minimumSubListSize) {
        this.minimumSubListSize = minimumSubListSize;
    }

    /**
     * @deprecated The maximumSubListSize on the SubListSwapMoveSelectorConfig is deprecated and will be removed in a future
     *             major version of Timefold. Use {@link SubListSelectorConfig#getMaximumSubListSize()} instead.
     */
    @Deprecated(forRemoval = true)
    public Integer getMaximumSubListSize() {
        return maximumSubListSize;
    }

    /**
     * @deprecated The maximumSubListSize on the SubListSwapMoveSelectorConfig is deprecated and will be removed in a future
     *             major version of Timefold. Use {@link SubListSelectorConfig#setMaximumSubListSize(Integer)} instead.
     */
    @Deprecated(forRemoval = true)
    public void setMaximumSubListSize(Integer maximumSubListSize) {
        this.maximumSubListSize = maximumSubListSize;
    }

    public @Nullable Boolean getSelectReversingMoveToo() {
        return selectReversingMoveToo;
    }

    public void setSelectReversingMoveToo(@Nullable Boolean selectReversingMoveToo) {
        this.selectReversingMoveToo = selectReversingMoveToo;
    }

    public @Nullable SubListSelectorConfig getSubListSelectorConfig() {
        return subListSelectorConfig;
    }

    public void setSubListSelectorConfig(@Nullable SubListSelectorConfig subListSelectorConfig) {
        this.subListSelectorConfig = subListSelectorConfig;
    }

    public @Nullable SubListSelectorConfig getSecondarySubListSelectorConfig() {
        return secondarySubListSelectorConfig;
    }

    public void setSecondarySubListSelectorConfig(@Nullable SubListSelectorConfig secondarySubListSelectorConfig) {
        this.secondarySubListSelectorConfig = secondarySubListSelectorConfig;
    }

    // ************************************************************************
    // With methods
    // ************************************************************************

    public @NonNull SubListSwapMoveSelectorConfig withSelectReversingMoveToo(@NonNull Boolean selectReversingMoveToo) {
        this.setSelectReversingMoveToo(selectReversingMoveToo);
        return this;
    }

    public @NonNull SubListSwapMoveSelectorConfig
            withSubListSelectorConfig(@NonNull SubListSelectorConfig subListSelectorConfig) {
        this.setSubListSelectorConfig(subListSelectorConfig);
        return this;
    }

    public @NonNull SubListSwapMoveSelectorConfig
            withSecondarySubListSelectorConfig(@NonNull SubListSelectorConfig secondarySubListSelectorConfig) {
        this.setSecondarySubListSelectorConfig(secondarySubListSelectorConfig);
        return this;
    }

    @Override
    public @NonNull SubListSwapMoveSelectorConfig inherit(@NonNull SubListSwapMoveSelectorConfig inheritedConfig) {
        super.inherit(inheritedConfig);
        this.minimumSubListSize =
                ConfigUtils.inheritOverwritableProperty(minimumSubListSize, inheritedConfig.minimumSubListSize);
        this.maximumSubListSize =
                ConfigUtils.inheritOverwritableProperty(maximumSubListSize, inheritedConfig.maximumSubListSize);
        this.selectReversingMoveToo =
                ConfigUtils.inheritOverwritableProperty(selectReversingMoveToo, inheritedConfig.selectReversingMoveToo);
        this.subListSelectorConfig =
                ConfigUtils.inheritOverwritableProperty(subListSelectorConfig, inheritedConfig.subListSelectorConfig);
        this.secondarySubListSelectorConfig =
                ConfigUtils.inheritOverwritableProperty(secondarySubListSelectorConfig,
                        inheritedConfig.secondarySubListSelectorConfig);
        return this;
    }

    @Override
    public @NonNull SubListSwapMoveSelectorConfig copyConfig() {
        return new SubListSwapMoveSelectorConfig().inherit(this);
    }

    @Override
    public void visitReferencedClasses(@NonNull Consumer<Class<?>> classVisitor) {
        visitCommonReferencedClasses(classVisitor);
        if (subListSelectorConfig != null) {
            subListSelectorConfig.visitReferencedClasses(classVisitor);
        }
        if (secondarySubListSelectorConfig != null) {
            secondarySubListSelectorConfig.visitReferencedClasses(classVisitor);
        }
    }

    @Override
    public boolean hasNearbySelectionConfig() {
        return (subListSelectorConfig != null && subListSelectorConfig.hasNearbySelectionConfig())
                || (secondarySubListSelectorConfig != null && secondarySubListSelectorConfig.hasNearbySelectionConfig());
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" + subListSelectorConfig
                + (secondarySubListSelectorConfig == null ? "" : ", " + secondarySubListSelectorConfig) + ")";
    }
}

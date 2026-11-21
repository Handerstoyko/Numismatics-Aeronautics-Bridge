package dev.serhii.numiaerobridge.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

/**
 * Numismatics allows fluid orders up to four buckets. Simulated's connector tank
 * was hard-limited to one bucket, which made every larger SalePoint order fail
 * its capacity preflight. Match the SalePoint's four-bucket filter capacity.
 */
@Mixin(
    targets = "dev.simulated_team.simulated.content.blocks.docking_connector.DockingConnectorTank",
    remap = false
)
public abstract class DockingConnectorTankCapacityMixin {
    @ModifyConstant(
        method = "<init>",
        constant = @Constant(intValue = 1000),
        require = 1,
        remap = false
    )
    private int numiaerobridge$increaseSalePointFluidCapacity(int originalCapacity) {
        return 4000;
    }
}

package net.sideways_sky.create_radar.compat.cbc;

import static java.lang.Double.NaN;
import static java.lang.Math.log;
import static java.lang.Math.toRadians;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.solvers.BrentSolver;
import org.apache.commons.math3.analysis.solvers.UnivariateSolver;

import net.sideways_sky.create_radar.compat.vs2.PhysicsHandler;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.phys.Vec3;
import rbasamoyai.createbigcannons.cannon_control.cannon_mount.CannonMountBlockEntity;
import rbasamoyai.createbigcannons.cannon_control.contraption.AbstractMountedCannonContraption;
import rbasamoyai.createbigcannons.cannon_control.contraption.PitchOrientedContraptionEntity;

public class CannonTargeting {
    public static double calculateProjectileYatX(double speed, double dX, double thetaRad,double drag, double g ) {
        double log = log(1 - (drag * dX) / (speed * Math.cos(thetaRad)));
        if (Double.isInfinite(log)) log = NaN;
        return dX * Math.tan(thetaRad) + (dX * g) / (drag * speed * Math.cos(thetaRad)) + g*log / (drag * drag);
    }

    public static List<Double> calculatePitch(CannonMountBlockEntity mount, Vec3 targetPos, ServerLevel level) {
        if (mount == null || targetPos == null) {
            return null;
        }

        PitchOrientedContraptionEntity contraption = mount.getContraption();
        if ( contraption == null || !(contraption.getContraption() instanceof AbstractMountedCannonContraption cannonContraption)) {
            return null;
        }
        float speed = CannonUtil.getInitialVelocity(cannonContraption, level);

        Vec3 originPos = PhysicsHandler.getWorldVec(level, mount.getBlockPos().above(2).getCenter());
        int barrelLength = CannonUtil.getBarrelLength(cannonContraption);

        double drag = CannonUtil.getProjectileDrag(cannonContraption, level);
        double gravity = CannonUtil.getProjectileGravity(cannonContraption, level);

        if (speed == 0) {
            return null;
        }
        double d1 = targetPos.x - originPos.x;
        double d2 = targetPos.z - originPos.z;
        double distance = Math.abs(Math.sqrt(d1 * d1 + d2 * d2));
        double d3 = targetPos.y - originPos.y;
        double g = Math.abs(gravity);
        UnivariateFunction diffFunction = theta -> {
            double thetaRad = toRadians(theta);

            double dX = distance - (Math.cos(thetaRad) * (barrelLength));
            double dY = d3 - (Math.sin(thetaRad) * (barrelLength));
            double y = calculateProjectileYatX(speed, dX, thetaRad, drag, g);
            return y - dY;
        };

        UnivariateSolver solver = new BrentSolver(1e-32);

        double start = -90;
        double end = 90;
        double step = 1.0;
        List<Double> roots = new ArrayList<>();

        double prevValue = diffFunction.value(start);
        double prevTheta = start;
        for (double theta = start + step; theta <= end; theta += step) {
            double currValue = diffFunction.value(theta);
            if (prevValue * currValue < 0) {
                try {
                    double root = solver.solve(1000, diffFunction, prevTheta, theta);
                    roots.add(root);
                } catch (Exception e) {
                    return null;
                }
            }
            prevTheta = Double.isNaN(currValue) ? prevTheta : theta;
            prevValue = Double.isNaN(currValue) ? prevValue : currValue;
        }
        if (roots.isEmpty()) {
            return null;
        }
        return roots;
    }
}

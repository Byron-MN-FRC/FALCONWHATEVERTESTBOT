package frc.robot;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.util.Units;

public class Constants {
    public static final class VisionConstants {
        public static final String limelightName = "limelight-front";
        public static final String limelightName2 = "limelight-back";

        public static final double fieldWidth = 8.052;
        public static final double fieldLength = 17.548;

        public static final Pose2d ReefTagOffset = new Pose2d(0, Units.inchesToMeters(17.25), new Rotation2d(Math.PI));
        public static final Pose2d CoralStationTagOffset = new Pose2d(0, Units.inchesToMeters(27), new Rotation2d());
    }
}
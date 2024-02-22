package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

public class DriveWheelsIOCimDC implements DriveWheelsIO {

    // Invert the motors as necessary
    private final boolean leftFrontInverted = false;
    private final boolean leftRearInvertedFromFront = false;
    private final boolean rightFrontInverted = true;
    private final boolean rightRearInvertedFromFront = false;

    // SparkMAX motor controllers for the drive train
    private final CANSparkMax leftFrontMotor = new CANSparkMax(1, MotorType.kBrushed);
    private final CANSparkMax leftRearMotor = new CANSparkMax(2, MotorType.kBrushed);
    private final CANSparkMax rightFrontMotor = new CANSparkMax(3, MotorType.kBrushed);
    private final CANSparkMax rightRearMotor = new CANSparkMax(4, MotorType.kBrushed);

    public DriveWheelsIOCimDC() {
        // Restore factory defaults
        leftFrontMotor.restoreFactoryDefaults();
        leftRearMotor.restoreFactoryDefaults();
        rightFrontMotor.restoreFactoryDefaults();
        rightRearMotor.restoreFactoryDefaults();

        // Clear faults
        leftFrontMotor.clearFaults();
        leftRearMotor.clearFaults();
        rightFrontMotor.clearFaults();
        rightRearMotor.clearFaults();

        

        // Invert the front motors as necessary
        leftFrontMotor.setInverted(leftFrontInverted);
        rightFrontMotor.setInverted(rightFrontInverted);

        // Set the rear motors to follow the front motors
        leftRearMotor.follow(leftFrontMotor, leftRearInvertedFromFront);
        rightRearMotor.follow(rightFrontMotor, rightRearInvertedFromFront);

        // Burn flash
        leftFrontMotor.burnFlash();
        leftRearMotor.burnFlash();
        rightFrontMotor.burnFlash();
        rightRearMotor.burnFlash();
    }

    @Override
    public void drive(double leftSpeedMps, double rightSpeedMps) {
        leftFrontMotor.set(leftSpeedMps / DriveSubsystem.kMaxSpeedMetersPerSecond);
        rightFrontMotor.set(rightSpeedMps / DriveSubsystem.kMaxSpeedMetersPerSecond);
    }

    
}


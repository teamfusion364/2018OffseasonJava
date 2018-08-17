package frc.team364.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team364.robot.RobotMap;
import frc.team364.robot.PIDCalc;
import frc.team364.robot.commands.teleop.TeleopArmCommand;
import edu.wpi.first.wpilibj.AnalogInput;

public class ArmSystem extends Subsystem {

    private TalonSRX arm;
    private PIDCalc pidArm;
    private PIDCalc pidMoveArm;
    private double pidArmOutput;
    private double pidMoveArmOutput;
    private AnalogInput pot;

    /**
     * ArmSystem()
     * used to rotate the arm about a pivot on the y-axis
     */
    public ArmSystem() {
        arm = new TalonSRX(RobotMap.arm);
        pidArm = new PIDCalc(0, 0, 0, 0, "Arm");
        pidMoveArm = new PIDCalc(0.1, 0, 0, 0, "moveArm");
        pot = new AnalogInput(0);
    }

    
    protected void initDefaultCommand() {
        setDefaultCommand(new TeleopArmCommand());
    }
    /**
     * armForward()
     * moves the arm forward
     */
    public void armForward() {
        arm.set(ControlMode.PercentOutput, 0.7);
    }
    /**
     * armBackward()
     * moves the arm backward
     */
    public void armBackward(){
        arm.set(ControlMode.PercentOutput, -0.7);
    }
    /**
     * powerArm()
     * applies power to the arm
     * @param armPower percent output for the arm motor
     */
    public void powerArm(double armPower){
        arm.set(ControlMode.PercentOutput, armPower);
    }

    /**
     * armStop()
     * stops the arm
     */
    public void armStop() {
        arm.set(ControlMode.PercentOutput, 0);
    }

	// This should be all you need to set the position you want.
    // You may want to do some math to find out the counts per degree.
    /**
     * moveArmToPosition()
     * moves the arm to reach a desired positoin with the potentiometer and armPID
     * @param voltage voltage desired to be reached
     */
	public void moveArmToPosition(double voltage){
		pidMoveArmOutput = pidMoveArm.calculateOutput(voltage, getPotVoltage());
		arm.set(ControlMode.PercentOutput, pidMoveArmOutput);
    }

    public void keepArmVoltage(double voltage){
        pidArmOutput = pidArm.calculateOutput(voltage, getPotVoltage());
        arm.set(ControlMode.PercentOutput, pidArmOutput);
    }

    public boolean withinSafeZone(){
        if(getPotVoltage() >= 4.7){
            return false;
        
        }else{
            return true;
        }
    }

    public boolean withinDangerZone(){
        if(getPotVoltage() >= 4.6){
            return true;
        
        }else{
            return false;
        }
    }
    /**
     * getPotVoltage()
     * returns the current voltage reading of the potentiometer
     * @return retuns the voltage of the potentiometer
     */
      public double getPotVoltage(){
        return  pot.getVoltage();
    }

}
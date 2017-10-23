package org.usfirst.frc.team5472.robot.subsystems;

import org.usfirst.frc.team5472.robot.commands.JoystickDriveCommand;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *	Subsystems hold most of the program logic
 *  This is an example Drive subsystem, it should have methods that control the drive train of the robot
 */

public class DriveSubsystem extends Subsystem {

	//VictorSP is one of a few provided speed controller classes in WPILIB
	//Two of the other speed controller classes we use are CANTalon, Jaguar, and Talon.

	//CANTalon is used to connect TalonSRX speed controllers that are connected through the CAN bus
	//constructor: public CANTalon(int canID); canID is the id of the Talon SRX on the CAN bus

	//Talon is used to connect TalonSRX speed controllers that are connected to the RoboRIO through a PWM cable
	//this will allow the control of motors, but prevents the use of key functions suck as the built in PID controller and sensor interface
	//constructor: public Talon(int pwmID); pwmID is the id of the Talon SRX on the RoboRIO's PWM slots

	//Jaguar is used to connect to CTRE Jaguar speed controllers, it only offers a connection through PWM
	//constructor: public Jaguar(int pwmID); pwmID is the id of the Jaguar on the RoboRIO's PWM slots

	//VictorSP is used to connect to VictorSP speed controllers, they only offer a connection through PWM and we happen to use them the most
	//constructor: public VictorSP(int pwmID); pwmID is the id of the VictorSP on the RoboRIO's PWM slots

	//All of the aforementioned speed controller classes implement the SpeedController interface, some of its important methods are shown below
	//  public double get(); Returns the current set speed of the speed controller, within the range [-1, 1]
	//  public void set(double speed); Sets the speed of the speed controller to speed
	//  , according to the native source code (PWM.cpp) it accepts values of any range (including non-finite numbers), but will constrain them to [-1, 1]
	//  public void setInverted(boolean inverted); //Allows you to invert a motor. Typically, to get a robot to move straight forwards, one side (left or right) will be given a value
	//  and the other side will be given the negative of that value. Inverting the motors on one side will allow you to give all motors the same value and have it move in the same direction

	private VictorSP leftMotor;
	private VictorSP rightMotor;


	//In this method, initialize the command that will act as this subsystem's default command. That is, the command that will be run when other commands that do not 'require' the drive subsystem are run
	//A good choice for the default command for a drive subsystem is one that takes joystick input and uses it to drive the robot (in teleoperated mode)
	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new JoystickDriveCommand());
	}

	//This is a simple example of setting motor values for the left and right wheels of a robot containing two motors
	public void set(double left, double right){
		leftMotor.set(left);
		rightMotor.set(right);
	}
}
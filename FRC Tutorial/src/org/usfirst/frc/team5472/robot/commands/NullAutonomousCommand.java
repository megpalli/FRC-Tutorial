package org.usfirst.frc.team5472.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

public class NullAutonomousCommand extends Command {
	//This command literally does nothing
	//Its purpose for existing is solely to make it easier to have the robot do nothing during autonomous

	@Override
	protected boolean isFinished() {
		return true;
	}

}

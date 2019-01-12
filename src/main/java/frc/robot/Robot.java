/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.ExampleSubsystem;

//Kevinlikesbiggay

public class Robot extends TimedRobot {
	int motor = 0;
	int kevingay = 1;
	int calebcewl = 2;
	int kevingaymore = 3;
	Spark sparkmotor;
	Spark sparkkevingay;
	Spark sparkcalebcewl;
	Spark sparkkevingaymore;
	Joystick joystick;
	SpeedControllerGroup left;
	SpeedControllerGroup right;
	
	
	
	
	
	
	@Override
	public void robotInit() {
		
		
		
		
		sparkmotor = new Spark(motor);
		sparkkevingay = new Spark(kevingay);
		sparkcalebcewl = new Spark(calebcewl);
		sparkkevingaymore = new Spark(kevingaymore);

		//sparkmotor.setInverted(true);
		//sparkcalebcewl.setInverted(true);

		
		right = new SpeedControllerGroup(sparkkevingay, sparkmotor);
		left = new SpeedControllerGroup(sparkcalebcewl,sparkkevingaymore);
		

		
		
		joystick = new Joystick(0);
	}	
	

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	
	@Override
	public void autonomousInit() {


		

	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	//huge double dong!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	public void teleopPeriodic() {
		
		Scheduler.getInstance().run();
		double y = -joystick.getRawAxis(1);
		double x = joystick.getRawAxis(0);

		double leftSpeed = y*0.7;
		double rightSpeed = y*-0.7;
		
		if (x > 0.5) {
			rightSpeed = -x*0.4;
			

		}
		else if (x < -0.5) {
			leftSpeed = x*0.4;
		}

		if(-0.3 <y && 0.3>y){
			System.out.println("y is between -0.3 and 0.3");
			if(x<-0.5){
				System.out.println("turn left");
				right.set(0.5);
				left.set(-0.5);
			}
			else if(x>0.5){
				System.out.println("turn right");
				left.set(0.5);
				right.set(-0.5);
			}
		}

		left.set(leftSpeed);
		right.set(rightSpeed);
	
	

	}	
	
	
	
	
	
	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}
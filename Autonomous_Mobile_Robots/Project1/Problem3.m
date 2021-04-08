%% Move Robot Sample Code

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% 
% Code by: Matthew Jordan (UVA)
% AMR 2020 
% Date: 09/22/2020
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

clear
close all
clc

%% Parameters

% Workspace Size
xlim([0 200])
ylim([0 200])

%Velocity (constant for this demo example) 
vel = 0;

%Steering angle
steering = pi/4;

%Initialize a vector of positions for the robot
x=[]; 
y=[];

%% Robot Initial Pose

x(1) = 100;
y(1) = 100;
vel(1) = 0;

% Initial Orientation
start = randi(10);
theta(1) = pi/start;

% Build Robot Model
robot = RectangleRobot(x,y,theta(1));

plot(robot(:,1),robot(:,2),'-');
xlim([0 200])
ylim([0 200])


%% Move Robot

%number of steps of the simualtion
nstep = 1000;

%time step
dt = 0.1;
index = 0;
xgoal1 = randi([1 200],1);
ygoal1 = randi([1 200],1);

xgoal2 = randi([1 200],1);
ygoal2 = randi([1 200],1);

xgoal3 = randi([1 200], 1);
ygoal3 = randi([1 200], 1);

xgoal = xgoal1;
ygoal = ygoal1;
setpoint = 5;
previous_error = 0;
integral = 0;
for i = 1:nstep
    if (xgoal - 1 < x(i)) &&  (xgoal + 1 > x(i)) && (ygoal - 1 < y(i)) && (ygoal + 1 > y(i))
        break
    end
    %robot non-holonomic dynamics (as seen in class)
    x(i+1) = x(i) + vel(i) * cos(theta(i)) * dt;
    y(i+1) = y(i) + vel(i) * sin(theta(i)) * dt;
    thetaD = atan2((ygoal-y(i)),(xgoal - x(i)));
    gamma = 0.5 * (thetaD - theta(i));
    steering = vel(i) * tan(gamma);
    if steering > pi/4
        steering = pi/4;
    end
    theta(i+1) = theta(i) + steering * dt;
    
    error = setpoint - vel(i);
    integral = integral + (error * dt);
    derivative = (error - previous_error)/dt;
    output = error + (10 * integral) + (0 * derivative);
    previous_error = error;
    vel(i+1) = vel(i) + output - (0.01 * vel(i));
    
    robot = RectangleRobot(x(i),y(i),theta(i));
    plot(robot(:,1),robot(:,2),'-',x,y,'-',xgoal,ygoal,'bo','MarkerSize',5);
    title('Cruise Control: 3 Random Points');
    xlabel('X Values');
    ylabel('Y Values');
    %plot(xgoal, ygoal, 'bo', 'MarkerSize', 50);
    xlim([0 200])
    ylim([0 200])
    pause(0.01)
end
xgoal = xgoal2;
ygoal = ygoal2;
j = 1;
while j ~= nstep
      if (xgoal - 1 < x(i)) &&  (xgoal + 1 > x(i)) && (ygoal - 1 < y(i)) && (ygoal + 1 > y(i))
        break
      end
    x(i+1) = x(i) + vel(i) * cos(theta(i)) * dt;
    y(i+1) = y(i) + vel(i) * sin(theta(i)) * dt;
    thetaD = atan2((ygoal-y(i)),(xgoal - x(i)));
    gamma = 0.5 * (thetaD - theta(i));
    steering = vel(i) * tan(gamma);
    if steering > pi/4
        steering = pi/4;
    end
    theta(i+1) = theta(i) + steering * dt;
    error = setpoint - vel(i);
    integral = integral + (error * dt);
    derivative = (error - previous_error)/dt;
    output = error + (10 * integral) + (0 * derivative);
    previous_error = error;
    vel(i+1) = vel(i) + output - (0.01 * vel(i));
    
    robot = RectangleRobot(x(i),y(i),theta(i));
    
    plot(robot(:,1),robot(:,2),'-',x,y,'-',xgoal,ygoal,'bo','MarkerSize',5);
    title('Cruise Control: 3 Random Points');
    xlabel('X Values');
    ylabel('Y Values');
    j = j + 1;
    i = i + 1;
    xlim([0 200])
    ylim([0 200])
    pause(0.01)
end
xgoal = xgoal3;
ygoal = ygoal3;
j = 1;
while j ~= nstep
    if (xgoal - 0.2 < x(i)) &&  (xgoal + 0.2 > x(i)) && (ygoal - 0.2 < y(i)) && (ygoal + 0.2 > y(i))
        break
    end
          if (xgoal - 1 < x(i)) &&  (xgoal + 1 > x(i)) && (ygoal - 1 < y(i)) && (ygoal + 1 > y(i))
        break
      end
    x(i+1) = x(i) + vel(i) * cos(theta(i)) * dt;
    y(i+1) = y(i) + vel(i) * sin(theta(i)) * dt;
    thetaD = atan2((ygoal-y(i)),(xgoal - x(i)));
    gamma = 0.5 * (thetaD - theta(i));
    steering = vel(i) * tan(gamma);
    if steering > pi/4
        steering = pi/4;
    end
    theta(i+1) = theta(i) + steering * dt;
    error = setpoint - vel(i);
    integral = integral + (error * dt);
    derivative = (error - previous_error)/dt;
    output = error + (10 * integral) + (0 * derivative);
    previous_error = error;
    vel(i+1) = vel(i) + output - (0.01 * vel(i));
    robot = RectangleRobot(x(i),y(i),theta(i));
    plot(robot(:,1),robot(:,2),'-',x,y,'-',xgoal,ygoal,'bo','MarkerSize',5);
    title('Cruise Control: 3 Random Points');
    xlabel('X Values');
    ylabel('Y Values');
    j = j + 1;
    i = i + 1;
    xlim([0 200])
    ylim([0 200])
    pause(0.01)
end
plot(vel,'color','g');
title('Velocity Vs. Time');
legend('Velocity')
xlabel('Time');
ylabel('Velocity');
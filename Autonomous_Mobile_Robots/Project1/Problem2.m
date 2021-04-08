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
velArr = [];

%% Robot Initial Pose

x(1) = 100;
y(1) = 100;
velArr(1) = 0;
% Initial Orientation
start = randi(10);
theta(1) = pi/start;

% Build Robot Model
robot = RectangleRobot(x,y,theta(1));
plot(robot(:,1),robot(:,2),'-');
title('Go-To-Goal Robot Demonstration: 1 Random Point');
xlim([0 200])
ylim([0 200])


%% Move Robot

%number of steps of the simualtion
nstep = 2000;
vel = 0;
%time step
dt = 0.1;

xgoal = randi([1 200],1);
ygoal = randi([1 200],1);

for i = 1:nstep
    if (xgoal - 0.1 < x(i)) &&  (xgoal + 0.1 > x(i)) && (ygoal - 0.1 < y(i)) && (ygoal + 0.1 > y(i))
        break
    end
    vel = 0.5 * sqrt((xgoal - x(i))^2 + (ygoal-y(i))^2);
    if vel > 3.0
        vel = 3.0;
    end 
    %robot non-holonomic dynamics (as seen in class)
    x(i+1) = x(i) + vel * cos(theta(i)) * dt;
    y(i+1) = y(i) + vel * sin(theta(i)) * dt;
    velArr(i+1) = vel;
    thetaD = atan2((ygoal-y(i)),(xgoal - x(i)));
    gamma = 0.5 * (thetaD - theta(i));
    steering = vel * tan(gamma);
    if steering > pi/4
        steering = pi/4;
    end
    theta(i+1) = theta(i) + steering * dt;
    
    robot = RectangleRobot(x(i),y(i),theta(i));
    plot(robot(:,1),robot(:,2),'-',x,y,'-',xgoal,ygoal,'bo','MarkerSize',5);
    title('Go-To-Goal Robot Demonstration: 1 Random Point');
    xlabel('X Coordinate');
    ylabel('Y Coordinate');
    xlim([0 200])
    ylim([0 200])
    pause(0.01)
    grid on
end
plot(velArr,'color','g');
title('Velocity Vs. Time');
legend('Velocity')
xlabel('Time');
ylabel('Velocity');
ylim([0 5]);
figure;
plot(x)
hold on
plot(y)
title('X Position and Y Position Vs. Time');
legend('X Values','Y Values')
xlabel('Time');
ylabel('Coordinate Position');
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
vel = 5;

%Steering angle
steering = pi/4;

%Initialize a vector of positions for the robot
x=[]; 
y=[];
dist=[];
%% Robot Initial Pose

x(1) = 200;
y(1) = 140;

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
nstep = 600;

%time step
dt = 0.1;

linex = 0 : 400;
linea = 1;
lineb = -1;
linec = 2;
liney = (-linea * linex - linec)/lineb;
plot(linex, liney)

for i = 1:nstep
    if x(i) < -10
        break
    end
    %robot non-holonomic dynamics (as seen in class)
    x(i+1) = x(i) + vel * cos(theta(i)) * dt;
    y(i+1) = y(i) + vel * sin(theta(i)) * dt;
    
    thetaD = atan2(-linea,lineb);
    
    distance = (linea*x(i) + lineb*y(i) + linec)/(sqrt(linea^2 + lineb^2));
    if distance > -11 && distance < 11
        if distance > 0
            distance = -10;
            dist(i) = distance * -1;
        else
            distance = 10;
            dist(i) = distance * -1;
        end
    else
        dist(i) = distance;
    end
    alpha = distance * -0.01;
    gamma = 0.5 * (thetaD - theta(i)) + alpha;
    steering = vel * tan(gamma);
    
    theta(i+1) = theta(i) + steering * dt;
    
    robot = RectangleRobot(x(i),y(i),theta(i));
    plot(robot(:,1),robot(:,2),'-',x,y,'-',linex,liney);
    title('Follow Wall');
    %plot(xgoal, ygoal, 'bo', 'MarkerSize', 50);
    xlim([0 200])
    ylim([0 200])
    pause(0.01)
    
end
plot(dist);
title('Follow Wall: Distance vs. Time');
xlabel('Time');
ylabel('Distance');
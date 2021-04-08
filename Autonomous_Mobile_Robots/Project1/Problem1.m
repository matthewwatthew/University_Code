
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
steering = pi/6; 

%Initialize a vector of positions for the robot
x=[]; 
y=[];
theta=[];
%% Robot Initial Pose

x(1) = 50;
y(1) = 50;

% Initial Orientation 
theta(1) = pi/3;

% Build Robot Model
robot = RectangleRobot(x,y,theta(1));

plot(robot(:,1),robot(:,2),'-');
xlim([0 100])
ylim([0 100])

a = [-2 0];
b = [-2 6];
c = [2 0];
d = [2 6];

robot = RectangleRobot(50,50,theta(1));
robot2 = Translate(50,50,theta(1));
plot(robot2(:,1),robot2(:,2),'-',x,y,'-');
title('After Translation: Robot');
xlabel('X Values');
ylabel('Y Values');
xlim([0 100])
ylim([0 100])
figure;
plot(robot(:,1),robot(:,2),'-',x,y,'-');
title('Before Translation: Robot');
xlabel('X Values');
ylabel('Y Values');
xlim([0 100])
ylim([0 100])



rotmat = [cos(theta) -sin(theta); sin(theta) cos(theta)];
    

function [robot] = Translate(x,y,theta)
primex = x + 20;
primey = y + 40;
primetheta = theta + pi/6;
robot = RectangleRobot(primex, primey, primetheta);
end
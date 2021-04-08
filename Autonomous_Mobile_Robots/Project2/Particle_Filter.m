%% Move Robot Sample Code 

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% 
% Code by: Matthew Jordan (UVA)
% AMR 2020 
% Date: 10/27/2020
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
clear
close all
clc
%% Parameters


% Workspace Size
xlim([0 100])
ylim([0 100])

%Velocity (constant for this demo example) 
vel = 0;

%Steering angle
steering = pi/4;

%Initialize a vector of positions for the robot
x=[]; 
y=[];
theta=[];

%Initialize a vector of points for filter
points_x=[];
points_y=[];
orientation=[];


%% Robot Initial Pose

x(1) = randi([40 60]);
y(1) = randi([40 60]);
% Initial Orientation
start = randi(10);
theta(1) = pi/start;
dt = 0.1;
% Build Robot Model
robot = RectangleRobot(x,y,theta(1));
plot(robot(:,1),robot(:,2),'-');
hold on
xlim([0 100])
ylim([0 100])
%% Part 1: Point Generation/Distance to Landmarks
Obstacle1x = 25;
Obstacle1y = 25;
plot(Obstacle1x, Obstacle1y, 'bo')
Obstacle2x = 25;
Obstacle2y = 70;
plot(Obstacle2x, Obstacle2y, 'bo')
Obstacle3x = 70;
Obstacle3y = 25;
plot(Obstacle3x, Obstacle3y, 'bo')
Obstacle4x = 70;
Obstacle4y = 70;
plot(Obstacle4x, Obstacle4y, 'bo')
Obstacle5x = 10;
Obstacle5y = 40;
plot(Obstacle5x, Obstacle5y, 'bo')
Obstacle6x = 80;
Obstacle6y = 60;
plot(Obstacle6x, Obstacle6y, 'bo')
for i = 1:1000
    points_x(i) = randi([0 100]);
    points_x(i) = points_x(i) + normrnd(0,0.5);
    points_y(i) = randi([0 100]);
    points_y(i) = points_y(i) + normrnd(0,0.5);
    orientation(i) = pi/randi(10);
    orientation(i) = orientation(i) + normrnd(0,0.5);
end
disp("Predicted Initial Values:")
disp(sum(points_x)/1000);
disp(sum(points_y)/1000);
disp("Actual Values:");
disp(x(1));
disp(y(1));
plot(points_x,points_y,'or','MarkerSize',1); 
title('Particle Filter: 1000 Points')
%% 10 Loop 
nstep = 10;
for k = 1:nstep
    currentx = x(k);
    currenty = y(k);
    currentTheta = theta(k);
    
    distanceToL1 = distance(Obstacle1x, currentx, Obstacle1y, currenty);
    distanceToL1 = distanceToL1 + normrnd(0,8); 

    distanceToL2 = distance(Obstacle2x, currentx, Obstacle2y, currenty);
    distanceToL2 = distanceToL2 + normrnd(0,8); 

    distanceToL3 = distance(Obstacle3x,currentx,Obstacle3y,currenty);
    distanceToL3 = distanceToL3 + normrnd(0,8); 

    distanceToL4 = distance(Obstacle4x,currentx,Obstacle4y,currenty);
    distanceToL4 = distanceToL4 + normrnd(0,8); 

    distanceToL5 = distance(Obstacle5x,currentx,Obstacle5y,currenty);
    distanceToL5 = distanceToL5 + normrnd(0,8); 

    distanceToL6 = distance(Obstacle6x,currentx,Obstacle6y,currenty);
    distanceToL6 = distanceToL6 + normrnd(0,8); 
    
    probabilityArr = [];
    
    for i = 1:1000
        probability = 1;
        L1Distance = distance(points_x(i), Obstacle1x, points_y(i), Obstacle1y);
        probability = probability * gaussian(L1Distance, distanceToL1, normrnd(0,8));
        
        L2Distance = distance(points_x(i),Obstacle2x,points_y(i),Obstacle2y);
        probability = probability * gaussian(L2Distance,distanceToL2, normrnd(0,8));
    
        L3Distance = distance(points_x(i),Obstacle3x,points_y(i),Obstacle3y);
        probability = probability * gaussian(L3Distance,distanceToL3, normrnd(0,8));
    
        L4Distance = distance(points_x(i), Obstacle4x, points_y(i), Obstacle4y);
        probability = probability * gaussian(L4Distance, distanceToL4, normrnd(0,8));
    
        L5Distance = distance(points_x(i),Obstacle5x, points_y(i), Obstacle5y);
        probability = probability * gaussian(L5Distance, distanceToL5, normrnd(0,8));
    
        L6Distance = distance(points_x(i),Obstacle6x, points_y(i), Obstacle6y);
        probability = probability * gaussian(L6Distance, distanceToL6, normrnd(0,8));
    
        probabilityArr(i) = probability;
    end
%% RESAMPLING STEP
    weightSum = sum(probabilityArr);
    weightArr = [];
% Normalize Weights in Array
    for i = 1:1000
        weightArr(i) = probabilityArr(i) / weightSum;
    end

    index = randi([1 1000]);
    maxWeight = 2 * max(weightArr);
    beta = 0;
    counter = 1;
    samples_x = [];
    samples_y = [];
    while length(samples_x) < 1000
        beta = beta + unifrnd(0, maxWeight);
        while weightArr(index) < beta
            beta = beta - weightArr(index);
            index = index + 1;
            if index > 1000
                index = 1;
            end
        end
        samples_x(counter) = points_x(index) + normrnd(0,0.5);
        samples_y(counter) = points_y(index) + normrnd(0,0.5);
        counter = counter + 1;
    end
    x_average = sum(samples_x) / 1000;
    y_average = sum(samples_y) / 1000;
    disp("Predicted Values:");
    disp(x_average);
    disp(y_average);
    disp("Actual Values:")
    disp(currentx);
    disp(currenty);
    figure
    plot(samples_x,samples_y,'or','MarkerSize',1);
    hold on
    plot(Obstacle1x, Obstacle1y, 'bo')
    plot(Obstacle2x, Obstacle2y, 'bo')
    plot(Obstacle3x, Obstacle3y, 'bo')
    plot(Obstacle4x, Obstacle4y, 'bo')
    plot(Obstacle5x, Obstacle5y, 'bo')
    plot(Obstacle6x, Obstacle6y, 'bo')
    robot = RectangleRobot(x(k),y(k),orientation(i));
    plot(robot(:,1),robot(:,2),'-',x,y,'-');
    title('Particle Filter: New 1000 Points')
    xlim([0 100])
    ylim([0 100])
    
    %% Update Points
    x(k+1) = x(k) + 5 * cos(theta(k)) * dt;
    y(k+1) = y(k) + 5 * sin(theta(k)) * dt;
    theta(k+1) = theta(k) + 0.2 * dt;
    
    for i = 1:1000
        samples_x(i) = samples_x(i) + 5 * cos(orientation(i));
        samples_y(i) = samples_y(i) + 5 * sin(orientation(i));
        orientation(i) = orientation(i) + 0.2 * dt;
    end
    
    points_x = samples_x;
    points_y = samples_y;
end
function d = distance(x1,x2,y1,y2)
    d = sqrt((x2 - x1)^2 + (y2-y1)^2);
end
function g = gaussian(x,mean,sigma)
    g = (1/sqrt(2*pi*(sigma^2)))*exp((-(x - mean)^2)/(2*(sigma^2)));
end
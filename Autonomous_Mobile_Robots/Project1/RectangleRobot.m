%% Homogeneous Transformation

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% 
% Code by: Matthew Jordan (UVA)
% AMR 2020 
% Date: 09/20/2020
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

function [robot] = RectangleRobot(x,y,theta)

center = [x y];

% Robot triangle shape

a = [-2 0];
b = [-2 6];
c = [2 0];
d = [2 6];

% Rotation Matrix

rotmat = [cos(theta) -sin(theta); sin(theta) cos(theta)];

rota = (rotmat * (a'));
rotb = (rotmat * (b'));
rotc = (rotmat * (c'));
rotd = (rotmat * (d'));

% Final Robot Configuration after transformation

robot1 = [rota(1) + center(1), rota(2) + center(2)];
robot2 = [rotb(1) + center(1), rotb(2) + center(2)];
robot3 = [rotc(1) + center(1), rotc(2) + center(2)];
robot4 = [rotd(1) + center(1), rotd(2) + center(2)];

robot = [robot1;robot3;robot4;robot2;robot1];
 
end



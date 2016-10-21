clear;

% IIR filter design, using firpm

% Lowpass filter
% specifiy target design
n = 5; % filter order
Wn = 0.125;  % normalized cutoff frequency
Wp = 0.125;  % normalized passband edge frequency 
Ws = 0.2;    % normalized stopband edge frequency 
% compute filter coefficients
[b1,a1] = butter(n,Wn);
[b2,a2]=cheby1(n,1,Wp);
[b3,a3]=cheby2(n,70,Ws);
[b4,a4]=ellip(n,1,70,Wp);
% plot results
filt = fvtool(b1,a1,b2,a2,b3,a3,b4,a4);
legend(filt,'Butterworh','Chebyshev Type 1','Chebyshev Type 2','Elliptic')
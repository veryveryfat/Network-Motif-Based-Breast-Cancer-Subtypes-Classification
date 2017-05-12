%clear;
load ./Data/A_motif_3_P0p_train.csv;
xtrain = A_motif_3_P0p_train;
fid = fopen('./Data/A_motif_3_P0p_train_label');
label = textscan(fid, '%s');
ytrain = label{1};
load ./Data/A_motif_3_P0p_test.csv;
xtest = A_motif_3_P0p_test;
fid = fopen('./Data/A_motif_3_P0p_test_label');
label = textscan(fid, '%s');
ytest = label{1};

avg = 0;
for i = 1:10    
forest = TreeBagger(50,xtrain,ytrain, 'Method', 'classification');
[Yfit,scores] = predict(forest,xtest);
correctness = sum(strcmp(Yfit,ytest))/length(Yfit)
avg = avg + correctness;
end
avg = avg/10


xtrain5 = xtrain;
xtest5 = xtest;
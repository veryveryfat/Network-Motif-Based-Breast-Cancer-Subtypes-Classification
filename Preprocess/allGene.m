clear;
load ./Data/all_sample_relevant_T_train.csv;
xtrain = all_sample_relevant_T_train;
fid = fopen('./Data/all_sample_relevant_T_train_label');
label = textscan(fid, '%s');
ytrain = label{1};
load ./Data/all_sample_relevant_T_test.csv;
xtest = all_sample_relevant_T_test;
fid = fopen('./Data/all_sample_relevant_T_test_label');
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
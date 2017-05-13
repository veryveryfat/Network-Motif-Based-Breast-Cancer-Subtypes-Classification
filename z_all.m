%clear;
load ./Data/Z_all_sample_relevant_train.csv;
xtrain = Z_all_sample_relevant_train;
fid = fopen('./Data/Z_all_sample_relevant_train_label');
label = textscan(fid, '%s');
ytrain = label{1};
load ./Data/Z_all_sample_relevant_test.csv;
xtest = Z_all_sample_relevant_test;
fid = fopen('./Data/Z_all_sample_relevant_test_label');
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
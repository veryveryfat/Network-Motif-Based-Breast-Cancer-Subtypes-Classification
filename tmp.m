load all_sample_relevant.csv;
origin = all_sample_relevant;
Z = zscore(origin,0,2);
csvwrite('Z_all_sample_relevant',Z')


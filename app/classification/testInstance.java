package classification;

import weka.attributeSelection.InfoGainAttributeEval;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.functions.Logistic;
import weka.classifiers.functions.SMO;
import weka.classifiers.functions.SimpleLogistic;
import weka.classifiers.rules.DecisionTable;
import weka.classifiers.rules.PART;
import weka.classifiers.trees.*;
import weka.classifiers.trees.lmt.LogisticBase;
import weka.core.*;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.StringToWordVector;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

/**
 * Created by Dammina on 04/11/2014.
 */
public class testInstance {


    final static String trainingDataFileLocation = "public/dataFiles/train.txt";

    public String getClassifiedDA(String input) throws Exception {


        // deserialize model
        Classifier cls = (Classifier) weka.core.SerializationHelper.read("public/dataFiles/cls.model");

            MakeTestingInstance makeTestInst = new MakeTestingInstance();
            Instances testIns = makeTestInst.getTestingSet(trainingDataFileLocation, input);

            double pred = cls.classifyInstance(testIns.instance(0));
            System.out.println("predicted: " + testIns.classAttribute().value((int) pred));


        return testIns.classAttribute().value((int) pred);
    }
}
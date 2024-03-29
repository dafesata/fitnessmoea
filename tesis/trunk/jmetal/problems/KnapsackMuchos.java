/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jmetal.problems;

import java.text.NumberFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import jmetal.base.*;
import jmetal.base.solutionType.ArrayIntSolutionType;
import jmetal.base.solutionType.BinaryRealSolutionType;
import jmetal.base.solutionType.RealSolutionType;
import jmetal.base.solutionType.ArrayRealSolutionType;
import jmetal.base.solutionType.BinarySolutionType;
import jmetal.base.solutionType.IntRealSolutionType;
import jmetal.base.solutionType.IntSolutionType;
import jmetal.base.solutionType.PermutationSolutionType;
import jmetal.base.variable.Binary;
import jmetal.base.variable.Int;
import jmetal.base.variable.Real;
import jmetal.util.JMException;
import jmetal.util.PseudoRandom;

public class KnapsackMuchos extends Problem {

    double[][] valores;
    double[] VarValor1 = {17,	17,	21,	20,	11,	24,	16,
8,	22,	1,	10,	24,	10,	13,
7,	1,	17,	16,	3,	10,	2,
12,	14,	3,	13,	2,	19,	13,
12,	9,	15,	15,	2,	13,	14,
12,	15,	10,	22,	18,	12,	24,
1,	16,	21,	22,	8,	17,	19,
19,	7,	6,	2,	19,	20,	24,
2,	5,	17,	3,	17,	18,	8,
7,	16,	13,	25,	23,	12,	19
};

    double[] VarValor2 = {3,	12,	17,	8,	25,	3,	20,
20,	20,	1,	2,	20,	22,	9,
6,	2,	9,	22,	16,	8,	8,
9,	1,	3,	18,	23,	9,	2,
25,	14,	15,	16,	19,	4,	8,
2,	12,	17,	15,	10,	22,	4,
4,	21,	5,	18,	14,	18,	11,
2,	7,	8,	16,	1,	15,	15,
11,	7,	20,	14,	14,	15,	15,
5,	16,	3,	18,	18,	2,	25
};

    double[] VarValor3 = {22,	24,	8,	16,	25,	9,	12,
17,	2,	10,	17,	25,	12,	18,
11,	19,	11,	6,	22,	6,	8,
8,	19,	3,	6,	14,	3,	2,
10,	1,	19,	6,	13,	10,	5,
1,	7,	18,	8,	11,	5,	5,
18,	6,	7,	8,	4,	24,	3,
10,	22,	23,	6,	9,	9,	8,
14,	20,	21,	18,	18,	23,	20,
22,	9,	8,	21,	15,	2,	24
};

    double[] VarValor4 = {13,	4,	1,	8,	21,	23,	3,
23,	10,	10,	16,	4,	7,	12,
19,	14,	18,	19,	21,	10,	18,
5,	1,	15,	1,	7,	18,	25,
10,	24,	3,	9,	10,	1,	8,
4,	9,	4,	2,	21,	2,	20,
21,	19,	13,	19,	12,	6,	1,
13,	14,	1,	13,	17,	24,	16,
11,	24,	5,	24,	9,	22,	19,
25,	8,	2,	25,	8,	15,	10
};

    double[] VarValor5 = {6,	22,	2,	13,	16,	14,	25,
23,	7,	11,	7,	5,	23,	24,
24,	21,	14,	12,	2,	19,	7,
19,	23,	15,	15,	16,	15,	21,
9,	3,	11,	2,	22,	8,	24,
12,	9,	1,	10,	20,	19,	11,
19,	9,	9,	8,	11,	8,	1,
16,	11,	17,	7,	18,	15,	1,
23,	10,	24,	5,	15,	16,	11,
25,	21,	17,	24,	15,	20,	18
};

    double[] VarPeso;
    double maxCapacidad1 = 1900.0;
    double[]maxCapacidad;//={5200,4400,4600,1900,1600,2000,900};
    double maxCapacidad2 = 2300.0;
    double maxCapacidad3 = 2280.0;
    double maxCapacidad4 = 2300.0;
    double maxCapacidad5 = 1900.0;
    double maxCapacidad6 = 2000.0;
 public KnapsackMuchos(String solutionType) throws ClassNotFoundException {
    this(solutionType, 3);
  } // Kursawe
 public void llenarValores(){
    
 }
    public KnapsackMuchos(String solutionType, Integer numberOfObj) throws ClassNotFoundException {
        numberOfVariables_=85;
        numberOfObjectives_ = numberOfObj;
        valores = new double[numberOfObj][numberOfVariables_];
        VarPeso = new double[numberOfVariables_];
        maxCapacidad = new double[numberOfObj];
         for (int i =0;i<numberOfObj;i++){
            maxCapacidad[i] = PseudoRandom.randInt((int)(numberOfVariables_*15/numberOfObj), (int)(numberOfVariables_*30/numberOfObj));
         for (int j=0;j<numberOfVariables_;j++){
            valores[i][j]=  PseudoRandom.randInt(1, 50) ;

         }
     }
        for (int j=0;j<numberOfVariables_;j++){
         VarPeso[j]   = PseudoRandom.randInt(1,30);
        }
        numberOfConstraints_ = numberOfObj;
        problemName_ = "KnapsackMuchos";

        lowerLimit_ = new double[numberOfVariables_];
        upperLimit_ = new double[numberOfVariables_];

        for (int var = 0; var < numberOfVariables_; var++) {
            lowerLimit_[var] = 0.0;
            upperLimit_[var] = numberOfObjectives_;
        }

        if (solutionType.compareTo("Real") == 0) {
            solutionType_ = new IntSolutionType(this);
        } else {
            System.out.println("Error: solution type " + solutionType + " invalid");
            System.exit(-1);
        }
    }

    @Override
    public void evaluate(Solution solution) throws JMException {
        Variable[] variables = solution.getDecisionVariables();
        double[] Peso = new double[numberOfObjectives_];
        double[] mochila = new double[numberOfObjectives_]; // function values
        for (int obj = 0; obj<numberOfObjectives_; obj++)
        {


        for (int var = 0; var < numberOfVariables_; var++) {
            if ((variables[var].getValue() == obj) && (mochila.length >= obj)) {
                mochila[obj] = mochila[obj] + valores[obj][var];
            }
         
        }
        }
        // for
        for (int i = 0; i < numberOfObjectives_; i++) {
           
                solution.setObjective(i, (-1) * mochila[i]);
    
          
        }
    }

    @Override
    public void evaluateConstraints(Solution solution) throws JMException {
        Variable[] variables = solution.getDecisionVariables();
        int number = 0;
        double total = 0.0;
        double[] pesos = new double[numberOfConstraints_];
        double[] constraint = new double[numberOfConstraints_];
        for (int i=0;i<numberOfObjectives_;i++){

            for (int var = 0; var < this.numberOfVariables_; var++) {
                if (variables[var].getValue() == i+1) {
                    pesos[i] += VarPeso[var];
                    if (pesos[0] > this.maxCapacidad[i]) {
                        constraint[i] = pesos[i] - maxCapacidad[i];
                    }
                }
            }

        }
//        if (numberOfObjectives_ >= 1) {
//            for (int var = 0; var < this.numberOfVariables_; var++) {
//                if (variables[var].getValue() == 1) {
//                    pesos[0] = pesos[0] + VarPeso[var];
//                    if (pesos[0] > this.maxCapacidad1) {
//                        constraint[0] = pesos[0] - maxCapacidad1;
//                    }
//                }
//            }
//        }
//        if (numberOfObjectives_ >= 2) {
//            for (int var = 0; var < this.numberOfVariables_; var++) {
//                if (variables[var].getValue() == 2) {
//                    pesos[1] = pesos[1] + VarPeso[var];
//                    if (pesos[1] > this.maxCapacidad2) {
//                        constraint[1] = pesos[1] - maxCapacidad2;
//                    }
//                }
//            }
//        }
//        if (numberOfObjectives_ >= 3) {
//            for (int var = 0; var < this.numberOfVariables_; var++) {
//                if (variables[var].getValue() == 3) {
//                    pesos[2] = pesos[2] + VarPeso[var];
//                    if (pesos[2] > this.maxCapacidad3) {
//                        constraint[2] = pesos[2] - maxCapacidad3;
//                    }
//                }
//            }
//        }
//        if (numberOfObjectives_ >= 4) {
//            for (int var = 0; var < this.numberOfVariables_; var++) {
//                if (variables[var].getValue() == 4) {
//                    pesos[3] = pesos[3] + VarPeso[var];
//                    if (pesos[3] > this.maxCapacidad4) {
//                        constraint[3] = pesos[3] - maxCapacidad4;
//                    }
//                }
//            }
//        }
//        if (numberOfObjectives_ >= 5) {
//            for (int var = 0; var < this.numberOfVariables_; var++) {
//                if (variables[var].getValue() == 5) {
//                    pesos[4] = pesos[4] + VarPeso[var];
//                    if (pesos[4] > this.maxCapacidad5) {
//                        constraint[4] = pesos[4] - maxCapacidad5;
//                    }
//                }
//            }
//        }
        for (int i = 0; i < this.numberOfConstraints_; i++) {
            if (constraint[i] > 0.0) {
                number++;
                total += constraint[i];
            }
        }

        solution.setOverallConstraintViolation((-1) * total);
        solution.setNumberOfViolatedConstraint(number);
    }
}

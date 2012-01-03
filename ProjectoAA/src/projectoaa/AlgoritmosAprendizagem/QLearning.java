/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectoaa.AlgoritmosAprendizagem;

import java.util.Random;

/**
 *
 * @author Bruno
 */
public class QLearning {

    private int[][] R;
    private double[][] Q;

    public void init(int s, int a) {
        R[s][a] = 100;
    }

    private int R(int s, int a) {
        return R[s][a];
    }

    public void setQ(int s, int a, double value) {
        Q[s][a] = value;
    }

    private double Q(int s, int a) {
        return Q[s][a];
    }

    double maxQ(int s, int[][] accoes) {
        int[] actionsFromState = accoes[s];
        double maxValue = Double.MIN_VALUE;
        for (int i = 0; i < actionsFromState.length; i++) {
            int nextState = actionsFromState[i];
            double value = Q[s][nextState];

            if (value > maxValue) {
                maxValue = value;
            }
        }
        return maxValue;
    }

    void run(int contadorEstados, int [][]estados, int[][] accoes, double learningRate, double discountRate, int goalState) {


        // Por cada episódio
        Random rand = new Random();
        for (int i = 0; i < 1000; i++) { // treino
            // Selecciona aleatóriamente um estado inicial
            int state = rand.nextInt(contadorEstados);
            while (state != goalState) //estado a que se quer chegar
            {
                // Select one among all possible actions for the current state
                int[] actionsFromState = estados[state];

                // Selection strategy is random in this example
                int index = rand.nextInt(actionsFromState.length);
                int action = actionsFromState[index];

                // Action outcome is set to deterministic in this example
                // Transition probability is 1
                int nextState = action; // data structure

                // Using this possible action, consider to go to the next state
                double q = Q(state, action);
                double maxQ = maxQ(nextState, accoes);
                int r = R(state, action);

                double value = q + learningRate * (r + discountRate * maxQ - q);
                setQ(state, action, value);

                // Set the next state as the current state
                state = nextState;
            }
        }
    }
}

package schmitt.joao.chapter_1.saco;

import java.util.ArrayList;
import java.util.List;

/**
 * S-ACO (Simple Ant Colony Optimization)
 *
 * S-ACO é um algoritmo que se adapta ao comportamento real das formigas para busca de uma solução de caminho mais
 * curto. Para cada aresta (i,j) do grafo G = (N,A) nós associamos uma variável τij chamada trilha de feromônios
 * artificiais. As trilhas de feromônios são lidas e escritas pelas formigas. O montante (intensidade) de feromônio é
 * proporcional a sua utilidade, como estimado pelas formigas, de usar aquele arco para construir boas soluções.
 *
 */
public class SACO {

    private static int bestPath[];

    public static void main(String[] args) {

        Parameters.configureExtendedDoubleBridge();

        Ant[] ants = new Ant[Parameters.antPopSize];
        for(int a = 0; a < ants.length; a++) {
            ants[a] = new Ant();
            ants[a].setCurrentNode(Parameters.source);
        }

        initializePheromone();

        for(int t = 0; t < Parameters.timeSize; t++) {
            for(int a = 0; a < ants.length; a++) {
                evaporatePheromone();
                moveAnt(ants[a]);
            }
        }

        System.out.print("Best (" + bestPath.length + ") = ");
        for(int i = 0; i < bestPath.length; i++) {
            System.out.print(bestPath[i]);
            if(i < bestPath.length - 1) {
                System.out.print("->");
            }
        }
        System.out.println();
    }

    public static void moveAnt(Ant ant) {
        //Find next node
        if(ant.getTargetNode() == -1) {
            int nextNode = getNextNode(ant.getCurrentNode(), ant.getPredecessor());
            ant.setTargetNode(nextNode);
        }
        //Move in direction
        if(ant.getDistance() < Parameters.graph[ant.getCurrentNode()][ant.getTargetNode()]) {
            ant.setDistance(ant.getDistance() + Parameters.antSpeed);
        }
        //If was achieved the target
        if(ant.getDistance() >= Parameters.graph[ant.getCurrentNode()][ant.getTargetNode()]) {
            int target = ant.getTargetNode();
            if(target == Parameters.destination) {
                ant.addNode(ant.getCurrentNode());
                ant.addNode(ant.getTargetNode());
                calculatePheromone(ant.getOptimizedPath());
                ant.setCurrentNode(Parameters.source);
                ant.setTargetNode(-1);
                ant.clear();
            } else {
                ant.addNode(ant.getCurrentNode());
                ant.setCurrentNode(ant.getTargetNode());
                ant.setTargetNode(-1);
            }
        }
    }


    public static void evaporatePheromone() {
        for(int i = 0;  i < Parameters.graph.length; i++) {
            for (int j = 0; j < Parameters.graph[i].length; j++) {
                if(Parameters.graph[i][j] > 0.0) {
                    Parameters.pheromone[i][j] *= Parameters.evaporation;
                    if(Parameters.pheromone[i][j] < Parameters.pheromoneMin) {
                        Parameters.pheromone[i][j] = Parameters.pheromoneMin;
                    }
                }
            }
        }
    }

    public static void calculatePheromone(int[] path) {
        double pheromone = 1 / path.length;
        if(bestPath == null || path.length < bestPath.length) {
            bestPath = path;
        }
        for(int n = 0; n < path.length - 1; n++) {
            Parameters.pheromone[n][n + 1] += pheromone;
        }
    }

    public static void initializePheromone() {
        for(int i = 0;  i < Parameters.graph.length; i++) {
            for (int j = 0; j < Parameters.graph[i].length; j++) {
                if(Parameters.graph[i][j] > 0.0) {
                    Parameters.pheromone[i][j] = Parameters.pheromoneMin;
                }
            }
        }
    }

    public static int getNextNode(int i, int predecessor) {
        double totalSum = 0.0;
        double[] probabilities = new double[Parameters.N];
        for(int j = 0; j < Parameters.N; j++) {
            if(Parameters.graph[i][j] > 0.0 && j != predecessor) {
                totalSum += Parameters.pheromone[i][j];
                probabilities[j] = Parameters.pheromone[i][j];
            } else {
                probabilities[j] = -1;
            }
        }
        double cumSum = 0.0;
        double rand = Math.random();
        for(int j = 0; j < Parameters.N; j++) {
            if(probabilities[j] >= 0) {
                probabilities[j] = probabilities[j] / totalSum;
                probabilities[j] += cumSum;
                cumSum = probabilities[j];
                if(rand < cumSum) {
                    return j;
                }
            }
        }
        return predecessor;
    }


}

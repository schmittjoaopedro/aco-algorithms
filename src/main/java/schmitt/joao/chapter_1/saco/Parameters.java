package schmitt.joao.chapter_1.saco;

/**
 * Created by root on 09/09/17.
 */
public class Parameters {

    public static int N; //Vertices number

    public static double graph[][];

    public static double pheromone[][];

    public static int source = 0;

    public static int destination = 8;

    public static double pheromoneMin = 1.0;

    public static int antPopSize = 5;

    public static int timeSize = 100;

    public static double antSpeed = 0.5;

    public static double evaporation = 0.01;

    public static void configureExtendedDoubleBridge() {
        N = 19;
        graph = new double[N][N];
        pheromone = new double[N][N];

        graph[0][1] = 1.0;
        graph[0][9] = 1.0;
        graph[1][0] = 1.0;
        graph[1][2] = 1.0;
        graph[2][1] = 1.0;
        graph[2][3] = 1.0;
        graph[3][2] = 1.0;
        graph[3][4] = 1.0;
        graph[4][3] = 1.0;
        graph[4][5] = 1.0;
        graph[5][4] = 1.0;
        graph[5][6] = 1.0;
        graph[6][5] = 1.0;
        graph[6][7] = 1.0;
        graph[7][6] = 1.0;
        graph[7][8] = 1.0;
        graph[8][7] = 1.0;
        graph[8][13] = 1.0;
        graph[9][0] = 1.0;
        graph[9][10] = 1.0;
        graph[9][16] = 1.0;
        graph[10][9] = 1.0;
        graph[10][11] = 1.0;
        graph[10][12] = 1.0;
        graph[10][14] = 1.0;
        graph[11][10] = 1.0;
        graph[11][12] = 1.0;
        graph[12][10] = 1.0;
        graph[12][11] = 1.0;
        graph[12][13] = 1.0;
        graph[12][15] = 1.0;
        graph[13][8] = 1.0;
        graph[13][12] = 1.0;
        graph[13][17] = 1.0;
        graph[14][10] = 1.0;
        graph[14][15] = 1.0;
        graph[14][16] = 1.0;
        graph[15][12] = 1.0;
        graph[15][14] = 1.0;
        graph[15][17] = 1.0;
        graph[16][9] = 1.0;
        graph[16][14] = 1.0;
        graph[16][17] = 1.0;
        graph[16][18] = 1.0;
        graph[17][13] = 1.0;
        graph[17][15] = 1.0;
        graph[17][16] = 1.0;
        graph[17][18] = 1.0;
        graph[18][16] = 1.0;
        graph[18][17] = 1.0;

    }

}

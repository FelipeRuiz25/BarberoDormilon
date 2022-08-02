package util;

import java.util.Random;

/**
 * Es la clase encargada de generar numeros aleatorios para el juego
 */
public class RandomNumberGenerator {

    private static Random random = new Random();

    /**
     * genera un pseudo numero aleatorio entre el valor minimo y el valor maximo(sin tomar el valor
     * maximo)
     * @param minBound valor minimo
     * @param maxBound valor máximo
     * @return pseudo numero aleatorio mayor o igual a minBound y menor que maxBound
     * @see Random
     */
    public static int getRandIntBetween(int minBound, int maxBound){
        return random.nextInt(maxBound-minBound) + minBound;
    }

    /**
     * genera un numero pseudoaleatorio entre 0 y bound(sin tomar bound)
     * @param bound valor maximo
     * @return pseudonumero mayor o igual a 0 y menor que bound
     * @see Random
     */
    public static int getRandInt(int bound){
        return random.nextInt(bound);
    }
}

package io.paulbaker.generator;

import java.util.Random;

/**
 * This is based on the implementation online here: <a
 * href="http://freespace.virgin.net/hugo.elias/models/m_perlin.htm">
 * http://freespace.virgin.net/hugo.elias/models/m_perlin.htm</a>
 * 
 * @author paulbaker
 */
public class PerlinNoiseGenerator {

  private static final Random RAND;
  static {
    RAND = new Random();
  }

  private PerlinNoiseGenerator() {}

  public static double[][] generate(int width, int length, long seed) {
    RAND.setSeed(seed);
    return generate(width, length);
  }

  public static double[][] generate(int width, int length) {
    if (width == 0 || length == 0)
      throw new IllegalArgumentException("You cannot have a 0 length or width");
    // Initialize the two edges of the field
    double[][] field = new double[width][length];
    for (int i = 0; i < field.length; i++) {
      field[i][0] = RAND.nextDouble();
    }
    for (int i = 0; i < field[0].length; i++) {
      field[0][i] = RAND.nextDouble();
    }
    // Interpolate all the points in between
    for (int i = 1; i < field.length; i++) {
      for (int j = 1; j < field[i].length; j++) {
        field[i][j] = interpolateCosine(field[0][i], field[j][0], 0.5);
      }
    }
    return field;
  }

  private static double interpolateLinear(double a, double b, double x) {
    checkInterpolateX(x);
    return a * (1 - x) + b * x;
  }

  private static double interpolateCosine(double a, double b, double x) {
    checkInterpolateX(x);
    double ft = x * Math.PI;
    double f = (1 - Math.cos(ft)) * 0.5;
    return a * (1 - f) + b * f;
  }

  // private double interpolateCubic(double a, double b, double x) {
  // checkInterpolateX(x);
  // return 0f;
  // }

  private static void checkInterpolateX(double x) {
    if (x < 0 || x > 1)
      throw new IllegalArgumentException("X must be between [0, 1]");
  }
}

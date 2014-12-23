package io.paulbaker.generator;

import java.util.Random;

public class PerlinNoiseGenerator {

  private static final Random RAND;
  static {
    RAND = new Random();
  }

  private PerlinNoiseGenerator() {}

  public static void generate(int width, int length, long seed) {
    RAND.setSeed(seed);
    generate(width, length);
  }

  public static void generate(int width, int length) {

  }

  private double interpolateLinear(double a, double b, double x) {
    checkInterpolateX(x);
    return a * (1 - x) + b * x;
  }

  private double interpolateCosine(double a, double b, double x) {
    checkInterpolateX(x);
    double ft = x * Math.PI;
    double f = (1 - Math.cos(ft)) * 0.5;
    return a * (1 - f) + b * f;
  }

  // private double interpolateCubic(double a, double b, double x) {
  // checkInterpolateX(x);
  // return 0f;
  // }

  private void checkInterpolateX(double x) {
    if (x < 0 || x > 1)
      throw new IllegalArgumentException("X must be between [0, 1]");
  }
}

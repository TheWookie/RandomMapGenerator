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

  private float interpolateLinear(int a, int b, int x) {
    return 0f;
  }

  private float interpolateCosine(int a, int b, int x) {
    return 0f;
  }

  private float interpolateCubic(int a, int b, int x) {
    return 0f;
  }
}

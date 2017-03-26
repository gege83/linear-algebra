package com.gergo.takacs.linearAlgebra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Vector {
    private List<Double> coordinates;

    public Vector(List<Double> coordinates) {
        if (coordinates.isEmpty()) {
            throw new EmptyCollectionNotPermitted();
        }
        this.coordinates = coordinates;
    }

    public Vector(double first, Double... coordinates) {
        this(convertToList(first, coordinates));
    }

    private static List<Double> convertToList(double first, Double[] coordinates) {
        List<Double> list = new ArrayList<>(coordinates.length + 1);
        list.add(first);
        list.addAll(Arrays.asList(coordinates));
        return list;
    }

    public Vector add(Vector vector) {
        List<Double> newCoords = zipped(coordinates, vector.coordinates, (c1, c2) -> c1 + c2).collect(Collectors.toList());
        return new Vector(newCoords);
    }

    <A, B, C> Stream<C> zipped(List<A> lista, List<B> listb, BiFunction<A, B, C> zipper) {
        int shortestLength = Math.min(lista.size(), listb.size());
        return IntStream.range(0, shortestLength).mapToObj((int i) -> zipper.apply(lista.get(i), listb.get(i)));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector vector = (Vector) o;
        return Objects.equals(coordinates, vector.coordinates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinates);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Vector{");
        sb.append("coordinates=").append(coordinates);
        sb.append('}');
        return sb.toString();
    }

    public Vector sub(Vector vector) {
        List<Double> newCoords = zipped(coordinates, vector.coordinates, (c1, c2) -> c1 - c2).collect(Collectors.toList());
        return new Vector(newCoords);
    }

    public Vector scalarProduct(double multiplier) {
        List<Double> newCoords = coordinates.stream().map(c -> c * multiplier).collect(Collectors.toList());
        return new Vector(newCoords);
    }


    public double dotProduct(Vector vector) {
        return zipped(coordinates, vector.coordinates, (c1, c2) -> c1 * c2).mapToDouble(c1 -> c1).sum();
    }

    public double magnitude() {
        double squareSum = coordinates.stream().mapToDouble(c -> c * c).sum();
        return Math.sqrt(squareSum);
    }

    public Vector normalize() {
        double magnitude = magnitude();
        List<Double> newCoords = coordinates.stream().map(c -> c / magnitude).collect(Collectors.toList());
        return new Vector(newCoords);
    }

    public double angle(Vector vector) {
        double product = this.dotProduct(vector);
        return Math.acos(product / (magnitude() * vector.magnitude()));
    }

    public double angleDeg(Vector vector) {
        return Math.toDegrees(angle(vector));
    }

    public boolean isOrthogonal(Vector vector) {
        double dotProduct = dotProduct(vector);
        return isZero(dotProduct);
    }

    private boolean isZero(double dotProduct) {
        return dotProduct < 10e-10;
    }

    public boolean isParallel(Vector vector) {
        double angle = angle(vector);
        return isPi(angle) || isZero(angle);
    }

    private boolean isPi(double angle) {
        return Math.abs(angle - Math.PI) < 10e-10;
    }

    public Vector projectTo(Vector vector) {
        Vector unit = vector.normalize();
        return unit.scalarProduct(this.dotProduct(unit));
    }

    public Vector projectToOrthogonal(Vector vector) {
        return this.sub(projectTo(vector));
    }

    public Vector crossProduct(Vector vector) {
        double x1 = coordinates.get(0);
        double y1 = coordinates.get(1);
        double z1 = getZ();
        double x2 = vector.coordinates.get(0);
        double y2 = vector.coordinates.get(1);
        double z2 = vector.getZ();
        return new Vector(Arrays.asList(y1 * z2 - y2 * z1, x2 * z1 - x1 * z2, x1 * y2 - x2 * y1));
    }

    private Double getZ() {
        if (coordinates.size() == 2) {
            return 0d;
        }
        return this.coordinates.get(2);
    }

    public double parallelogramArea(Vector vector) {
        return crossProduct(vector).magnitude();
    }

    public double triangleArea(Vector vector) {
        return parallelogramArea(vector) / 2;
    }
}



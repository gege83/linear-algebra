from numbers import Number
from math import *

class Vector(object):
    def __init__(self, coordinates):
        try:
            if not coordinates:
                raise ValueError
            self.coordinates = tuple(coordinates)
            self.dimension = len(coordinates)

        except ValueError:
            raise ValueError('The coordinates must be nonempty')

        except TypeError:
            raise TypeError('The coordinates must be an iterable')


    def __str__(self):
        return 'Vector: {}'.format(self.coordinates)


    def __eq__(self, v):
        return self.coordinates == v.coordinates

    def __add__(self, other):
        newCoords = [x-y for x,y in zip(self.coordinates, other.coordinates)]
        return Vector(newCoords)

    def __sub__(self, other):
        newCoords = [x-y for x,y in zip(self.coordinates, other.coordinates)]
        return Vector(newCoords)

    def scalar(self, scalar):
        newCoords = [x*scalar for x in self.coordinates]
	return Vector(newCoords)

    def innerProduct(self, other):
        coordinateProducts = [x*y for x,y in zip(self.coordinates, other.coordinates)]
        return sum(coordinateProducts)

    def __rmul__(self, other):
        return self.__mul__(other)

    def __mul__(self, other):
        if isinstance(other, Number):
            return self.scalar(other)
        elif isinstance(other, Vector):
            return self.innerProduct(other)

    def length(self):
        coordinateSquares = [x**2 for x in self.coordinates]
        return sqrt(sum(coordinateSquares))

    def normalize(self):
	try:
            length = self.length()
	    return 1./length*self
        except ZeroDivisionError:
            raise Exception('cannot normalize the zero vector')

    def angle(self, other):
        prod = self.innerProduct(other)
        result = prod / (self.length() * other.length())
	return acos(result)

    def angleInDeg(self, other):
        return degrees(self.angle(other))

    def isOrthogonal(self, other, tolerance=1e-10):
        return abs(self*other)<tolerance

    def isParalel(self, other):
        if self.isZero() or other.isZero() :
            return True
        angle = self.angle(other)
        return angle == 0 or angle == pi 

    def isZero(self, tolerance=1e-10):
        return self.length()<tolerance

    def projectTo(self, base):
        unit = base.normalize()
	return (self*unit)*unit

    def projectToOrth(self, base):
        return self-self.projectTo(base)


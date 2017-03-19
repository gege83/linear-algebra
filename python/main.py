from vector import *

myVector1 = Vector([2,2,2])
print myVector1

myVector2 = Vector([2,2,3]);

print myVector1+myVector2;
print myVector1-myVector2;
print 12*myVector1;
print myVector1*12;
print myVector1*myVector2;
print myVector1.length();
print myVector1.angle(myVector2);
print myVector1.angleInDeg(myVector2);
print myVector1.normalize()

v1 = Vector([-2.328, -7.284, -1.214])
w1 = Vector([-1.821, 1.072, -2.94])
print v1.isOrthogonal(w1)
print v1*w1
print v1.isParalel(w1)
print v1.angle(w1)


v1 = Vector([3.039, 1.879])
w1 = Vector([0.825, 2.036])

v2 = Vector([-9.88, -3.264, -8.159])
w2 = Vector([-2.155, -9.353, -9.473])

v3 = Vector([3.009, -6.172, 3.692, -2.51])
w3 = Vector([6.404, -9.144, 2.759, 8.718])

print v1.projectTo(w1)
print v1.projectToOrth(w1)

print v2.projectTo(w2)
print v2.projectToOrth(w2)
print v2.projectTo(w2).isOrthogonal(v2.projectToOrth(w2))

print v3.projectTo(w3)
print v3.projectToOrth(w3)

v1 = Vector([8.462, 7.893, -8.187])
w1 = Vector([6.984, -5.975, 4.778])

v2 = Vector([-8.987, -9.838, 5.031])
w2 = Vector([-4.268, -1.861, -8.866])

v3 = Vector([1.5, 9.547, 3.691])
w3 = Vector([-6.007, 0.124, 5.772])

print v1.crossProduct(w1)
print v2.paralelogramArea(w2)
print v3.triangleArea(w3)


# Hyperbolic Random Graph Visulisation

This project produces a visualisation of the hyperbolic random graph, with a GUI which allows the real time editing of the main parameters of the model. The parameters are:

* R - The radius of the disk and connection radius.
* α - The strength of the power law distribution.
* N - The number of nodes in the graph.

## Context

The hyperbolic random graph was introduced as a model for real-world complex networks. Hyperbolic random graphs usually have the properties expected of real-world networks such as a power-law degree distribution and small-world properties. We assume the network lives on a 2-Dimensional Poincare disk of a fixed radius R. A fixed number of points are then randomly distributed on the disk as follows: For each point p with polar co-ordinates (r, θ), we have that θ ~ U(0, 2π), while r possess the probablity density function: p(r) = αSinh(αr)/(Cosh(αR) - 1).

The distance between points is given by the hyperbolic cosine rule,

For points P<sub>1</sub> = (r<sub>1</sub>, θ<sub>1 </sub>) and P<sub>2</sub> = (r<sub>2</sub>, θ<sub>2</sub>) we have that:

d(P<sub>1</sub>, P<sub>2</sub>) = arCosh( Cosh(r<sub>1</sub>)Cosh(r<sub>2</sub>) - Sinh(r<sub>1</sub>)Sinh(r<sub>2</sub>)Cos(π - |π - |θ<sub>1</sub> - θ<sub>2</sub> ||).

The quantity π - |π - |θ<sub>1</sub> - θ<sub>2</sub> ||, represents the smallesr relative angle between P<sub>1</sub> and P<sub>2</sub>.

If we have that d(P<sub>1</sub>, P<sub>2</sub>) < R, then we will connect these two points in the graph. The given project produces a visulisation of this using two pacakages, one which generates the underlying network for a fixed set of parameters, while a second which produces an associated GUI for tweaking parameters.

## File Structure

Comprised of two packages, the first is "Hyperbolic Graph Construction" package. This package focuses on creating the hyperbolic random graph. Vertices are held in an array, (will eventually change to ArrayList for readability). Each vertex holds a pair of random polar co-ordinates given by the appropriate distribution. Each pair is then compared to compute whether there should be an edge between them. The notion of an edge is indicated by a verticies adding each other to their neighbourhood arrays.

The second package is designed to hold a Hyperbolic Random graph and draw it on the Canvas with a specific projection for radial co-ordinates. The GUI contains settings for adjusting α, R and n, (would like to add an adjustment for v at some point). There also choices between the type of projection of the Canvas, the linear scale factor tends to be be a better repsentation. A max Radii of around 6.2 is usually best. 



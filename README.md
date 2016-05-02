# RamificayPoda
Algoritmo ramifica y poda pensado para una pasteleria. 

Descripción del problema
En una pastelería hay n pasteleros, que aunque son capaces de hacer cualquiera de los m tipos
de pasteles distintos que ofrece la pastelería, tienen distinta destreza, y por tanto mayor
beneficio de venta por su calidad en la preparación de cada uno de ellos. Se desea asignar una
lista de n pedidos de pasteles, uno a cada pastelero, maximizando el beneficio de ventas total de
la preparación de todos los pasteles. Para ello se conoce de antemano la tabla de beneficios
C[1..n, l..m] en la que el valor Cij corresponde el beneficio que da el pastelero i por realizar el
pastel j. También se conoce los tipos de pasteles correspondientes a los pedidos mediante el
vector pedidos[1..n], de tal forma que pedidos[i] indica el tipo de pastel que se solicita en la
posición i de la lista del pedido. Por ejemplo, para la siguiente tabla:

| 1 | 2 | 3 |
|---|---|---|
| 2 | 5 | 3 |
| 5 | 3 | 2 |
| 6 | 4 | 9 |
| 6 | 3 | 8 |
| 7 | 5 | 8 |

y un pedido:
  1 1 3 2 1
Una solución podría ser la asignación de los pasteleros siguiente al pedido: [5,4,3,1,2] que tiene un beneficio de 32.

# Descripción de la entrada
Los datos de entrada se recogen en un fichero con el siguiente formato:
 En la primera línea se indica el valor de n y m separados por un espacio en blanco
 En las sucesivas n líneas siguientes se indican el valor de las filas de la tabla C, de tal forma que cada línea corresponde a una fila completa. Dentro de cada línea, los valores
de las columnas se separan con un espacio en blanco
 En la última línea se indica el pedido de los pasteles (donde se separan por un espacio en blanco cada uno de los tipos de pasteles).

# Descripción de la salida
La salida es un fichero que contiene dos líneas. En la primera línea se muestra el vector de asignación resultante, es decir, la lista de los números de pastelero asignado a cada pastel pedido,
separados por comas. En la segunda línea se muestra el valor del beneficio obtenido para esa asignación.

# clusteringHumano
Este proyecto está compuesto por las siguientes clases: CargaDatosInicial, VistaDatos, Clustering, Grafo y BFS.
CargaDatosInicial
Esta clase contiene los siguientes elementos:
Un objeto de la clase Clustering.
También, un JLabel que informa reducidamente lo que se tiene que hacer en esta ventana inicial.
Tiene un JTextField para introducir el nombre de la persona y 4 JComboBox para seleccionar del 1 al 5 el nivel de interés por cada tema indicado en 4 JLabel ubicados al lado de cada cuadro.
Debajo de la ventana se encuentran 2 JButton.
El botón “Cargar” , cuando es seleccionado, genera un objeto de la clase VistaDatos, llama a la función generarGrupos. Esta función llama a la función generarGrupos del objeto Clustering. Envia los datos de las personas ingresadas al objeto VistaDatos. Envia los grupos generados al objeto VistaDatos. Y por último cierra esta ventana y abre la siguiente (VistsaDatos). 
VistaDatos
Esta clase tiene un hashMap para las personas con un String como clave y un array de enteros como valor (donde se guardan los intereses) y 2 Set que guarda los grupos que recibe de la clase CargaDatosInicial.
En cuanto a elementos de interfaz, hay 2 JTable, una para mostrar los datos y otra para mostrar los grupos. Cada una está puesta en un JScrollPane. El motivo de esta implementación es que fue la solución que encontré para que muestre los títulos de las columnas.
También cuenta con 2 JButton.
El botón “Ver datos”, cuando es seleccionado, se crea un objeto DefaultTableModel para poder trabajar con la tabla. Se agregan los datos del HashMap personas a la tabla y se hace visible el ScrollPane que la contiene y se deshabilita el botón.
El botón “Mostrar grupos” hace algo parecido con el bóton “Ver datos”. Crea el DefaultTableModel para agregar los 2 Sets en cada columna de la tabla. Se muestra el ScrollPane y se dehabilita el botón. 

Clustering
Esta clase de negocio tiene un HashMap para las personas, 2 Sets para los grupos, un grafo, un árbol generador mínimo y una variable auxiliar nombres que es un arraylist.
Tiene una función principal agregarPersona que recibe como parámetros el nombre y los niveles de los 4 intereses. Si los parámetros son válidos, se guardan en el hashmap sino, lanza una excepción.
La otra función principal es generarGrupos. Si la cantidad de personas ingresadas es menor a 2, lanza una excepción ya que no tendría sentido generar grupos con menos de 2 personas ingresadas. Sino, llama a la función generarGrafo. Esta función instancia un grafo, asigna un numero a cada persona y se agregan las aristas con el peso. Ese peso lo da la función similaridad que hace lo que se pide en la consigna del trabajo práctico.
Luego se llama a la función generarAGM que llama al método del mismo nombre del objeto Grafo y lo guarda en la variable del árbol generador mínimo.
A continuación se llama a la función eliminarAristaDeMayorPeso que llama al método estático de Grafo que devuelve los vértices de la arista y lo guarda en un array vértices.
Por último, llama a la función generarGrupo que recibe como parámetro los vértices de la arista eliminada y guarda el set que retorna en las variables grupo1 y grupo2.
Grafo
Esta clase que ya fue creada en la cursada.
Lo que se agregó como variable fue una matriz que guarda los pesos de las aristas.
En cuanto a los métodos, se agregó uno nuevo para agregar aristas que recibe como parámetro, a parte de los vértices, el peso y lo guarda en la matriz.
También cuenta con una función que devuelve el peso de una arista.
Se agregó un método que devuelve cuál es el peso más grande de las aristas del grafo. Esto lo hace recorriendo la matriz y guardando el peso máximo que al final lo retorna.
Una decisión de implementación fue generar el árbol generador mínimo en la clase Grafo porque el proyecto no necesita realizar más acciones con este. Por este motivo, se creó una función generarAGM que implementa el algoritmo de Prim. Básicamente pone un vertice en un conjunto y agarra sus vecinos. El vecino que tenga menor peso se agrega en el conjunto y se agrega la arista. Este ciclo se hace vértices-1 veces.
Por último, eliminarAristaDeMayorPeso recorre todos los vértices y sus vecinos, y pregunta por la arista de mayor peso. Va guardando el peso y los 2 vertices. Al final retorna los dos vértices de la arista de mayor peso.

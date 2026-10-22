# Invernadero con hilos

## ¿Qué hace?
Simula el sistema de control y monitorización de un invernadero

## ¿Cómo ejecutarlo?
Hay que compilar el proyecto y ejecutar la clase correspondiente a cada fase

## Captura
<img width="566" height="425" alt="image" src="https://github.com/user-attachments/assets/369ab941-f687-4d5f-ab8d-ab24757e83d3" />

## Resultados
* **Fase 1: Secuencial** -> Tiempo total: 34305ms
* **Fase 2: Concurrente** -> Tiempo total: 10027ms

## Análisis
### ¿Por qué compensa usar hilos en el invernadero?
Compensa usar hilos porque pasas de tardar la suma de los tiempos de cada tarea a tardar solo lo que dure el proceso más lento.

### Experimento A
Se imprime al inicio informe con 0 lecturas, mientras que la segunda llamada muestra las 5 lecturas completas.
Esto ocurre porque el método start() arranca el hilo en segundo plano y devuelve el control del hilo al main y, como main no espera, accede al sensor antes de que el nuevo hilo haya completado su bucle

### Experimento B
En el log, el nombre del hilo que ejecuta el riego es main y no riego y, al consultar riego.setState() tras finalizar run(), el estado sigue siendo NEW

### Experimento C
Se lanza la excepción IllegalThreadStateException

### Estados observados
* **New:** Se produce antes de invocar la ejecución del hilo. La línea es new Thread(...)
* **Runnable:** Se produce cuando el hilo está listo para ejecutarse. Se provoca tras start()
* **Timed_Waiting:** Se produce durante las pausas programadas de lectura y ciclos de riego. Sale tras la llamada Thread.sleep(periodo)
* **Terminated:** Se produce una vez que el método run() ha terminado todas sus iteraciones, cuando ocurre el fin de la ejecución del método run()

### ¿Por qué termina el programa si el vigilante sigue en un bucle infinito?
El vigilante se define como un hilo demonio con setDaemon(true) y la JVM no espera a que los demonios terminen, por lo que finaliza el programa automáticamente en cuanto acaban los hilos principales

### Thread frente Runnable
#### Tabla comparativa
| Aspecto | Runnable | Thread |
| :--- | :--- | :--- |
| **Concepto / Rol** | Representa una tarea que debe ser ejecutada. | Representa tanto el hilo de ejecución como la tarea. |
| **Herencia en Java** | Permite heredar de otra clase base. Java admite implementar múltiples interfaces. | Ocupa la única herencia simple de Java (`extends`), impidiendo heredar de otra clase. |
| **Obtención del nombre** | Requiere llamar a `Thread.currentThread().getName()`. | Dispone directamente del método heredado `this.getName()`. |
| **Arranque y acoplamiento** | Requiere envolverse en un hilo: `new Thread(runnable).start()`. Desacopla la lógica del ciclo de vida del hilo. | Se inicia directamente invocando sobre la instancia: `objeto.start()`. Tarea y ciclo de vida quedan fuertemente acoplados. |

#### ¿Por qué los sensores SOLO pueden ser Runnable en tu diseño?
Porque las clases Temperatura, Humedad y Luz ya hereda de la clase abstracta Sensor para compartir lógica común

#### ¿Qué forma elegirías para el riego y por qué?
Es preferible implementar Runnable, ya que desacopla el trabajo que realiza del mecanismo de ejecución, permitiendo que pueda ser gestionado por un gestor de tareas sin acoplarlo al ciclo de vida de un Thread concreto

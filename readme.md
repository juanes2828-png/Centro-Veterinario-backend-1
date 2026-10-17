# Hospital Veterinario

Este es un proyecto de consola que desarrollé para gestionar los pacientes y las jaulas de una clínica veterinaria. Lo construí aplicando los conceptos principales de la Programación Orientada a Objetos (POO).

## Que hice

Se creó un sistema que permite ingresar pacientes, controlar tratamientos, asignar jaulas y darles de alta teniendo cuenta las reglas del negocio. La codificación se realizó utizando la inteligencia artificial y validando cada paso que registraba. 

* Manejo de memoria fija: Trabajé únicamente con arreglos de tamaño fijo para los pacientes (máximo 10) y las jaulas (máximo 6).
* Control de estados: Los pacientes cambian de estado según lo que pase con ellos, pasando por INGRESADO, CUARENTENA o EN\_TRATAMIENTO (solo se permiten estas opciones).
* Reglas clínicas: Agregué la lógica para calcular las dosis máximas de medicamento dependiendo de la especie y el peso. También controlé que el estrés de los gatos no suba demasiado y validé que las jaulas asignadas correspondan al tamaño y la zona adecuada para cada animal.
* Compactación de vectores: Escribí la lógica manual para reorganizar el arreglo de pacientes cada vez que se da de alta a un animal, desplazando los elementos para no dejar espacios vacíos.

## Como lo construi

El código fue escrito en IntelliJ IDEA con apoyo de la IA gemini y lo estructuré pensando en separar muy bien las reglas del negocio de lo que ve el usuario en la consola. Apliqué estos principios:

1. Herencia: Creé una clase base llamada Paciente con los atributos generales. De ahí saqué tres subclases (Perro, Gato y AveExotica) que reutilizan el código base pero definen sus propios modificadores de dosis llamando al constructor principal.
2. Polimorfismo: Guardé todos los tipos de animales mezclados en un solo arreglo general de pacientes dentro de la clase Hospital.
3. Encapsulamiento: Todos los atributos son privados. Los métodos para modificar datos (setters) tienen validaciones que bloquean cualquier intento de guardar pesos negativos, edades irreales o textos vacíos.
4. Separación de responsabilidades: Me aseguré de que ninguna clase del dominio (como Paciente o Jaula) interactúe con la consola. Toda la lectura de comandos y la impresión de mensajes en pantalla ocurren exclusivamente en la clase Main.

## Instrucciones para ejecutarlo

Como el proyecto no tiene paquetes declarados, compilarlo y ejecutarlo desde la consola es muy directo. Solo debes seguir estos pasos:

1. Abre tu terminal o línea de comandos.
2. Navega hasta la carpeta src del proyecto.
3. Compila todos los archivos escribiendo el comando: javac \*.java
4. Ejecuta el programa principal con el comando: java Main

## Comandos disponibles

Una vez que el programa esté corriendo, puedes escribir cualquiera de estos comandos:

* ingresar perro <nombre> <peso> <edad> <raza> <si/no>
* ingresar gato <nombre> <peso> <edad>
* ingresar ave <nombre> <peso> <edad> <envergadura>
* vacunar <nombre>
* asignar <nombre>
* medicar <nombre> <mg>
* alta <nombre>
* reporte
* salir


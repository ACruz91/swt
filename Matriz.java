package matriz;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class Matriz {
	/* Atriburos de la Clase Matriz */
	private int[][] matriz;
	private int filas, columnas;
	private int min, max;

	public void crear(int nFilas, int nColumnas) {
		matriz = new int[nFilas][nColumnas];
		int nMin, nMax;
		nMin = nMax = 0;
		// Este contador sirve para inicializar nMin y nMax al primer numero de
		// la Matriz, este contador hacer de controlador para que solo entre una
		// única vez
		int cont = 0;
		for (int i = 0; i < nFilas; i++) {
			for (int j = 0; j < nColumnas; j++) {
				matriz[i][j] = (int) (Math.random() * 100);
				if (cont <= 0) {
					nMin = nMax = matriz[0][0];
					cont++;
				}
				if (nMin > matriz[i][j]) {
					nMin = matriz[i][j];
				}
				if (nMax < matriz[i][j]) {
					nMax = matriz[i][j];
				}
			}
		}
		setFilas(nFilas);
		setColumnas(nColumnas);
		setMinimo(nMin);
		setMaximo(nMax);
	}

	/* Metodos Get */
	public int getMatriz(int nFilas, int nColumnas) {
		return this.matriz[nFilas][nColumnas];
	}

	public int getFilas() {
		return this.filas;
	}

	public int getColumnas() {
		return this.columnas;
	}

	public int getMinimo() {
		return this.min;
	}

	public int getMaximo() {
		return this.max;
	}

	/* Metodos Set */
	public void setFilas(int nFilas) {
		filas = nFilas;
	}

	public void setColumnas(int nColumnas) {
		columnas = nColumnas;
	}

	public void setMinimo(int nMin) {
		min = nMin;
	}

	public void setMaximo(int nMax) {
		max = nMax;
	}

	public void leer(String fichero) {
		int nFilas, nColumnas, cont, i, j, nLineas;
		nFilas = nColumnas = cont = i = j = nLineas = 0;
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;

		try {
			// Lectura del Fichero
			archivo = new File(fichero);
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			String linea;

			while ((linea = br.readLine()) != null) {
				// Leemos la primera Línea del Fichero que se corresponde con el
				// número de Filas de la Matriz
				if (cont == 0) {
					nFilas = Integer.parseInt(linea);
					setFilas(nFilas);
					cont = cont + 1;
					// Leemos la segunda Línea del Fichero que se corresponde
					// con el número de Columnas de la Matriz
				} else if (cont == 1) {
					nColumnas = Integer.parseInt(linea);
					setColumnas(nColumnas);
					cont = cont + 1;
					nLineas = nFilas * nColumnas + 2;
				} else {
					// Si el Contador es == 2 quiere decir que ya estamos en la
					// Línea donde empieza la Matriz por tanto leemos línea por
					// línea
					// Este if sirve para crear la matriz cuando empieza a leer
					// la matriz
					if (cont == 2) {
						matriz = new int[nFilas][nColumnas];
					}
					if (cont != nLineas && cont != nLineas + 1) {
						matriz[i][j] = Integer.parseInt(linea);
						j++;
					}
					if (cont == nLineas) {
						setMinimo(Integer.parseInt(linea));
					} else if (cont == nLineas + 1) {
						setMaximo(Integer.parseInt(linea));
					}

					// Si j ha llegado al número de columnas, ponemos el número
					// de columnas a 0 y añadimos 1 a i para que rellene la
					// siguiente fila
					if (j == nColumnas) {
						j = 0;
						i++;
					}

					// Incrementamos el Contador para comprobar que la matriz se
					// crea en la línea 3 ya que antes está nFilas y nColumnas
					cont = cont + 1;
				}

			}
			System.out.println(" ");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Cerrado del Fichero
			try {
				if (null != fr) {
					fr.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public void guardar(String fichero) {
		FileWriter fw = null;
		PrintWriter pw = null;
		min = max = matriz[0][0];
		try {
			fw = new FileWriter(fichero);
			pw = new PrintWriter(fw);
			pw.println(filas);
			pw.println(columnas);
			for (int i = 0; i < filas; i++) {
				for (int j = 0; j < columnas; j++) {
					if (min > matriz[i][j]) {
						min = matriz[i][j];
					}
					if (max < matriz[i][j]) {
						max = matriz[i][j];
					}
					pw.print(matriz[i][j]);
					pw.println("");
				}
			}
			pw.println(min);
			pw.println(max);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != fw)
					fw.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}
